package ua.kharkov.epam.mitroshkina.taxiService.db;

import ua.kharkov.epam.mitroshkina.taxiService.db.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Data access object for User entity.
 */
public class UserDao {

    private static final String SQL__FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String FIND_ALL_CLIENTS = "SELECT * FROM users WHERE role_id=1";
    private final static String ADD_NEW_CLIENT = "INSERT INTO users(name,surname,email,password,phone_number,role_id) VALUES(?,?,?,?,?,?)";

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

    public User findUserByEmail(String email) throws SQLException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = getUserValue(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return user;
    }

    public List<User> findAllClients() throws SQLException {
        try (Connection conn = getConnection()) {
            return findAllClients(conn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private List<User> findAllClients(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(FIND_ALL_CLIENTS);
             ResultSet res = ps.executeQuery()) {
            List<User> list = new LinkedList<>();
            while (res.next()) {
                User user = getUserValue(res);
                list.add(user);
            }
            return list;
        }
    }

    public boolean addNewClient(User user) throws SQLException {
        boolean result=false;
        Connection conn=null;
        PreparedStatement prst=null;
        ResultSet rs=null;
        try {
            conn = getConnection();
            prst=conn.prepareStatement(ADD_NEW_CLIENT, Statement.RETURN_GENERATED_KEYS);
            int k=1;
            prst.setString(k++,user.getName());
            prst.setString(k++,user.getSurname());
            prst.setString(k++,user.getEmail());
            prst.setString(k++,user.getPassword());
            prst.setInt(k++,user.getPhone_number());
            prst.setInt(k,user.getRole_id());
            if(prst.executeUpdate()>0){
                rs = prst.getGeneratedKeys();
                if(rs.next()) {
                    user.setUser_id(rs.getInt(1));
                    result=true;
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
        return result;
    }

    private User getUserValue(ResultSet rs) throws SQLException{
        try {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhone_number(rs.getInt("phone_number"));
            user.setRole_id(rs.getInt("role_id"));
            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
