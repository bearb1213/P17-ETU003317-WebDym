package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

public class CreditFormServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispat = request.getRequestDispatcher("/CreditForm.jsp");
        dispat.forward(request, response);
    }
    
}
