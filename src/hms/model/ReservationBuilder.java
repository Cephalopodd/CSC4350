/**
 * Builds reservation objects
 */
package hms.model;


public class ReservationBuilder {
    private int confirmation = 0;
    private String firstName = "";
    private String lastName = "";
    private String checkinDate = "";
    private String checkoutDate = "";
    private String groupName = "";
    private String companyName = "";
    private String phoneNumber = "";
    private String roomType = "";
    private String status = "Pending";
    private String comments = "";
    private int numberAdults = 1;
    private int numberChildren = 0;
    private double roomRate = 100.00;
    private int roomNumber = 0;
    private int profileID = 0;
    private String creditCardID;

    public ReservationBuilder() {
    }

    public ReservationBuilder setConfirmation(int confirmation) {
        this.confirmation = confirmation;
        return this;
    }

    public ReservationBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ReservationBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ReservationBuilder setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
        return this;
    }

    public ReservationBuilder setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    public ReservationBuilder setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public ReservationBuilder setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ReservationBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ReservationBuilder setRoomType(String roomType) {
        this.roomType = roomType;
        return this;
    }

    public ReservationBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public ReservationBuilder setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public ReservationBuilder setNumberAdults(int numberAdults) {
        this.numberAdults = numberAdults;
        return this;
    }

    public ReservationBuilder setNumberChildren(int numberChildren) {
        this.numberChildren = numberChildren;
        return this;
    }

    public ReservationBuilder setRoomRate(double roomRate) {
        this.roomRate = roomRate;
        return this;
    }

    public ReservationBuilder setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public ReservationBuilder setProfileID(int profileID) {
        this.profileID = profileID;
        return this;
    }

    public ReservationBuilder setCreditCardID(String creditCardID) {
        this.creditCardID = creditCardID;
        return this;
    }

    public Reservation createReservation() {
        return new Reservation(confirmation, firstName, lastName, checkinDate, checkoutDate, groupName, companyName, phoneNumber, roomType, status, comments, numberAdults, numberChildren, roomRate, roomNumber, profileID, creditCardID);
    }
    
}
