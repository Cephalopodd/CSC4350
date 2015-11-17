package hms.model;

import java.time.LocalDate;
import java.sql.*;

public class RoomsDAO {
    
    //Return the total rooms in the database. In this case we have 5 so return 5.
    public int getTotalRoomCount(){
        int totalRoomCount = 23;
        
        return totalRoomCount;
    }
    
    //Return the numbers of rooms not occupied.
    public int getActiveRmCount(){
        int activeRmCount = 23;
        
        return activeRmCount;
    }
    
    //Return the numbers of rooms occupied.
    public int getOccupiedRmCount(){
        int occupiedRmCount = 23;
        
        
        return occupiedRmCount;
    }
    
    //Return the numbers of rooms inactive becuase of maintenance.
    public int getMaintenanceRmCount(){
        int maintenanceRmCount = 33;
        
        return maintenanceRmCount;
    }
    
    //Returns the number of rooms that are being cleaned by House Keeping.
    public int getHkRmCount(){
        int hkRmCount = 23;
        
        return hkRmCount;
    }
    
    
    
}
