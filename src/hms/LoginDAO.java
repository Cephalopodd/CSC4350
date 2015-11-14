/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.util.ArrayList;

/**
 *
 * @author jgreene
 */
public class LoginDAO {
  
    public User authenticateUser(String userName, String password){
        
        User authenticatedUser = null;
        for ( User u : getFakeUsers() ){
            if ( u.getUserName().equals(userName) && u.getPassword().equals(password) ) {
                authenticatedUser = u;
                System.out.println("\nAuthenticated: " + u.getUserName());
            }
        }
        
        return authenticatedUser;
    }

    private ArrayList<User> getFakeUsers() {
        
        ArrayList users = new ArrayList();
    
        users.add(new User("Reservations", "password", MenuType.RESERVATIONS,
                true, false, false, false, false));
        users.add(new User("FrontDesk", "password", MenuType.FRONTDESK,
                false, true, false, false, false));
        users.add(new User("Rooms", "password", MenuType.ROOMS,
                false, false, true, false, false));
        users.add(new User("Billing", "password", MenuType.BILLING,
                false,false,false,true,false));
        users.add(new User("Admin", "password", MenuType.ADMIN,
                true,true,true,true,true));
        
        return users;
    }
      
}
