package dev.koorius.demo.Server;

import dev.koorius.demo.DAO.CartDAO;
import dev.koorius.demo.model.Cart;
import dev.koorius.demo.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cartWish")
public class CartWish extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        removeFromCart(request,response);
        response.sendRedirect("viewCart.jsp");

    }
    private void removeFromCart (HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String productToRemove = request.getParameter("productToRemove");
        User myUser = (User) session.getAttribute("userCred");

        /** first remove from database */
        CartDAO.removeFromCart(productToRemove, myUser.getUserId());

        /** then remove from cart */
        Map<String, Cart> cartMap = (Map<String, Cart>) session.getAttribute("loggedInUserCart");
        cartMap.remove(productToRemove);
    }

}
