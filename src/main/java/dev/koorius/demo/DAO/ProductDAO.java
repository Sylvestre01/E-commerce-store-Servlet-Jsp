package dev.koorius.demo.DAO;

import dev.koorius.demo.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO {
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

    public static void addProduct (Product product) throws SQLException {
        Connection myConnection = null;
        try {
            myConnection = connection();
            String query = "INSERT INTO product (productName, quantity, category, price) VALUES (?,?,?,?)";
            PreparedStatement sqlQuery = myConnection.prepareStatement(query);
            sqlQuery.setString(1, product.getProductName());
            sqlQuery.setInt(2, product.getQuantity());
            sqlQuery.setString(3, product.getCategory());
            sqlQuery.setDouble(4, product.getPrice());

            int rows = sqlQuery.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            myConnection.close();
        }
    }

    public static List<Product> getAllProducts () {
        List<Product> productList = new ArrayList<>();
        try {
        Connection connection = ProductDAO.connection();
        PreparedStatement sqlQuery = connection.prepareStatement("SELECT * FROM product");
            ResultSet queryResult = sqlQuery.executeQuery();
            while (queryResult.next()) {
                Product product = new Product();
                product.setProductId(queryResult.getLong(1));
                product.setProductName(queryResult.getString(2));
                product.setQuantity((queryResult.getInt(3)));
                product.setCategory(queryResult.getString(4));
                product.setPrice(queryResult.getDouble(5));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return productList;
    }

//    public static Map<String, Product> getAllProducts () {
//        Map<String, Product> productMap = new HashMap<>();
//        try {
//            Connection connection = ProductDAO.connection();
//            PreparedStatement sqlQuery = connection.prepareStatement("SELECT * FROM productList");
//            System.out.println("inside DAO new");
//            ResultSet queryResult = sqlQuery.executeQuery();
//            while (queryResult.next()) {
//                Product product = new Product();
//                product.setProductId(queryResult.getLong(1));
//                product.setProductName(queryResult.getString(2));
//                product.setQuantity((queryResult.getInt(3)));
//                product.setCategory(queryResult.getString(4));
//                product.setPrice(queryResult.getDouble(5));
//                productMap.put(product.getProductName(), product);
//            }
//            System.out.println(sqlQuery);
//            System.out.println(queryResult);
//
//        } catch (SQLException e) {
//            e.getMessage();
//        }
//        return productMap;
//    }

    public static Product search(String productName) {
        List<Product> allProduct = ProductDAO.getAllProducts();
        Product myProduct = new Product();
        for(Product product : allProduct) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                myProduct = product;
            }
        }
        return myProduct;
    }
}
