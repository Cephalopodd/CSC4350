/**
 * This data transfer object is used in billing
 * menu to populate the guest table
 */

package hms.model;

/**
 *
 * @author team slam
 */
public class BillingMenuDTO {
    int confirmation;
    String firstName;
    String lastName;
    int roomNumber;
    String arrivalDate;
    String departureDate;
    String cc_last4;
    String roomtype;
    String ratecode;

    public BillingMenuDTO(int confirmation, String firstName, 
            String lastName, int roomNumber, String arrivalDate, 
            String departureDate, String cc_last4, 
            String roomtype, String ratecode) {
        this.confirmation = confirmation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomNumber = roomNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.cc_last4 = cc_last4;
        this.roomtype = roomtype;
        this.ratecode = ratecode;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public String getArrivalDate() {
        return arrivalDate;
    }
    
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    
    public String getDepartureDate() {
        return departureDate;
    }
    
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
    
    public String getCc_last4() {
        return cc_last4;
    }
    
    public void setCc_last4(String cc_last4) {
        this.cc_last4 = cc_last4;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getRatecode() {
        return ratecode;
    }

    public void setRatecode(String ratecode) {
        this.ratecode = ratecode;
    }
    
    @Override
    public String toString() {
        return confirmation + firstName + " " + lastName + " " + roomNumber
                + " " + arrivalDate + " " + departureDate;
    }
    
    
}
