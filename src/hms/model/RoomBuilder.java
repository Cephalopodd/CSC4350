/**
 * Builds a room
 */
package hms.model;


public class RoomBuilder {
    private int number = 0;
    private String type = RoomType.DNN;
    private String status = "";
    private boolean handicapAccess = false;
    private boolean occupied = false;
    private double cost;

    public RoomBuilder() {
    }

    public RoomBuilder setNumber(int number) {
        this.number = number;
        return this;
    }

    public RoomBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public RoomBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public RoomBuilder setHandicapAccess(boolean handicapAccess) {
        this.handicapAccess = handicapAccess;
        return this;
    }

    public RoomBuilder setOccupied(boolean occupied) {
        this.occupied = occupied;
        return this;
    }

    public RoomBuilder setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public Room createRoom() {
        return new Room(number, type, status, handicapAccess, occupied, cost);
    }
    
}
