/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

/**
 *
 * @author jgreene
 */
public class AccessLevel {
    
    private boolean roomAccess;
    private boolean frontDeskAccess;
    private boolean adminAccess;
    private boolean reservationsAccess;
    private boolean billingAccess;

    /**
     *
     * @param roomAccess
     * @param frontDeskAccess
     * @param adminAccess
     * @param reservationsAccess
     * @param billingAccess
     */
    public AccessLevel(boolean roomAccess, boolean frontDeskAccess, boolean adminAccess,
            boolean reservationsAccess, boolean billingAccess) {
        this.roomAccess = roomAccess;
        this.frontDeskAccess = frontDeskAccess;
        this.adminAccess = adminAccess;
        this.reservationsAccess = reservationsAccess;
        this.billingAccess = billingAccess;
    }

    /**
     * @return the roomAccess
     */
    public boolean isRoomAccess() {
        return roomAccess;
    }

    /**
     * @param roomAccess the roomAccess to set
     */
    public void setRoomAccess(boolean roomAccess) {
        this.roomAccess = roomAccess;
    }

    /**
     * @return the frontDeskAccess
     */
    public boolean isFrontDeskAccess() {
        return frontDeskAccess;
    }

    /**
     * @param frontDeskAccess the frontDeskAccess to set
     */
    public void setFrontDeskAccess(boolean frontDeskAccess) {
        this.frontDeskAccess = frontDeskAccess;
    }

    /**
     * @return the adminAccess
     */
    public boolean isAdminAccess() {
        return adminAccess;
    }

    /**
     * @param adminAccess the adminAccess to set
     */
    public void setAdminAccess(boolean adminAccess) {
        this.adminAccess = adminAccess;
    }

    /**
     * @return the reservationsAccess
     */
    public boolean isReservationsAccess() {
        return reservationsAccess;
    }

    /**
     * @param reservationsAccess the reservationsAccess to set
     */
    public void setReservationsAccess(boolean reservationsAccess) {
        this.reservationsAccess = reservationsAccess;
    }

    /**
     * @return the billingAccess
     */
    public boolean isBillingAccess() {
        return billingAccess;
    }

    /**
     * @param billingAccess the billingAccess to set
     */
    public void setBillingAccess(boolean billingAccess) {
        this.billingAccess = billingAccess;
    }

}
