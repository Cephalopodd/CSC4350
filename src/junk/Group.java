/**
 * Group class
 * 
 * This data type is used to define a group. The group object is designed to
 * encapsulate this information so that it can be easily sorted or added to
 * table views. It is used to identify reservations that are related.
 */
package junk;

/**
 *
 * @author Team SLAM
 */
public class Group {
    private final int groupId;
    private String groupName;
    private Address address;
    private String contactName;
    private String contactNumber;

     /**
     * @param groupName
     * @param address
     * @param contactName
     * @param contactNumber
     * @Constructor creates a new Group
     */
    public Group(String groupName, Address address, String contactName,
            String contactNumber) {
        this.groupName = groupName;
        this.address = address;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.groupId = generateGroupID();
    }

    /**
     * This method is used to generate a unique group ID. 
     * precondition: A database connection to retrieve existing IDs is 
     * available.
     * postcondition: A unique ID is generated and returned.
     * @return new GroupID
     */
    private int generateGroupID() {
        //TODO
        return 0;
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
     * @return the groupId
     */
    public int getGroupId() {
        return groupId;
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
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
