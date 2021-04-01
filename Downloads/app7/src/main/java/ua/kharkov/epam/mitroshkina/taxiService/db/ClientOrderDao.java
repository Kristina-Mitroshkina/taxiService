package ua.kharkov.epam.mitroshkina.taxiService.db;

import ua.kharkov.epam.mitroshkina.taxiService.db.bean.ConfirmedOrderBean;
import ua.kharkov.epam.mitroshkina.taxiService.db.entity.ClientOrder;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;


public class ClientOrderDao {

    private static final String SQL__FIND_DESTINATION =
            "SELECT (dep.km-des.km) FROM addresses dep JOIN clientOrder o "+
            "ON dep.address_id=o.departure_address JOIN addresses des ON o.destination_address=des.address_id WHERE o.id=?";
    private final static String ADD_NEW_CLIENT_ORDER = "INSERT INTO clientOrder(user_id,departure_address,destination_address,passengers_number,car_category) VALUES(?,?,?,?,?)";
    private final static String ADD_NEW_CONFIRMED_ORDER = "INSERT INTO confirmedOrder(clientOrder_id,cost,discount_cost,car_id) VALUES(?,?,?,?)";
    private final static String FIND_SUITABLE_CAR = " SELECT c.car_id FROM cars c, clientOrder co WHERE c.category_id = co.car_category AND co.passengers_number<=c.max_capacity AND c.state_id=0 AND co.id = ?";
    private final static String FIND_SUITABLE_CAR_ANOTHER_CATEGORY = " SELECT c.car_id FROM cars c, clientOrder co WHERE co.passengers_number<=c.max_capacity AND c.state_id=0 AND co.id = ?";
    private final static String GET_CONFIRMED_ORDER = "SELECT cono.clientOrder_id, dep.str_name, des.str_name, clo.passengers_number, ca.category_name, d.brand, d.model, c.car_number, dr.dr_name, dr.dr_surname, cono.cost, cono.discount_cost\n" +
            "FROM addresses dep, addresses des, clientOrder clo, car_category ca, car_description d, cars c, drivers dr, confirmedOrder cono\n" +
            "WHERE cono.clientOrder_id = clo.id AND clo.car_category = ca.category_id AND dep.address_id = clo.departure_address AND des.address_id = clo.destination_address AND cono.car_id = c.car_id AND c.car_description_id = d.car_description_id AND c.driver_id = dr.driver_id AND cono.confirmedOrder_id = ?";
    private final static String UPDATE_ORDER_STATUS = "UPDATE confirmedOrder SET status = 'confirmed' WHERE clientOrder_id = ?";
    private final static String GET_ALL_ORDERS= "SELECT cono.confirmedOrder_id, clo.user_id, dep.str_name, des.str_name, clo.passengers_number, c.car_id, cono.cost, cono.discount_cost, clo.order_date, cono.status\n" +
            "FROM addresses dep, addresses des, clientOrder clo, car_category ca, car_description d, cars c, confirmedOrder cono\n" +
            "WHERE cono.clientOrder_id = clo.id AND clo.car_category = ca.category_id AND dep.address_id = clo.departure_address AND des.address_id = clo.destination_address AND cono.car_id = c.car_id AND c.car_description_id = d.car_description_id";
    private final static String SORT_ORDERS_BY_DATE = "SELECT cono.confirmedOrder_id, clo.user_id, dep.str_name, des.str_name, clo.passengers_number, c.car_id, cono.cost, cono.discount_cost, clo.order_date, cono.status\n" +
            "FROM addresses dep, addresses des, clientOrder clo, car_category ca, car_description d, cars c, confirmedOrder cono\n" +
            "WHERE cono.clientOrder_id = clo.id AND clo.car_category = ca.category_id AND dep.address_id = clo.departure_address AND des.address_id = clo.destination_address AND cono.car_id = c.car_id AND c.car_description_id = d.car_description_id ORDER BY clo.order_date DESC";
    private final static String SORT_ORDERS_BY_COST = "SELECT cono.confirmedOrder_id, clo.user_id, dep.str_name, des.str_name, clo.passengers_number, c.car_id, cono.cost, cono.discount_cost, clo.order_date, cono.status\n" +
            "FROM addresses dep, addresses des, clientOrder clo, car_category ca, car_description d, cars c, confirmedOrder cono\n" +
            "WHERE cono.clientOrder_id = clo.id AND clo.car_category = ca.category_id AND dep.address_id = clo.departure_address AND des.address_id = clo.destination_address AND cono.car_id = c.car_id AND c.car_description_id = d.car_description_id ORDER BY cono.cost DESC";
    private final static String FILTER_ORDERS_BY_CLIENTS = "SELECT cono.confirmedOrder_id, clo.user_id, dep.str_name, des.str_name, clo.passengers_number, c.car_id, cono.cost, cono.discount_cost, clo.order_date, cono.status\n" +
            "FROM addresses dep, addresses des, clientOrder clo, car_category ca, car_description d, cars c, confirmedOrder cono\n" +
            "WHERE cono.clientOrder_id = clo.id AND clo.car_category = ca.category_id AND dep.address_id = clo.departure_address AND des.address_id = clo.destination_address AND cono.car_id = c.car_id AND c.car_description_id = d.car_description_id AND clo.user_id = ?";
    private final static String FILTER_ORDERS_BY_DATE = "SELECT cono.confirmedOrder_id, clo.user_id, dep.str_name, des.str_name, clo.passengers_number, c.car_id, cono.cost, cono.discount_cost, clo.order_date, cono.status\n" +
            "FROM addresses dep, addresses des, clientOrder clo, car_category ca, car_description d, cars c, confirmedOrder cono\n" +
            "WHERE cono.clientOrder_id = clo.id AND clo.car_category = ca.category_id AND dep.address_id = clo.departure_address AND des.address_id = clo.destination_address AND cono.car_id = c.car_id AND c.car_description_id = d.car_description_id AND clo.order_date LIKE ?";


