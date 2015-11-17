package hms.model;

import java.sql.*;

public class NewUserDAO {
    
    boolean resAccess, frontAccess, billAccess, roomsAccess, adminAccess;
    Connection c;
    Statement stmt;
    ResultSet rs;
  
    private void closeAll() {
        try {
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
        
    private void createUser(String username, int pw, String permissions) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            stmt.executeUpdate("insert into user values (" + 
                    username + "," + pw + permissions);
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }

    }
    
    
    public void createAdministratorUser(String userName, String pw){
        createUser(userName, pw.hashCode(), ",1,1,1,1,1");
    }
    
    public void createManagerUser(String userName, String pw){
        createUser(userName, pw.hashCode(), ",1,1,1,1,0");
    }

    public void createEmployeeUser(String userName, String pw) {
        createUser(userName, pw.hashCode(), ",1,1,1,1,0");
    }

    public boolean unTaken(String userName) {
        boolean result = false;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery("select login from user where login like " + userName);
            result = !rs.next();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return result;
    }

    public void resetPassword(String userName, String pw) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            stmt.executeUpdate("update user set pw = " + pw.hashCode() 
                    + " where login like " + userName);
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
    }
}
