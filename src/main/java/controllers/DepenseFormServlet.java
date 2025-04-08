package controllers;
import jakarta.servlet.*;

import jakarta.servlet.http.*;
import models.Credit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Util.BaseObject;

public class DepenseFormServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Credit c= new Credit();
            List<BaseObject> credits = c.getAll();
            List<Credit> ret = new ArrayList<>();
            
            for(BaseObject re : credits){
                ret.add((Credit) re);
            }
            request.setAttribute("credits", ret);
            
            RequestDispatcher dispat = request.getRequestDispatcher("/DepenseForm.jsp");
            dispat.forward(request, response);
            
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }

    }
}
