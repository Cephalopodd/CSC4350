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
    
    private static final Map<String, String> USERS = new HashMap<String, String>();
    static {
        USERS.put("Reservations", "password");
        USERS.put("Admin", "password");
        USERS.put("Billing", "password");
        USERS.put("Rooms", "password");
        USERS.put("FrontDesk", "password");
    }
    public static boolean validate(String user, String password){
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}
