/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

/**
 *
 * @author admin
 */
public class BillingMenuDTO {

    private Reservation confirmation;
    private FolioBillingCode code;
    private String description;
    private String date;
    private double subTotal;
    private int quantity;

    public BillingMenuDTO() {
        this(null, null, "", "", 0, 0);
    }

    public BillingMenuDTO(Reservation confirmation, FolioBillingCode code, String description, String date, double subTotal, int quantity) {
        this.confirmation = confirmation;
        this.code = code;
        this.description = description;
        this.date = date;
        this.subTotal = subTotal;
        this.quantity = quantity;

    }
       public Reservation getConfirmation() {
        return confirmation;
    }

    /**
     * @param confirmation the confirmation to set
     */
    public void setConfirmation(Reservation confirmation) {
        this.confirmation = confirmation;
    }

    /**
     * @return the code
     */
    public FolioBillingCode getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setLCode(FolioBillingCode code) {
        this.code = code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * @return the SubTotal
     */
    public double getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the SubTotal to set
     */
    public void setSubTotal(double SubTotal) {
        this.subTotal = SubTotal;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
    
}
