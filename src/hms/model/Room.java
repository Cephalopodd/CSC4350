/** 
 * Room
 * 
 * This data type contains information related to a guest room.
 * The room object is designed to encapsulate this information
 * so that it can be easily sorted or added to table views.
 * 
 */
package hms.model;

/**
 *
 * @author Team SLAM
 */
public class Room {

    private int number = 0;
    private String type = RoomType.DBLNS;
    private String status = RoomStatus.CLEAN;
    private boolean handicapAccess = false;
    private boolean occupied = false;
    private double cost = 120.00;
    
    public Room(int number) {
        this.number = number;
    }
    
    public Room(int number, String type, String status) {
        this.number = number;
        this.type = type;
        this.status = status;
    }
    
    public Room(int number, String type, String status, boolean handicapAccess, boolean occupied) {
        this.number = number;
        this.type = type;
        this.status = status;
        this.handicapAccess = handicapAccess;
        this.occupied = occupied;
    }

     public Room(int number, String type, String status, boolean handicapAccess, boolean occupied, double cost) {
        this.number = number;
        this.type = type;
        this.status = status;
        this.handicapAccess = handicapAccess;
        this.occupied = occupied;
        this.cost = cost;
    }

    
    
    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the handicapAccess
     */
    public boolean isHandicapAccess() {
        return handicapAccess;
    }

    /**
     * @param handicapAccess the handicapAccess to set
     */
    public void setHandicapAccess(boolean handicapAccess) {
        this.handicapAccess = handicapAccess;
    }

    /**
     * @return the occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * @param occupied the occupied to set
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public double getCost() {
        return cost;
    }
        
}
    
