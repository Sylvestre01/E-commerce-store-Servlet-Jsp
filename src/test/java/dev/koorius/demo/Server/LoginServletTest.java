package dev.koorius.demo.Server;

import dev.koorius.demo.DAO.UserDAO;
import dev.koorius.demo.model.Role;
import dev.koorius.demo.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginServletTest extends Mockito {
    static HttpServletRequest request;
    static HttpServletResponse response;
    static HttpSession session;
    static UserDAO userDAO = mock(UserDAO.class);;
    static LoginServlet loginServlet;
    static User user = new User();

    @BeforeAll
    static void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        user.setUserId(1L);
        user.setUsername("arya");
        user.setEmail("ayra@starr");
        user.setPassword("ayra");
        user.setPhoneNumber("1278034");
        user.setUserRole(Role.CUSTOMER);
    }

    @Test
    void doPost() throws SQLException, ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("ayra@starr");
        when(request.getParameter("password")).thenReturn("ayra");
        when(userDAO.customerLogin(any(),any())).thenReturn(user);
        loginServlet.doPost(request,response);

        User newUser = (User) session.getAttribute("userCred");

        assertEquals(user, newUser);

    }
}