package dev.koorius.demo.Server;

import dev.koorius.demo.model.Product;
import dev.koorius.demo.DAO.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("addProduct.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        /**admin trying to add products to the store
         * get request parameters from the addProduct.jsp
         */
        String productName = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String category = request.getParameter("category");
        double price = Double.parseDouble((request.getParameter("price")));

        /** use these params to create a user object */
        Product newProduct = new Product();
        newProduct.setProductName(productName);
        newProduct.setQuantity(quantity);
        newProduct.setCategory(category);
        newProduct.setPrice(price);
        try {/** call the ProductDAO addProduct method to send data to the db */
            ProductDAO.addProduct(newProduct);
            response.sendRedirect("./homepage");
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
