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
    
    private String name;
    private String CCNumber;
    private String code;
    private String type;
    private String exp;

    /**
     * @Constructor creates CreditCard
     * @param CCNumber
     * @param code
     * @param name
     * @param expMonth
     * @param expYear
     */
    public CreditCard(String name, String CCNumber, String type, 
            String code, String exp) {
        this.CCNumber = CCNumber;
        this.code = code;
        this.type = type;
        this.name = name;
        this.exp = exp;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the CCNumber
     */
    public String getCCNumber() {
        return CCNumber;
    }

    /**
     * @param CCNumber the CCNumber to set
     */
    public void setCCNumber(String CCNumber) {
        this.CCNumber = CCNumber;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the expMonth
     */
    public String getExp() {
        return exp;
    }

    /**
     * @param expMonth the expMonth to set
     */
    public void setExp(String exp) {
        this.exp = exp;
    }   
    
}
