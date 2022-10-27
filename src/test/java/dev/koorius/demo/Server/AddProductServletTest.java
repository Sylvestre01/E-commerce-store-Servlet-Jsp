package dev.koorius.demo.Server;

import dev.koorius.demo.DAO.ProductDAO;
import dev.koorius.demo.DAO.UserDAO;
import dev.koorius.demo.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AddProductServletTest extends Mockito {
    static HttpServletRequest request = mock(HttpServletRequest.class);;
    static HttpServletResponse response = mock(HttpServletResponse.class);;
    static HttpSession session = mock(HttpSession.class);
    static ProductDAO productDAO = mock(ProductDAO.class);
    Product product = new Product(69L, "noodles", "food", 23, 700.0);

    @Test
    void doPost() throws SQLException, ServletException, IOException {
        when(request.getParameter("name")).thenReturn("noodles");
        when(request.getParameter("quantity")).thenReturn("23");
        when(request.getParameter("category")).thenReturn("food");
        when(request.getParameter("price")).thenReturn("700.0");

        verify(request,times(1)).getParameter("name");
        verify(request, times(1)).getParameter("quantity");
        verify(request, times(1)).getParameter("category");
        verify(request, times(1)).getParameter("price");

        assertEquals(product, productDAO.search("noodles"));
    }
}