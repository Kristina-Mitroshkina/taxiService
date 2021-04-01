package ua.kharkov.epam.mitroshkina.taxiService.db;

import ua.kharkov.epam.mitroshkina.taxiService.db.bean.CarBean;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class CarDao {

    private static final String SQL__GET_CAR_BEANS =
            "SELECT c.car_id, d.brand, d.model, c.car_number, c.max_capacity, ca.category_name, dr.dr_name, dr.dr_surname, s.state_name" +
                    "	FROM cars c JOIN car_description d ON c.car_description_id=d.car_description_id" +
                    "   JOIN car_category ca ON c.category_id=ca.category_id " +
                    "   JOIN drivers dr ON c.driver_id=dr.driver_id" +
                    "   JOIN car_state s ON c.state_id=s.state_id" ;


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

    public List<CarBean> getCarBeans() throws SQLException {
        try (Connection conn = getConnection()) {
            return getCarBeans(conn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private List<CarBean> getCarBeans(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SQL__GET_CAR_BEANS);
             ResultSet res = ps.executeQuery()) {
            List<CarBean> list = new LinkedList<>();
            while (res.next()) {
                CarBean bean = getCarBeanValue(res);
                list.add(bean);
            }
            return list;
        }
    }

    private CarBean getCarBeanValue(ResultSet rs) throws SQLException{
        try {
            CarBean bean = new CarBean();
            bean.setCar_id(rs.getLong("car_id"));
            bean.setBrand(rs.getString("brand"));
            bean.setModel(rs.getString("model"));
            bean.setCar_number(rs.getString("car_number"));
            bean.setMax_capacity(rs.getInt("max_capacity"));
            bean.setCategory_name(rs.getString("category_name"));
            bean.setDriver_name(rs.getString("dr_name"));
            bean.setDriver_surname(rs.getString("dr_surname"));
            bean.setState_name(rs.getString("state_name"));
            return bean;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
