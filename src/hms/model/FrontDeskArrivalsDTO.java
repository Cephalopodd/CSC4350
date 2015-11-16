/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

import java.time.LocalDate;

/**
 *
 * @author jgreene
 */
public class FrontDeskArrivalsDTO {

    private String firstName;
    private String lastName;
    private String companyName;
    private String groupName;
    private String confirmation;
    private String phoneNumber;
    private String arrivalDate;
    private String departureDate;

    public FrontDeskArrivalsDTO() {
        this("","","","","","","","");
    }
    
    public FrontDeskArrivalsDTO(String firstName, String lastName, String companyName, String groupName, String confirmation, String phoneNumber, String arrivalDate, String departureDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.groupName = groupName;
        this.confirmation = confirmation;
        this.phoneNumber = phoneNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
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
     * @return the confirmation
     */
    public String getConfirmation() {
        return confirmation;
    }

    /**
     * @param confirmation the confirmation to set
     */
    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
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
     * @return the arrivalDate
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /**
     * @param arrivalDate the arrivalDate to set
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * @return the departureDate
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * @param departureDate the departureDate to set
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
    
}



