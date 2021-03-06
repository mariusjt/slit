<%-- 
    Document   : AllModules
    Created on : 26-Nov-2018, 10:37:53
    Author     : matjo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,DbUtil.ConnectionManager,java.sql.Statement,java.sql.ResultSet,java.io.IOException,java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alle moduler</title>
    </head>
    <body>
        <%
            out.println("<h2>Alle moduler</h2>");
            out.println("<ul>");
        %>
        <%
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT m_id, m_name, m_deadline, m_description FROM MODULE WHERE m_published = 1 ORDER BY m_id ASC;";
        //connect til db
        
    try {
        conn = DbUtil.ConnectionManager.getConnection();
        stmt = conn.createStatement();
        //executer query
        rs = stmt.executeQuery(query);
    

      // itererer gjennom hele listen (resultset rs)
      while (rs.next())
        {
            //lager ny printer med navn sqlWriter og skriver ut i HTML format
          PrintWriter sqlWriter = response.getWriter();
          //hvilke columns som skal kalles hva (SQL -> Java)
          String mName = rs.getString("m_name");
          String mDesc = rs.getString("m_description");
          String moduleID = rs.getString("m_id");
          String mDeadline = rs.getString("m_deadline");
          // kjøres for hver row med følgende format:
          sqlWriter.format("<li>Modulnavn: %s<br> Beskrivelse: %s <br> Frist: <b>%s</b><br>", mName, mDesc, mDeadline);
          sqlWriter.format("<form method=\"post\" action=\"ModuleInspector?MID=%s&mName=%s\">", moduleID, mName);
          sqlWriter.println("<input type=\"submit\" value=\"Se læremål\">");
          sqlWriter.println("</form>");
        }
    
      //lukker tilkoblingen
         stmt.close();
             //henter og sender feilmeldinger. Foreløpig går de kun til console i IDE
    }
            catch (Exception e)
            {
              System.err.println("Det oppstod en feil! ");
              System.err.println(e.getMessage());
            }
            %>
            </ul>
            <br> 
                <a href="../index.jsp" class="text-white">Tilbake til forsiden</a>
    </body>
</html>
