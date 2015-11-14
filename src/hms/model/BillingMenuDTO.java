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
    private double amount;
    private int quantity;
    private double subTotal;
    
    private CreditCard CC;

    public BillingMenuDTO() {
        this(null, null, "", "", 0, 0, 0);
    }

    public BillingMenuDTO(Reservation confirmation, FolioBillingCode code, String description, String date, double amount, int quantity, double subTotal) {
        this.confirmation = confirmation;
        this.code = code;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.quantity = quantity;
        this.subTotal = subTotal;

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
     * @return the amount
     */
    public double getamount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setamount(double amount) {
        this.amount = amount;
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
    
    /**
     * @return the subTotal
     */
    public double getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }
    
      public void chargeCard(CreditCard CC, double amount){
        
        this.CC = CC;
        this.amount = amount;
    } 

   
}
