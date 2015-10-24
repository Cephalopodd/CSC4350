/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jgreene
 */
public class Authenticate {
    
    //Generate Fake Users Database
    private static final List<User> users= new ArrayList<>();
    
    static {
        users.add( new User("Reservations", "password", 
                new AccessLevelBuilder().addReservationsAccess().create(), 
                MenuType.RESERVATIONS));
        users.add( new User("FrontDesk", "password", 
                new AccessLevelBuilder().addFrontDeskAccess().create(), 
                MenuType.FRONTDESK));
        users.add( new User("Billing", "password", 
                new AccessLevelBuilder().addBillingAccess().create(), 
                MenuType.BILLING));
        users.add( new User("Rooms", "password", 
                new AccessLevelBuilder().addRoomsAccess().create(), 
                MenuType.ROOMS));
        users.add( new User("Admin", "password", 
                new AccessLevelBuilder()
                        .addReservationsAccess()
                        .addFrontDeskAccess()
                        .addBillingAccess()
                        .addRoomsAccess()
                        .addAdminAccess()
                        .create(), 
                MenuType.ADMIN));
    }
    
    public static User validateUser(String login, String password){
        System.out.println("Testing login, pass: " + login + password);
        User validatedUser = null;
        for ( User u : users ){
            System.out.println(u.getLogin() + " " + u.getPassword());
            if ( u.getLogin().equals(login) && u.getPassword().equals(password) ) {
                validatedUser = u;
            }
        }
        return validatedUser;
    }
    
}
