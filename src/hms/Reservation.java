/**
 * Reservation 
 * 
 * This data type contains information related to a guest's
 * scheduled stay at the property. The reservation object is designed to
 * encapsulate this information so that it can be easily sorted or added to
 * table views.
 *
 */
package hms;

import java.time.LocalDate;

/**
 *
 * @author Team SLAM
 */
public class Reservation {

    private Profile guest;
    private PaymentTypeCode paymentType;
    private CreditCard creditCard;
    private LocalDate checkinDate, checkoutDate;
    private RoomTypeCode roomType;
    private RoomRateCode rateCode;
    private double rate;
    private int roomNumber, numAdults, numChildren,numRooms;
    private int confirmationNumber;
    private String comments;
    private String firstName;
    private String lastName;
    
    /**
     * This constructor creates a reservation. Due to the large number
     * of parameters, it is recommended to use the ReservationBuilder
     * to create new Reservations.
     * 
     * @param guest
     * @param paymentType
     * @param creditCard
     * @param checkinDate
     * @param checkoutDate
     * @param roomType
     * @param rateCode
     * @param roomNumber
     * @param numAdults
     * @param numChildren
     * @param numRooms
     * @param rate
     * @param comments
     * @param firstName
     * @param lastName
     * @Contructor to create a reservation
     */
    public Reservation(Profile guest, PaymentTypeCode paymentType, 
            CreditCard creditCard, LocalDate checkinDate, 
            LocalDate checkoutDate, RoomTypeCode roomType,
            RoomRateCode rateCode, int roomNumber, int numAdults,
            int numChildren, int numRooms, double rate, String comments,
            String firstName, String lastName) {
        
        this.guest = guest;
        this.paymentType = paymentType;
        this.creditCard = creditCard;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.roomType = roomType;
        this.rateCode = rateCode;
        this.roomNumber = roomNumber;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
        this.numRooms = numRooms;
        this.rate = rate;
        this.comments = comments;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
    * @return the guest
    */
    public static int generateConfirmationNumber() {
        //TODO
        return 0;
    }
    /**
     * @return the guest
     */
    public Profile getGuest() {
        return guest;
    }

    /**
     * @param guest the guest to set
     */
    public void setGuest(Profile guest) {
        this.guest = guest;
    }

    /**
     * @return the paymentType
     */
    public PaymentTypeCode getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(PaymentTypeCode paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return the creditCard
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @param creditCard the creditCard to set
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * @return the checkinDate
     */
    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    /**
     * @param checkinDate the checkinDate to set
     */
    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    /**
     * @return the checkoutDate
     */
    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * @param checkoutDate the checkoutDate to set
     */
    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     * @return the roomType
     */
    public RoomTypeCode getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(RoomTypeCode roomType) {
        this.roomType = roomType;
    }

    /**
     * @return the rateCode
     */
    public RoomRateCode getRateCode() {
        return rateCode;
    }

    /**
     * @param rateCode the rateCode to set
     */
    public void setRateCode(RoomRateCode rateCode) {
        this.rateCode = rateCode;
    }

    /**
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * @return the numAdults
     */
    public int getNumAdults() {
        return numAdults;
    }

    /**
     * @param numAdults the numAdults to set
     */
    public void setNumAdults(int numAdults) {
        this.numAdults = numAdults;
    }

    /**
     * @return the numChildren
     */
    public int getNumChildren() {
        return numChildren;
    }

    /**
     * @param numChildren the numChildren to set
     */
    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    /**
     * @return the numRooms
     */
    public int getNumRooms() {
        return numRooms;
    }

    /**
     * @param numRooms the numRooms to set
     */
    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the confirmationNumber
     */
    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * @param confirmationNumber the confirmationNumber to set
     */
    public void setConfirmationNumber(int confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
