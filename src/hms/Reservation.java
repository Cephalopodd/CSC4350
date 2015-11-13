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

/**
 *
 * @author Team SLAM
 */
public class Reservation {

    //Required
    private int confirmation;
    private String firstName;
    private String lastName;
    private String checkinDate;
    private String checkoutDate;
    private String groupName;
    private String companyName;
    private String phoneNumber;
    private String roomType;
    private String status;
    private String comments;
    private int numberAdults;
    private int numberChildren;
    private double roomRate;
    
    //Reference other tables
    private int roomNumber;
    private int profileID;
    private int creditCardID;

    public Reservation(int confirmation, String firstName, String lastName, String checkinDate, String checkoutDate, String groupName, String companyName, String phoneNumber, String roomType, String status, String comments, int numberAdults, int numberChildren, double roomRate, int roomNumber, int profileID, int creditCardID) {
        this.confirmation = confirmation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.groupName = groupName;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.roomType = roomType;
        this.status = status;
        this.comments = comments;
        this.numberAdults = numberAdults;
        this.numberChildren = numberChildren;
        this.roomRate = roomRate;
        this.roomNumber = roomNumber;
        this.profileID = profileID;
        this.creditCardID = creditCardID;
    }

    /**
     * @return the confirmation
     */
    public int getConfirmation() {
        return confirmation;
    }

    /**
     * @param confirmation the confirmation to set
     */
    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
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

    /**
     * @return the checkinDate
     */
    public String getCheckinDate() {
        return checkinDate;
    }

    /**
     * @param checkinDate the checkinDate to set
     */
    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    /**
     * @return the checkoutDate
     */
    public String getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * @param checkoutDate the checkoutDate to set
     */
    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
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
     * @return the numberAdults
     */
    public int getNumberAdults() {
        return numberAdults;
    }

    /**
     * @param numberAdults the numberAdults to set
     */
    public void setNumberAdults(int numberAdults) {
        this.numberAdults = numberAdults;
    }

    /**
     * @return the numberChildren
     */
    public int getNumberChildren() {
        return numberChildren;
    }

    /**
     * @param numberChildren the numberChildren to set
     */
    public void setNumberChildren(int numberChildren) {
        this.numberChildren = numberChildren;
    }

    /**
     * @return the roomRate
     */
    public double getRoomRate() {
        return roomRate;
    }

    /**
     * @param roomRate the roomRate to set
     */
    public void setRoomRate(double roomRate) {
        this.roomRate = roomRate;
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
     * @return the profileID
     */
    public int getProfileID() {
        return profileID;
    }

    /**
     * @param profileID the profileID to set
     */
    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    /**
     * @return the creditCardID
     */
    public int getCreditCardID() {
        return creditCardID;
    }

    /**
     * @param creditCardID the creditCardID to set
     */
    public void setCreditCardID(int creditCardID) {
        this.creditCardID = creditCardID;
    }
    
    
    
}
