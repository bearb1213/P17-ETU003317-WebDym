package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;
import models.*;

public class CreditServlet extends HttpServlet{
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
        String libelle = request.getParameter("libelle");
        String montant = request.getParameter("montant");

        if (libelle!=null && !libelle.isEmpty() && montant!=null && !montant.isEmpty() ){
            try {
                testSess(request);
                double mont = Double.parseDouble(montant);

                Credit credit = new Credit(0,libelle,mont);

                credit.save();
                
            } catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        } else {
            throw new ServletException("info pas complet");
        }
        response.sendRedirect("./");
    }
}
