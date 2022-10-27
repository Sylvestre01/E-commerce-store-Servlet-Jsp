package dev.koorius.demo.DAO;

import dev.koorius.demo.Database.DatabaseConnection;
import dev.koorius.demo.model.Cart;
import dev.koorius.demo.model.Product;
import dev.koorius.demo.model.User;
import dev.koorius.demo.model.Wishlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.koorius.demo.DAO.CartDAO.convertFromJAVADateToSQLDate;

public class WishListDAO {

    public static void like (Long id, String name, User user)  {

        String query = "INSERT INTO wishList (productName, productId, customerId, date) VALUES (?, ?, ?, ?)";
        Connection connection = DatabaseConnection.connect();
        try {
        PreparedStatement sqlQuery = connection.prepareStatement(query);
        sqlQuery.setString(1, name);
        sqlQuery.setLong(2, id);
        sqlQuery.setLong(3,user.getUserId());

        /** create a current date variable (now)
         * convert to sql date format with the custom static
         * method(convertFromJAVADateToSQLDate) imported from our CartDAO
         */
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = convertFromJAVADateToSQLDate(now);
        sqlQuery.setDate(4, sqlDate);

            System.out.println("hello from like");
        System.out.println("like query: " + sqlQuery);
        sqlQuery.executeUpdate();
        connection.close();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static void unlike (String productName, Long customerId) {
        String query = "DELETE FROM wishList WHERE productName = ? AND customerId = ?";
        Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement sqlQuery = connection.prepareStatement(query);
            sqlQuery.setString(1, productName);
            sqlQuery.setLong(2, customerId);
            sqlQuery.execute();
            connection.close();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static List<Wishlist> loadWishList (Long customerId) {

        List<Wishlist> wishList = new ArrayList<>();
        Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement sqlQuery = connection.prepareStatement("SELECT * From wishList WHERE customerId = ?");
            sqlQuery.setLong(1, customerId);
            ResultSet queryResult = sqlQuery.executeQuery();
            while (queryResult.next()) {
                Wishlist myWishList = new Wishlist();
                myWishList.setProductName(queryResult.getString(2));
                myWishList.setProductId(queryResult.getLong(3));
                myWishList.setCustomerId(queryResult.getLong(4));
                myWishList.setDate(queryResult.getDate(5));
                wishList.add(myWishList);
            }
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        return wishList;
    }
}
