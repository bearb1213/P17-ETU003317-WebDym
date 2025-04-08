package controllers;
import jakarta.servlet.*;

import jakarta.servlet.http.*;
import java.io.*;

import javax.sql.rowset.serial.SerialException;


public class TestServlet extends HttpServlet{
    private String password = "ETU003317";
    public void testSess(HttpServletRequest request)throws ServletException{
        HttpSession sess = request.getSession();

        String test =(String) sess.getAttribute("id");
        if(test!=null && !test.isEmpty() && test.equals("testCO")){
        } else {
            throw new ServletException("Vous ne vous etes pas encore authentifier");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mdp = request.getParameter("mdp");
        if(mdp!=null && !mdp.isEmpty()){
            if(mdp.equals(password)){
                HttpSession sess = request.getSession();

                sess.setAttribute("id", "testCO");
            }else{
                throw new ServletException("mot de passe Incorrect");
            }
        } else {
            throw new ServletException("info non compltes");
        }
        response.sendRedirect("./");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispat = request.getRequestDispatcher("/loginForm.jsp");
        dispat.forward(request, response);   
    }

}
