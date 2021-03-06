/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author matjo
 */
public class OverviewServlet extends HttpServlet {
        private Connection conn = null;
        private Statement stmt = null;
        private ResultSet rs = null;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>All users:</h1>");
            //lager query
        String query = "SELECT * FROM Progress;";
        //connect til db
        conn = DbUtil.ConnectionManager.getConnection();
        stmt = conn.createStatement();
        //executer query
        rs = stmt.executeQuery(query);
            out.println("<ul>");
      // itererer gjennom hele listen (resultset rs)
      while (rs.next())
      {
          //lager ny printer med navn sqlWriter og skriver ut i HTML format
        PrintWriter sqlWriter = response.getWriter();
        //hvilke columns som skal kalles hva (SQL -> Java)
        String firstName = rs.getString("u_fname");
        String lastName = rs.getString("u_lname");
        int delivered = rs.getInt("Delivered");
        int approved = rs.getInt("Approved");
        int totalModules = rs.getInt("ModuleCount");
        // kjøres for hver row med følgende format:
        int progressValue = 100 / totalModules * delivered;
        sqlWriter.format("<li>Name: %s %s <br>   <progress value=\"%s\" max=\"%s\"></progress></br>"
                + "Levert: %s <br> Godkjent: %s", firstName, lastName, approved, totalModules, delivered, approved);
      }
        out.println("</ul>");
      //lukker tilkoblingen
         stmt.close();
            } //henter og sender feilmeldinger. Foreløpig går de kun til console i IDE
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
