/** 
 * ReservationBuilder class
 * 
 * This builder is used to generate new reservations. 
 * 
 */
package hms;

import java.time.LocalDate;

/**
 *
 * @author Team SLAM
 */
public class ReservationBuilder {
    private Profile guest;
    private PaymentTypeCode paymentType;
    private CreditCard creditCard;
    private LocalDate checkinDate, checkoutDate;
    private RoomTypeCode roomType;
    private RoomRateCode rateCode;
    private int roomNumber, numAdults, numChildren, numRooms;
    private double rate;
    private String comments;
    private String firstName;
    private String lastName;

    public ReservationBuilder() {
    }
        
    /**
     * This method returns a new reservation, and is called after
     * the preceding methods are used to build the reservation.
     * @return Reservation 
     */
    public Reservation createReservation() {
        return new Reservation(guest, paymentType, creditCard, checkinDate,
                checkoutDate, roomType, rateCode, roomNumber, numAdults, 
                numChildren, numRooms, rate, comments, firstName, lastName);
    }
    

    public ReservationBuilder setGuest(Profile guest) {
        this.guest = guest;
        return this;
    }

    public ReservationBuilder setPaymentType(PaymentTypeCode paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public ReservationBuilder setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public ReservationBuilder setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
        return this;
    }

    public ReservationBuilder setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    public ReservationBuilder setRoomType(RoomTypeCode roomType) {
        this.roomType = roomType;
        return this;
    }

    public ReservationBuilder setRateCode(RoomRateCode rateCode) {
        this.rateCode = rateCode;
        return this;
    }

    public ReservationBuilder setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public ReservationBuilder setNumAdults(int numAdults) {
        this.numAdults = numAdults;
        return this;
    }

    public ReservationBuilder setNumChildren(int numChildren) {
        this.numChildren = numChildren;
        return this;
    }

    public ReservationBuilder setNumRooms(int numRooms) {
        this.numRooms = numRooms;
        return this;
    }

    public ReservationBuilder setRate(double rate) {
        this.rate = rate;
        return this;
    }

    public ReservationBuilder setComments(String comments) {
        this.comments = comments;
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

}
