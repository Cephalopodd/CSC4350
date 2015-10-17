/**
 * Folio class
 * 
 * This data type is a convenience to encapsulate the information
 * contained in guest folio. Folios contain information about a
 * guest's stay, and billable charges to the room.
 * 
 */
package hms;

import javafx.collections.ObservableList;

/**
 *
 * @author Team SLAM
 */
public class Folio {
    private Profile guest;
    private CreditCard ccOnFile;
    private double cashDeposit;
    private Room room;
    private ObservableList<FolioCharge> charges;
    private boolean settled;

    /**
     * @Constructor creates Folio
     * @param cashDeposit
     * @param guest
     * @param ccOnFile
     * @param room
     */
    public Folio(Profile guest, CreditCard ccOnFile, Room room, 
            double cashDeposit) {
        this.guest = guest;
        this.ccOnFile = ccOnFile;
        this.cashDeposit = cashDeposit;
    }
    
    /**
     * This method adds a charge to the guests folio
     * precondition: A valid charge is submitted
     * postcondition: The charge is added the chargeList
     * @param c
     */
    public void addCharge(FolioCharge c){
        //TODO
    }
    
    /**
     * This method deletes a charge from the guests folio
     * precondition: A valid charge is submitted
     * postcondition: The charge is deleted from the chargeList
     * @param c
     */
    public void delCharge(FolioCharge c){
        //TODO
    }

    /**
     * @return the guest
     */
    public Profile getGuest() {
        return guest;
    }

    /**
     * @param guest the guest to set
     */
    public void setGuest(Profile guest) {
        this.guest = guest;
    }

    /**
     * @return the ccOnFile
     */
    public CreditCard getCcOnFile() {
        return ccOnFile;
    }

    /**
     * @param ccOnFile the ccOnFile to set
     */
    public void setCcOnFile(CreditCard ccOnFile) {
        this.ccOnFile = ccOnFile;
    }

    /**
     * @return the cashDeposit
     */
    public double getCashDeposit() {
        return cashDeposit;
    }

    /**
     * @param cashDeposit the cashDeposit to set
     */
    public void setCashDeposit(double cashDeposit) {
        this.cashDeposit = cashDeposit;
    }

    /**
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * @return the charges
     */
    public ObservableList<FolioCharge> getCharges() {
        return charges;
    }

    /**
     * @return the settled
     */
    public boolean isSettled() {
        return settled;
    }

    /**
     * @param settled the settled to set
     */
    public void setSettled(boolean settled) {
        this.settled = settled;
    }

}
