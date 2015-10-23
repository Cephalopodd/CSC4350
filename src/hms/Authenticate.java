/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jgreene
 */
public class Authenticate {
    
    //Fake Database lookup
    private static final Map<String, String> USERS = new HashMap<>();
    private static final Map<String, AccessLevel> ACCESS = new HashMap<>();
    
    static {
        USERS.put("Reservations", "password");
        USERS.put("Admin", "password");
        USERS.put("Billing", "password");
        USERS.put("Rooms", "password");
        USERS.put("FrontDesk", "password");
        
        ACCESS.put("Reservations", new AccessLevelBuilder()
                .addReservationsAccess().create());
        ACCESS.put("Billing", new AccessLevelBuilder()
                .addBillingAccess().create());
        ACCESS.put("Rooms", new AccessLevelBuilder()
                .addRoomsAccess().create());
        ACCESS.put("Frontdesk", new AccessLevelBuilder()
                .addFrontDeskAccess().create());
        ACCESS.put("Admin", new AccessLevelBuilder()
                .addFrontDeskAccess()
                .addRoomsAccess()
                .addBillingAccess()
                .addReservationsAccess()
                .addAdminAccess()
                .create());
    }
    
    public static boolean validate(String user, String password){
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }
    
    public static AccessLevel getAccessLevel(String user){
        AccessLevel currentAccessLevel = ACCESS.get(user);
        if ( currentAccessLevel == null) {
            return new AccessLevelBuilder().create();
        } else {
            return currentAccessLevel;  
        }
    }
    
}
