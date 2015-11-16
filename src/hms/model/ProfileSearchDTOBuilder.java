/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;


public class ProfileSearchDTOBuilder {
    private String firstName = "";
    private String lastName = "";
    private String memberID = "";
    private String phoneNumber = "";
    private String email;

    public ProfileSearchDTOBuilder() {
    }

    public ProfileSearchDTOBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ProfileSearchDTOBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ProfileSearchDTOBuilder setMemberID(String memberID) {
        this.memberID = memberID;
        return this;
    }

    public ProfileSearchDTOBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ProfileSearchDTOBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ProfileSearchDTO createProfileSearchDTO() {
        return new ProfileSearchDTO(firstName, lastName, memberID, phoneNumber, email);
    }
    
}
