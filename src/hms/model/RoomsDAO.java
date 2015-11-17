package hms.model;

import java.time.LocalDate;
import java.sql.*;

public class RoomsDAO {

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

    //Return the total rooms in the database. In this case we have 5 so return 5.
    public int getRoomCount(String condition){
        int totalRoomCount = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery("select count (*) from room " + condition);
            totalRoomCount = rs.getInt(1);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return totalRoomCount;
    }
    
    public int getTotalRoomCount(){
        return getRoomCount("");
    }
    
    //Return the numbers of rooms not occupied.
    public int getActiveRmCount(){
        return getRoomCount("where status not like 'dirty' "
                + "and status not like 'out' and occupied = 0");
    }
    
    //Return the numbers of rooms occupied.
    public int getOccupiedRmCount(){
        return getRoomCount("where occupied = 1");
    }
    
    //Return the numbers of rooms inactive becuase of maintenance.
    public int getMaintenanceRmCount(){
        return getRoomCount("where status like 'out'");
    }
    
    //Returns the number of rooms that are being cleaned by House Keeping.
    public int getHkRmCount(){
        return getRoomCount("where status like 'dirty'");
    }

    public boolean isOccupied(int roomNumber) {
        if(roomNumber == 0)
            return true;
        else
            return false;
    }

    public boolean isActive(int roomNumber) {
        if(roomNumber == 1)
            return true;
        else
            return false;
    }

    public boolean isInMaintenance(int roomNumber) {
        if(roomNumber == 2)
            return true;
        else
            return false;
    }
    
    public boolean isInHousekeeping(int roomNumber) {
        if(roomNumber == 3)
            return true;
        else
            return false;
    }
}