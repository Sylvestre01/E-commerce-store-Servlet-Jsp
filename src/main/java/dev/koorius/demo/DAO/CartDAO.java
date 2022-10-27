package dev.koorius.demo.DAO;

import dev.koorius.demo.Database.DatabaseConnection;
import dev.koorius.demo.model.Cart;
import dev.koorius.demo.model.Product;
import dev.koorius.demo.model.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CartDAO {
    public static Connection connection () {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "if30luw@");
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
        }
        return connection;
    }

    public static void addToCart (User myUser, Product product) throws SQLException {
        try {
            Connection myConnection = CartDAO.connection();
            PreparedStatement sqlQuery = myConnection.prepareStatement("INSERT INTO cart(customerId, product, quantity, dated) VALUES (?, ?, ?, ?)");

            /**
             * converting current time(now) to a sql time format for the query with the
             * method(convertFromJAVADateToSQLDate) defined at the bottom
             */
            java.util.Date now = new java.util.Date();
            java.sql.Date sqlDate = convertFromJAVADateToSQLDate(now);


            /* adding parameters to the sql query */
            sqlQuery.setLong(1, myUser.getUserId());
            sqlQuery.setString(2, product.getProductName());
            sqlQuery.setInt(3, product.getQuantity());
            sqlQuery.setDate(4, sqlDate);

            sqlQuery.executeUpdate();
            System.out.println("inside add to cart");
            myConnection.close();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    /** create a method to load the content of the cart table
     * store it in a hashmap
     * @param
     * @return
     */

    public static Map<String,Cart> loadCart (Long customerId) {
        Map<String, Cart> cartMap = new HashMap<>();
        Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement sqlQuery = connection.prepareStatement("SELECT * From cart WHERE customerId = ?");
            sqlQuery.setLong(1, customerId);
            ResultSet queryResult = sqlQuery.executeQuery();
            while (queryResult.next()) {
                Cart myCart = new Cart();
                myCart.setCartId(queryResult.getLong(1));
                myCart.setCustomerId(queryResult.getLong(2));
                myCart.setProduct(queryResult.getString(3));
                myCart.setQuantity(queryResult.getLong(4));
                myCart.setDate(queryResult.getDate(5));
                cartMap.put(myCart.getProduct(), myCart);
            }
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        return cartMap;
    }

    public static void removeFromCart (String productName, Long customerId) {
        String query = "DELETE FROM cart WHERE product = ?  AND customerId = ?";
        Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement sqlQuery = connection.prepareStatement(query);
            sqlQuery.setString(1, productName);
            sqlQuery.setLong(2,customerId );
            sqlQuery.execute();
            connection.close();

        } catch (SQLException e) {
            e.getMessage();
        }
    }


    public static java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }
}
