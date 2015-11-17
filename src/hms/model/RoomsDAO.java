package hms.model;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Integer> getRoomList(String condition) {
        ArrayList roomList = new ArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery("select number from room " + condition);
            while (rs.next()) {
                roomList.add(rs.getInt("number"));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return roomList;
    }
    
    public ArrayList<Integer> getTotalRmList() {
        return getRoomList("");
    }

    public ArrayList<Integer> getActiveRmList() {
        return getRoomList("where status not like 'dirty' "
                + "and status not like 'out' and occupied = 0");
    }

    public ArrayList<Integer> getOccupiedRmList() {
        return getRoomList("where occupied = 1");
    }
    
    public ArrayList<Integer> getMaintRmList() {
        return getRoomList("where status like 'out'");
    }

    public ArrayList<Integer> getHkRmList() {
        return getRoomList("where status like 'dirty'");
    }
}