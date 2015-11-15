/**
 * CreditCard class
 * 
 * This data type is a convenience to encapsulate the information
 * contained in a credit card. Credit cards are kept on file in
 * the folio and reservation, and used at checkout.
 */
package hms.model;

/**
 * @author Team SLAM
 */
public class CreditCard {
    private final String CCNumber;
    private final String code;
    private final String name;
    private final int expMonth;
    private final int expYear;
    private boolean verified = false;

    /**
     * @Constructor creates CreditCard
     * @param CCNumber
     * @param code
     * @param name
     * @param expMonth
     * @param expYear
     */
    public CreditCard(String CCNumber, String code, 
            String name, int expMonth, int expYear) {
        this.CCNumber = CCNumber;
        this.code = code;
        this.name = name;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.verified = verifyCC();
    }
    
    /**
     * This method checks the validity of the credit card
     * Precondition: none
     * Postcondition: returns true if the CC information is valid, and 
     * account is action. returns false if the card cannot be validated.
     * @return boolean 
     */
    public final boolean verifyCC() {
        //TODO
        return true;
    }

}
