/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connexion.Connexion;
import data.Region;
import data.Signalement;
import data.Valide;
import java.io.IOException;
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
public class Validation extends HttpServlet {

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
        String message = "";
        ServletContext context = getServletContext();
        
        
        if(request.getParameterMap().containsKey("i")){
            String id = request.getParameter("id");
            String i = request.getParameter("i");
            
            if(i.equals("0") || i.equals("1")){
                try {
                    new Valide(id, i, null).insert((Connexion)context.getAttribute("connexion"));
                } catch (Exception e) {
                    e.printStackTrace();
                    message = e.getMessage();
                }
            }else{
                message = "valeur non identifie";
            }
            response.sendRedirect("pages/home.jsp?onglet=2&pages=0");
        }else{
            String pages = (request.getParameter("pages")==null)?"0":request.getParameter("pages");
            try {
                int nbPage = new Signalement().getNbSignalement((Connexion)context.getAttribute("connexion"));
                ArrayList<Signalement> listes = new Signalement().getSignalementNonRepondu((Connexion)context.getAttribute("connexion"), (Integer.parseInt(pages)*10)+"", (10*(Integer.parseInt(pages)+1))+"");
                request.setAttribute("listes", listes);
                request.setAttribute("nbSignalement", nbPage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestDispatcher rd = request.getRequestDispatcher("/pages/validation.jsp?pages="+pages);
            rd.include(request, response);
        }
        
        //response.sendRedirect(request.getContextPath()+"/pages/home.jsp?onglet=2"+message);
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
