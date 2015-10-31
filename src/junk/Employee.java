/**
 * Employee class
 * 
 * This data type contains information related to an employee. 
 * 
 */
package junk;

import hms.AccessLevel;
import hms.Address;
import java.util.List;

/**
 *
 * @author Team SLAM
 */
public class Employee {
    
    private final int employeeID;
    private final String firstName;
    private final String lastName;
    private String phoneNumber;
    private String email;    
    private Address address;
    private AccessLevel accessLevel;
    private String password;
    private final String login;

    
    
    /**
     * @param login
     * @param password
     * @Contructor creates Employee
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param email
     * @param accessLevel 
     */
    public Employee(String login, String password, AccessLevel accessLevel,
            String firstName, String lastName, String phoneNumber, String email,
            Address address) {
        this.employeeID = generateEmployeeID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.login = login;
        this.password = "";       
        this.accessLevel = accessLevel;
    }

     /**
     * This method is used to generate a unique Employee ID. 
     * precondition: A database connection to retrieve existing IDs is 
     * available.
     * postcondition: A unique ID is generated and returned.
     * @return EmployeeID
     */
    private int generateEmployeeID() {
        //TODO
        return 0;
    }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the accessLevel
     */
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

   /**
     * @param accessLevel the accessLevel to set
     */
    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