    private Connection getConnection() throws SQLException {
        return getConnection(true);
    }

    private Connection getConnection(boolean autoCommit) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taxiService", "root", "14121710");
        con.setAutoCommit(autoCommit);
        return con;
    }

    private static void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                // nothing
            }
        }
    }

    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                // nothing to do
            }
        }
    }

    public int addNewClientOrder(ClientOrder clientOrder) throws SQLException {
        Connection conn=null;
        PreparedStatement prst=null;
        ResultSet rs=null;
        try {
            conn = getConnection();
            prst=conn.prepareStatement(ADD_NEW_CLIENT_ORDER, Statement.RETURN_GENERATED_KEYS);
            int k=1;
            prst.setInt(k++,clientOrder.getUser_id());
            prst.setInt(k++,clientOrder.getDeparture_address());
            prst.setInt(k++,clientOrder.getDestination_address());
            prst.setInt(k++,clientOrder.getPassengers_number());
            prst.setInt(k,clientOrder.getCar_category());
            if(prst.executeUpdate()>0) {
                rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    clientOrder.setClientOrder_id(rs.getInt(1));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            close(rs);
            close(prst);
            close(conn);
        }
        return clientOrder.getClientOrder_id();
    }

    public int findSuitableCar(int clientOrder_id) throws SQLException{
        int car_id = 0;
        Connection conn=null;
        PreparedStatement prst=null;
        ResultSet rs=null;
        try {
            conn = getConnection();
            prst = conn.prepareStatement(FIND_SUITABLE_CAR);
            prst.setInt(1, clientOrder_id);
            rs = prst.executeQuery();
            if (rs.next()) {
                car_id = rs.getInt(1);
            }
            return car_id;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            close(rs);
            close(prst);
            close(conn);
        }
    }

    public int findSuitableCarAnotherCategory(int clientOrder_id) throws SQLException{
        int car_id = 0;
        Connection conn=null;
        PreparedStatement prst=null;
        ResultSet rs=null;
        try {
            conn = getConnection();
            prst = conn.prepareStatement(FIND_SUITABLE_CAR_ANOTHER_CATEGORY);
            prst.setInt(1, clientOrder_id);
            rs = prst.executeQuery();
            if (rs.next()) {
                car_id = rs.getInt(1);
            }
            return car_id;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            close(rs);
            close(prst);
            close(conn);
        }
    }

    public int addNewConfirmedOrder(int clientOrder_id, int car_id) throws SQLException {
        int confirmedOrder_id = 0;
        Connection conn=null;
        PreparedStatement prst=null;
        ResultSet rs=null;
        try {
            conn = getConnection();
            int destination =0;
            int cost =0;
            prst = conn.prepareStatement(SQL__FIND_DESTINATION);
            prst.setInt(1, clientOrder_id);
            rs = prst.executeQuery();
            if (rs.next()) {
                destination = rs.getInt(1);
            }
            destination = abs(destination);
            if(destination<3){
                cost = 40;
            }
            else{
                cost = destination *15;
            }
            float discount_cost = (float) (cost * 0.9);
                prst = conn.prepareStatement(ADD_NEW_CONFIRMED_ORDER, Statement.RETURN_GENERATED_KEYS);
                int k = 1;
                prst.setInt(k++, clientOrder_id);
                prst.setInt(k++, cost);
                prst.setFloat(k++, discount_cost);
                prst.setInt(k, car_id);
                if (prst.executeUpdate() > 0) {
                    rs = prst.getGeneratedKeys();
                    if (rs.next()) {
                        confirmedOrder_id = rs.getInt(1);
                    }
                }
            return confirmedOrder_id;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            close(rs);
            close(prst);
            close(conn);
        }
    }

    public ConfirmedOrderBean showConfirmedOrderBean(int confirmedOrder_id) throws SQLException {
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        ConfirmedOrderBean cob = new ConfirmedOrderBean();
        try {
            con = getConnection();
            prst = con.prepareStatement(GET_CONFIRMED_ORDER);
            prst.setInt(1, confirmedOrder_id);
            rs = prst.executeQuery();
            if (rs.next()) {
                cob = getConfirmedOrderBeanValue(rs);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
            close(rs);
            close(prst);
            close(con);
        }
        return cob;
    }

    public boolean updateOrderStatus(int clientOrder_id) throws SQLException {
        boolean result=false;
        Connection conn=null;
        PreparedStatement prst=null;
        try {
            conn = getConnection();
            prst=conn.prepareStatement(UPDATE_ORDER_STATUS);
            int k=1;
            prst.setInt(k,clientOrder_id);
            result=prst.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            close(prst);
            close(conn);
        }
        return result;
    }

    public int timeToWait(){
        int time = (int)(Math.random()*10+3);
        return time;
    }

    public List<ConfirmedOrderBean> getConfirmedOrderBeans() throws SQLException {
        try (Connection conn = getConnection()) {
            return getConfirmedOrderBeans(conn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private List<ConfirmedOrderBean> getConfirmedOrderBeans(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(GET_ALL_ORDERS);
             ResultSet rs = ps.executeQuery()) {
            List<ConfirmedOrderBean> list = new LinkedList<>();
            while (rs.next()) {
                ConfirmedOrderBean bean = getConfirmedOrderBeanValueForAdmin(rs);
                list.add(bean);
            }
            return list;
        }
    }

    public List<ConfirmedOrderBean> sortConfirmedOrderBeansByDate() throws SQLException {
        try (Connection conn = getConnection()) {
            return sortConfirmedOrderBeansByDate(conn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private List<ConfirmedOrderBean> sortConfirmedOrderBeansByDate(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SORT_ORDERS_BY_DATE);
             ResultSet rs = ps.executeQuery()) {
            List<ConfirmedOrderBean> list = new LinkedList<>();
            while (rs.next()) {
                ConfirmedOrderBean bean = getConfirmedOrderBeanValueForAdmin(rs);
                list.add(bean);
            }
            return list;
        }
    }

    public List<ConfirmedOrderBean> sortConfirmedOrderBeansByCost() throws SQLException {
        try (Connection conn = getConnection()) {
            return sortConfirmedOrderBeansByCost(conn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private List<ConfirmedOrderBean> sortConfirmedOrderBeansByCost(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SORT_ORDERS_BY_COST);
             ResultSet rs = ps.executeQuery()) {
            List<ConfirmedOrderBean> list = new LinkedList<>();
            while (rs.next()) {
                ConfirmedOrderBean bean = getConfirmedOrderBeanValueForAdmin(rs);
                list.add(bean);
            }
            return list;
        }
    }

    public List<ConfirmedOrderBean> filterConfirmedOrderBeansByClients(int user_id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ConfirmedOrderBean> list = new LinkedList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(FILTER_ORDERS_BY_CLIENTS);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ConfirmedOrderBean bean = getConfirmedOrderBeanValueForAdmin(rs);
                list.add(bean);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
            close(rs);
            close(ps);
            close(con);
        }
        return list;
    }

    public List<ConfirmedOrderBean> filterConfirmedOrderBeansByDates(String date) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ConfirmedOrderBean> list = new LinkedList<>();
        try {
            date=date+"%";
            con = getConnection();
            ps = con.prepareStatement(FILTER_ORDERS_BY_DATE);
            ps.setString(1, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                ConfirmedOrderBean bean = getConfirmedOrderBeanValueForAdmin(rs);
                list.add(bean);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
            close(rs);
            close(ps);
            close(con);
        }
        return list;
    }

    private ConfirmedOrderBean getConfirmedOrderBeanValueForAdmin(ResultSet rs) throws SQLException{
        try {
            ConfirmedOrderBean cob = new ConfirmedOrderBean();
            cob.setConfirmedOrder_id(rs.getInt("confirmedOrder_id"));
            cob.setUser_id(rs.getInt("user_id"));
            cob.setDeparture_address(rs.getString("dep.str_name"));
            cob.setDestination_address(rs.getString("des.str_name"));
            cob.setPassengers_number(rs.getInt("passengers_number"));
            cob.setCar_id(rs.getInt("car_id"));
            cob.setCost(rs.getInt("cost"));
            cob.setDiscount_cost(rs.getInt("discount_cost"));
            cob.setOrder_date(rs.getString("order_date"));
            cob.setStatus(rs.getString("status"));
            return cob;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private ConfirmedOrderBean getConfirmedOrderBeanValue(ResultSet rs) throws SQLException{
        try {
            ConfirmedOrderBean cob = new ConfirmedOrderBean();
            cob.setClientOrder_id(rs.getInt("clientOrder_id"));
            cob.setDeparture_address(rs.getString("dep.str_name"));
            cob.setDestination_address(rs.getString("des.str_name"));
            cob.setPassengers_number(rs.getInt("passengers_number"));
            cob.setCategory_name(rs.getString("category_name"));
            cob.setBrand(rs.getString("brand"));
            cob.setModel(rs.getString("model"));
            cob.setCar_number(rs.getString("car_number"));
            cob.setDriver_name(rs.getString("dr_name"));
            cob.setDriver_surname(rs.getString("dr_surname"));
            cob.setCost(rs.getInt("cost"));
            cob.setDiscount_cost(rs.getInt("discount_cost"));
            return cob;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
