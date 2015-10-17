/**
 * Profile class
 * 
 * This data type encapsulates the information contained in a
 * guest profile. Profiles are stored independent of reservations, 
 * so the information can be reused on different stays.
 * 
 */
package hms;

/**
 *
 * @author Team SLAM
 */
public class Profile {
    
    private String firstName, lastName, title, phone, email, notes;
    private Address address;
    private boolean VIP;
    private int memberID;

    public Profile(String firstName, String lastName, String title, 
            String phone, String email, String notes, Address address, 
            boolean VIP) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
        this.address = address;
        this.VIP = VIP;
        this.memberID = generateMemberID();
    }

    /**
     * This method is used to generate a unique profile ID. 
     * precondition: A database connection to retrieve existing IDs is 
     * available.
     * postcondition: A unique ID is generated and returned.
     * @return new memberID
     */
    private int generateMemberID() {
        //TODO
        return 0;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the VIP
     */
    public boolean isVIP() {
        return VIP;
    }

    /**
     * @param VIP the VIP to set
     */
    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }

    /**
     * @return the memberID
     */
    public int getMemberID() {
        return memberID;
    }

    /**
     * @param memberID the memberID to set
     */
    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }
    
}
