/** 
 * ProfileBuilder class
 * 
 * This builder is used to generate new profiles. 
 * 
 */
package hms.model;

import junk.Address;

/**
 *
 * @author Team SLAM
 */
public class ProfileBuilder {
    private String firstName;
    private String lastName;
    private String title;
    private String phone;
    private String email;
    private String notes;
    private Address address;
    private boolean VIP;
    private int memberID;

    public ProfileBuilder() {
    }
    
    /**
     * This method returns a new profile, and is called after
     * the preceding methods are used to build the profile.
     * @return Profile 
     */
    public Profile createProfile() {
        return new Profile(firstName, lastName, title, phone, email,
                notes, address, VIP);
    }

    public ProfileBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ProfileBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ProfileBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProfileBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ProfileBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ProfileBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public ProfileBuilder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public ProfileBuilder setVIP(boolean VIP) {
        this.VIP = VIP;
        return this;
    }

    public ProfileBuilder setMemberID(int memberID) {
        this.memberID = memberID;
        return this;
    }
    
}
