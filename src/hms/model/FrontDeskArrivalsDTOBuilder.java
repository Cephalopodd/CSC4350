/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;


public class FrontDeskArrivalsDTOBuilder {
    private String firstName = "";
    private String lastName = "";
    private String companyName = "";
    private String groupName = "";
    private String confirmation = "";
    private String phoneNumber = "";
    private String arrivalDate = "";
    private String departureDate = "";

    public FrontDeskArrivalsDTOBuilder() {
    }

    public FrontDeskArrivalsDTOBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public FrontDeskArrivalsDTOBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public FrontDeskArrivalsDTOBuilder setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public FrontDeskArrivalsDTOBuilder setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public FrontDeskArrivalsDTOBuilder setConfirmation(String confirmation) {
        this.confirmation = confirmation;
        return this;
    }

    public FrontDeskArrivalsDTOBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public FrontDeskArrivalsDTOBuilder setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public FrontDeskArrivalsDTOBuilder setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public FrontDeskArrivalsDTO createQueryArrivalsDTO() {
        return new FrontDeskArrivalsDTO(firstName, lastName, companyName, groupName, confirmation, phoneNumber, arrivalDate, departureDate);
    }
    
}
