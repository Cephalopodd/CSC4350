/**
 * FolioCharge class
 * 
 * This data type is a convenience to encapsulate the information
 * contained in a single charge. Charges are typically posted to
 * guest folios, and contain billable charges to the room.
 * 
 */
package hms.model;

import java.time.LocalDate;

/**
 * 
 * @author Team SLAM
 */
public class FolioCharge {
    
    
    
    private FolioBillingCode code;
    private String description;
    private double amount;
    private String date;
    private CreditCard CC;

    public FolioCharge(FolioBillingCode code, String description, double amount, String date) {
        this.code = code;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public FolioBillingCode getCode() {
        return code;
    }

    public void setCode(FolioBillingCode code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
  public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDate.now().toString();
    }
    

    
}