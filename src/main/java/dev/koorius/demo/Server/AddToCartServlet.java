package dev.koorius.demo.Server;

import dev.koorius.demo.DAO.CartDAO;
import dev.koorius.demo.model.Cart;
import dev.koorius.demo.model.Product;
import dev.koorius.demo.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**to view the customer's cart
         * get current session and get the user credentials already
         * set as attribute when the user logged in (in the login servlet)
         * then send the request to the viewCart.jsp
         */
        HttpSession session = request.getSession();
        System.out.println("here called");
        User loggedInUser = (User) session.getAttribute("userCred");
        Map<String,Cart> cartMap = CartDAO.loadCart(loggedInUser.getUserId());
        session.setAttribute("loggedInUserCart", cartMap);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewCart.jsp");
        dispatcher.forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**add to customer cart here, the addToCart button in homepage.jsp
         * invokes this doPost method. Get user details att set when the user logged in
         * the call the CartDAO addToCart method
         * then send redirect by invoking the doGet method of this servlet
         */
        Product cartProduct = new Product();
        String productName = request.getParameter("product");
        cartProduct.setProductName(productName);
        cartProduct.setQuantity(10); /* hardcoded cos of time constraint */
        HttpSession session = request.getSession();
        User myUser = (User) session.getAttribute("userCred");

        try {
            CartDAO.addToCart(myUser, cartProduct);
            response.sendRedirect("viewCart.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }





}
