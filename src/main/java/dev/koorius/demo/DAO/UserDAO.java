package dev.koorius.demo.DAO;

import dev.koorius.demo.Database.DatabaseConnection;
import dev.koorius.demo.model.Role;
import dev.koorius.demo.model.User;

import java.sql.*;

public class UserDAO {


    /**
     * open connection to the user database with connect()
     * close connection with disconnect()
     * @throws SQLException
     */
    public static Connection connect()  {
                Connection myConnection = null;
            try { /* specifying MySQL Connector/J as the JDBC driver */
                Class.forName("com.mysql.cj.jdbc.Driver");
                myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "if30luw@");
            } catch (SQLException  | ClassNotFoundException e) {
                e.getMessage();
            }
                return myConnection;
    }

    public static void adminSignup (User customer) throws SQLException {
        /* create a sql query where '?' symbolises the query parameters */
            Connection myConnection = DatabaseConnection.connect();
        try {
            PreparedStatement queryStatement = myConnection.prepareStatement("INSERT INTO user (username, phoneNumber, email, password, userRole) VALUES (?, ?, ?, ?, 'ADMIN')");

            /* now we set the query parameters */ /* database index counts from 1 not 0 */
            queryStatement.setString(1, customer.getUsername());
            queryStatement.setString(2, customer.getPhoneNumber());
            queryStatement.setString(3, customer.getEmail());
            queryStatement.setString(4, customer.getPassword());

            System.out.println(queryStatement);
            queryStatement.executeUpdate();
            myConnection.close();

        } catch (SQLException e) {

        }
    }


    public static int customerSignup (User customer) throws SQLException {
        /* create a sql query where '?' symbolises the query parameters */
        int result = 0;
        try {
            String sqlQuery = "INSERT INTO user (username, phoneNumber, email, password, userRole) VALUES (?, ?, ?, ?, 'CUSTOMER')";
            Connection myConnection = UserDAO.connect();
            PreparedStatement queryStatement = myConnection.prepareStatement(sqlQuery);

            /* now we set the query parameters */ /* database index counts from 1 not 0 */
            queryStatement.setString(1, customer.getUsername());
            queryStatement.setString(2, customer.getPhoneNumber());
            queryStatement.setString(3, customer.getEmail());
            queryStatement.setString(4, customer.getPassword());


            System.out.println(queryStatement);
            result = queryStatement.executeUpdate();
            myConnection.close();

        } catch (SQLException e) {

        }
        return result;
    }

    public static User customerLogin (String email, String password) throws SQLException {

        User myUser = new User();
        String sqlQuery = "SELECT * FROM user WHERE email = ? AND password = ?";
        Connection connection = UserDAO.connect();
        PreparedStatement query = connection.prepareStatement(sqlQuery);
        query.setString(1, email);
        query.setString(2, password);

        ResultSet queryResult = query.executeQuery();
        while(queryResult.next()) {
            Long id = queryResult.getLong("userId");
            String username = queryResult.getString("username");
            String phone = queryResult.getString("phoneNumber");
            String realEmail = queryResult.getString("email");
            String role = String.valueOf(queryResult.getString("userRole"));
            myUser.setUserId(id);
            myUser.setUsername(username);
            myUser.setPhoneNumber(phone);
            myUser.setEmail(realEmail);
            myUser.setPassword(password);
            myUser.setUserRole(Role.valueOf(role));
            }
        queryResult.close();
        connection.close();

        return myUser;
    }
}
