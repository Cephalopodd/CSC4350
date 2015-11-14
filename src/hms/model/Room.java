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

    private final int roomNumber;
    private final int floorNumber;
    private final boolean handicappedAccesible;
    private final RoomTypeCode type;
    
    private boolean occupied;
    private RoomStatusCode status;
    
    /**
     * @Constructor Creates a room object
     * @param roomNumber
     * @param floorNumber
     * @param handicappedAccesible
     * @param type
     * @param occupied
     * @param status
    */
    public Room(int roomNumber, int floorNumber, boolean handicappedAccesible,
            RoomTypeCode type, boolean occupied, RoomStatusCode status) {
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.handicappedAccesible = handicappedAccesible;
        this.type = type;
        this.occupied = occupied;
        this.status = status;
    }

    /**
     * @return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * @return the floorNumber
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * @return the handicappedAccesible
     */
    public boolean isHandicappedAccesible() {
        return handicappedAccesible;
    }

    /**
     * @return the type
     */
    public RoomTypeCode getType() {
        return type;
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

    /**
     * @return the status
     */
    public RoomStatusCode getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(RoomStatusCode status) {
        this.status = status;
    }

}
