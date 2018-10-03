/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.servidor;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author artur
 */
public class ServletCalc extends HttpServlet {

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
        response.setContentType("application/json"); 
        try (PrintWriter out = response.getWriter()) {
            //Declaramos variables
            Double num1 = Double.parseDouble(request.getParameter("num1").trim());
            Double num2 = Double.parseDouble(request.getParameter("num2").trim());
            Double resultado;
            String operacion = request.getParameter("operacion");
            
             //Declaramos gson
            Gson gson = new Gson();
            String error = "";
        
             //Comprobamos si se introducen 0 o caracteres
            if(num1== 0 || num2 == 0){
                 //Declaramos el gson y el json
                response.setStatus(500);
                error = "Los dos números han de ser positivos.";
                response.getWriter().append(gson.toJson(error));                
            }else{
                switch(operacion){
                    case "add":
                        resultado=num1+num2;
                        response.getWriter().append(gson.toJson(resultado));
                        break;
                    case "substract":
                        resultado=num1-num2;
                        response.getWriter().append(gson.toJson(resultado));   
                        break;
                    case "multiply":
                        resultado=num1*num2;
                        response.getWriter().append(gson.toJson(resultado));   
                        break;
                    case "division":
                        resultado=num1/num2;
                        response.getWriter().append(gson.toJson(resultado));   
                        break;
                    default:
                        error = "Ninguna opción coincide.";
                        response.getWriter().append(gson.toJson(operacion.toString())); 
                }
            }                      
        }finally{
            out.close();
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


