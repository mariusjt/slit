package LearningUtil;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NewLearning {
    Connection conn;
    Statement stmt;

    public void logIn(PrintWriter out) {

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/slit", "slit", "verystrong");

            stmt = conn.createStatement();
        }
        catch (SQLException ex) {
            out.println("Error: " + ex);
        }
    }

    public void newLearning(String lg_string, int fk_m_id, PrintWriter out){
        String strInsert = "INSERT INTO LEARNINGGOAL (lg_string, fk_m_id) values ('" +lg_string +"', '" +fk_m_id +"')";
        out.println(strInsert);

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(strInsert);
        }
        catch (SQLException ex) {
            out.println("SQL sier: " +ex);
        }
    }

    public void commit() {

        try {
            conn.commit();
        } // end try

        catch (SQLException ex) {
            System.out.println("Ikke close DB " +ex);

        }

    }
    public void close() {

        try {
            conn.close();

        }
        catch (SQLException ex) {
            System.out.println("Ikke lukke DB " +ex);
        }
    }
}

