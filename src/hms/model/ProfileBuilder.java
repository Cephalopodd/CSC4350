/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;


public class ProfileBuilder {
    private String firstName = "";
    private String lastName = "";
    private String title = "";
    private String phoneNumber = "";
    private String email = "";
    private String notes = "";
    private String street = "";
    private String apt = "";
    private String city = "";
    private String state = "";
    private String zip = "";
    private String country = "";
    private boolean VIP = false;
    private int memberID;

    public ProfileBuilder() {
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

    public ProfileBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public ProfileBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public ProfileBuilder setApt(String apt) {
        this.apt = apt;
        return this;
    }

    public ProfileBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public ProfileBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public ProfileBuilder setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public ProfileBuilder setCountry(String country) {
        this.country = country;
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

    public Profile createProfile() {
        return new Profile(firstName, lastName, title, phoneNumber, email, notes, street, apt, city, state, zip, country, VIP, memberID);
    }
    
}
