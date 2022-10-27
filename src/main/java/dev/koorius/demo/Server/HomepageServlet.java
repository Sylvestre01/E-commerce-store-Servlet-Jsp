package dev.koorius.demo.Server;

import dev.koorius.demo.DAO.ProductDAO;
import dev.koorius.demo.DAO.WishListDAO;
import dev.koorius.demo.model.Product;
import dev.koorius.demo.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/homepage")
public class HomepageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> allProducts = ProductDAO.getAllProducts();

        /** create a session
         * store all products as attribute in the session with key and value resp.
         * send the request to homepage.jsp(which display the data) and dispatch
         */
        HttpSession session = request.getSession();
        session.setAttribute("allProducts", allProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
        dispatcher.forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**to activate like button, load all products in the db
         * set the data as attr in the session and grab the productName value
         * when the like button is clicked. use this data and other data to
         * call the WishListDAO like method which store them in the db
         */
        List<Product> allProducts = ProductDAO.getAllProducts();
        HttpSession session = request.getSession();
        session.setAttribute("allProducts", allProducts);

        String productToLikeName = request.getParameter("productToLike");
        String productToUnlikeName = request.getParameter("productToUnlike");

        if(productToLikeName == null) {
            User myUser = (User) session.getAttribute("userCred");
            WishListDAO.unlike(productToUnlikeName, myUser.getUserId());
            response.sendRedirect("homepage.jsp");

        } else {
        Product productToLike = ProductDAO.search(productToLikeName);
        User myUser = (User) session.getAttribute("userCred");
        WishListDAO.like(productToLike.getProductId(),productToLikeName, myUser);
            response.sendRedirect("homepage.jsp");

        }

//        PrintWriter out = response.getWriter();
//        out.println("product added to wishlist");
//        response.sendRedirect("homepage.jsp");
    }
}
