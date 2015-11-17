package hms.model;

import java.util.ArrayList;

public class NewUserDAO {
    
    boolean resAccess, frontAccess, billAccess, roomsAccess, adminAccess;
    
    public void createAdministratorUser(String userName, String pw){
        
        resAccess = true; 
        frontAccess = true; 
        billAccess = true; 
        roomsAccess = true; 
        adminAccess = true;
        
        //Create user in the database.
        
    }
    
    public void createManagerUser(String userName, String pw){
        
        resAccess = true; 
        frontAccess = true; 
        billAccess = true; 
        roomsAccess = true; 
        adminAccess = false;
        
        //Create user in the database.
        
    }

    public void createEmployeeUser(String userName, String pw) {
        resAccess = true; 
        frontAccess = true; 
        billAccess = true; 
        roomsAccess = true; 
        adminAccess = false;
        
        //Create user in the database.
        
    }

    public boolean unTaken(String userName) {
        //Check on the data base if this userName exsist.
        //If it does then return true else return false.
        
        return true;
    }

    public void resetPassword(String userName, String pw) {
        //reset password for the user name with the one provided.
    }
    
    public ArrayList<String> getUsersDb(){
        ArrayList user = new ArrayList();
        user.add("Thug Life");
        user.add("Pikachu");
        
        return user;
    }
    
}
