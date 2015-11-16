package hms.model;

import java.time.LocalDate;
import java.sql.*;

public class AdminDAO {

    Connection c;
    Statement stmt;
    ResultSet rs;
    
    //Query that returns the an int value of check outs that are schedule for the day.
    //Ex. If there are 10 check out schedules for %Todays Date%, the query will return a value of 10.
    public int getCheckOut(LocalDate today){
        int checkOutToday = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery(
                    "select count (*) from reservation "
                    + "where status like 'CHECKEDOUT' and "
                    + "dep like '" + today.toString() + "'");
            rs.next();
            checkOutToday = rs.getInt(1);
            System.out.println("Check Outs: " + checkOutToday);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return checkOutToday;
    }
    
    //Query that returns the an int value of check ins that are schedule for the day.
    //Ex. If there are 10 check in schedules for %Todays Date%, the query will return a value of 10.
    public int getCheckIn(LocalDate today){
        int checkInToday = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery(
                    "select count (*) from reservation "
                    + "where status like 'CHECKEDIN' and "
                    + "arr like '" + today.toString() + "'");
            rs.next();
            checkInToday = rs.getInt(1);
            System.out.println("Check Ins: " + checkInToday);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return checkInToday;
    }
    
    //Query that accesses the database and get toatalSales of the current day.
    //Ex. If the total sales is 123.33 for %Todays Date%, query will return 123.33.
    //Let me know if it is easier to return string vs double or int.
    public double getDailySales(LocalDate date){
        double sales = 0.00;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery(
                    "select sum (amount) from charge "
                    + "where date like '" + date.toString() + "'");
            rs.next();
            sales = rs.getDouble(1);
            System.out.println("Total sales: " + sales);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return sales;
    }
    
    private void closeAll() {
        try {
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}