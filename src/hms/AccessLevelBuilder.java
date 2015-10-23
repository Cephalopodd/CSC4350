/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;


public class AccessLevelBuilder {
    private boolean roomsAccess = false;
    private boolean frontDeskAccess = false;
    private boolean adminAccess = false;
    private boolean reservationsAccess = false;
    private boolean billingAccess = false;

    public AccessLevelBuilder() {
    }

    public AccessLevelBuilder addRoomsAccess() {
        this.roomsAccess = true;
        return this;
    }

    public AccessLevelBuilder addFrontDeskAccess() {
        this.frontDeskAccess = true;
        return this;
    }

    public AccessLevelBuilder addAdminAccess() {
        this.adminAccess = true;
        return this;
    }

    public AccessLevelBuilder addReservationsAccess() {
        this.reservationsAccess = true;
        return this;
    }

    public AccessLevelBuilder addBillingAccess() {
        this.billingAccess = true;
        return this;
    }

    public AccessLevel create() {
        return new AccessLevel(roomsAccess, frontDeskAccess, adminAccess, 
                reservationsAccess, billingAccess);
    }
    
}
