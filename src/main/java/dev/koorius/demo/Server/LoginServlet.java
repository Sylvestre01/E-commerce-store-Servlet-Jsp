package dev.koorius.demo.Server;

import dev.koorius.demo.DAO.UserDAO;
import dev.koorius.demo.model.Role;
import dev.koorius.demo.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.sendRedirect("login.jsp");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        String customerEmail = request.getParameter("email"); /* get login credentials from the login.jsp */
        String customerPassword = request.getParameter("password");
        try {
            User myUser =  UserDAO.customerLogin(customerEmail, customerPassword);/* login with these credentials */
            System.out.println(myUser.getUserRole());
            if(myUser.getEmail() != null) {
                userSession.setAttribute("userCred", myUser); /* store the user credential as an attr */
                if(myUser.getUserRole().equals(Role.ADMIN)) {
                    response.sendRedirect("addProduct.jsp"); /* if the user is an admin, advance to the add product page */
                } else {
                response.sendRedirect("./homepage"); /* else advance to homepage where products are displayed */
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }

    }


    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("email");
        session.removeAttribute("password");
    }





}
