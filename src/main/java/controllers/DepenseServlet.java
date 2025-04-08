package controllers;
import jakarta.servlet.*;

import jakarta.servlet.http.*;
import models.*;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import Util.BaseObject;


public class DepenseServlet extends HttpServlet{
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
        String credit = request.getParameter("credit");
        String montant = request.getParameter("montant");
        String date = request.getParameter("date");
        if (credit!=null && !credit.isEmpty() && montant!=null && !montant.isEmpty() && date!=null && !date.isEmpty() ){
            try {
                testSess(request);
                int id = Integer.parseInt(credit);
                double mont= Double.parseDouble(montant);
                Date d = Date.valueOf(date);
                Depense depense= new Depense(0,id,mont,d);
                depense.saveDep();
            } catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
        } else {
            throw new ServletException("info pas complet");
        }
        response.sendRedirect("./depForm");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Depense mpaka = new Depense();
            List<BaseObject> baseObjects = mpaka.getAll();
            List<Depense> depenses = new ArrayList<>();
            for(BaseObject d : baseObjects){
                depenses.add((Depense) d);
            }
            request.setAttribute("depenses", depenses);

            RequestDispatcher dispat = request.getRequestDispatcher("/DepenseListe.jsp");
            dispat.forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

}
