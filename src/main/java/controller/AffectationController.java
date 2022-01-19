/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connexion.Connexion;
import data.Admin;
import data.Affectation;
import data.Region;
import data.Signalement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
public class AffectationController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = getServletContext();
        PrintWriter out = response.getWriter();
        String action = (request.getAttribute("action")==null)?null:request.getAttribute("action")+"";
        String pages = (request.getParameter("pages")==null)?"0":request.getParameter("pages");
        int nbPage = 10;
        if(action==null){
            try {
                ArrayList<Signalement> listes = new Signalement().getSignalementNonReponduEtNonAssigne((Connexion)context.getAttribute("connexion"), Integer.parseInt(pages)*nbPage, nbPage*(Integer.parseInt(pages)+1));
                ArrayList<Region> listeRegion = new Region().getRegionFromDb((Connexion)context.getAttribute("connexion"));
                request.setAttribute("listes", listes);
                request.setAttribute("listeRegion", listeRegion);
                request.setAttribute("nbSignalement", new Signalement().getNbSignalementNonReponduEtNonAssigne((Connexion)context.getAttribute("connexion")));
                RequestDispatcher rd = request.getRequestDispatcher("/pages/affectation.jsp");
                rd.include(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(action!=null){
            try {
                int result = new Affectation().insertFromPage((Connexion)context.getAttribute("connexion"), request.getParameterMap(), ((Admin)request.getSession().getAttribute("admin")).getId());
            } catch (Exception e) {
                out.print(e.getMessage());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
