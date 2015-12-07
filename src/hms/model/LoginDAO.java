/**
 * This Data Access Object is used by the login menu
 * to query the database on login
 */
package hms.model;

import java.sql.*;

/**
 *
 * @author team slam
 */
public class LoginDAO {
    
    Connection c;
    Statement stmt;
    ResultSet rs;
  
    public User authenticateUser(String userName, Integer pwHash){
        
        User authenticatedUser = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            rs = stmt.executeQuery("select * from user where login like '" + userName + "'");
            if (rs.next()) {
                if (pwHash == rs.getInt("pw")) {
                    MenuType defMenu;
                    switch (rs.getString("menu")) {
                        case "res":
                            defMenu = MenuType.RESERVATIONS; break;
                        case "front":
                            defMenu = MenuType.FRONTDESK; break;
                        case "rooms":
                            defMenu = MenuType.ROOMS; break;
                        case "bill":
                            defMenu = MenuType.BILLING; break;
                        case "admin":
                            defMenu = MenuType.ADMIN; break;
                        default:
                            defMenu = MenuType.NONE;
                    }
                    authenticatedUser = new User(
                        rs.getString("login"), defMenu, rs.getBoolean("res_access"), 
                            rs.getBoolean("front_access"), rs.getBoolean("rooms_access"), 
                            rs.getBoolean("bill_access"), rs.getBoolean("admin_access"));
                }
            }
            
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return authenticatedUser;
    }

    /**
     * Closes DB connections
     */
    private void closeAll() {
        try {
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
   /**
    * Returns a match on user name and user password
    * @param userName
    * @param pwHash
    * @return 
    */
    public boolean matchPassword(String userName, Integer pwHash){
        
        User authenticatedUser = null;
        boolean passwordMatched = true;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            rs = stmt.executeQuery("select * from user where login like '" + userName + "'");
            if (rs.next()) {
                if (pwHash == rs.getInt("pw")) {
                    passwordMatched = true;
                }else{
                    passwordMatched = false;
                }
            }
            
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        
        return passwordMatched;
        
    }
}
