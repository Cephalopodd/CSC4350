/**
 * FolioCharge class
 * 
 * This data type is a convenience to encapsulate the information
 * contained in a single charge. Charges are typically posted to
 * guest folios, and contain billable charges to the room.
 * 
 */
package hms;

/**
 * 
 * @author Team SLAM
 */
public class FolioCharge {
    private final FolioBillingCode code;
    private final double amount;
    
    /** 
     * @Constructor Creates a charge 
     * @param code
     * @param amount
     */
    public FolioCharge(FolioBillingCode code, double amount) {
        this.code = code;
        this.amount = amount;
    }
    
}
