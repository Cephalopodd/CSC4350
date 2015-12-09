/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

/**
 *
 * @author jgreene
 */
public class BillingMenuDTO {
    int confirmation;
    String firstName;
    String lastName;
    int roomNumber;
    String arrivalDate;
    String departureDate;

    public BillingMenuDTO(int confirmation, String firstName, 
            String lastName, int roomNumber, String arrivalDate, 
            String departureDate) {
        this.confirmation = confirmation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomNumber = roomNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
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
    
    @Override
    public String toString() {
        return confirmation + firstName + " " + lastName + " " + roomNumber
                + " " + arrivalDate + " " + departureDate;
    }
    
    
}
