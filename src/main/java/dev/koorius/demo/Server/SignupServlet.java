package dev.koorius.demo.Server;

import dev.koorius.demo.model.User;
import dev.koorius.demo.DAO.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Signup", value = "/Signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserDAO userDAO = new UserDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            response.sendRedirect("CustomerSignup.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            signUp(request, response);
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void signUp (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String customerUsername = request.getParameter("username");
        String customerPhone = request.getParameter("phoneNumber");
        String customerEmail = request.getParameter("email");
        String customerPassword = request.getParameter("password");

        userDAO.customerLogin(customerEmail, customerPassword);
        User newUser = new User();
        newUser.setUsername(customerUsername);
        newUser.setPhoneNumber(customerPhone);
        newUser.setEmail(customerEmail);
        newUser.setPassword(customerPassword);
        try {
            UserDAO.customerSignup(newUser);
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
