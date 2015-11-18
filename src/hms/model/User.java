/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

/**
 *
 * @author jgreene
 */
public class User {

    private String userName;
    private MenuType defaultMenu;
    
    private boolean reservationsAccess;
    private boolean frontDeskAccess;
    private boolean roomsAccess;
    private boolean billingAccess;
    private boolean adminAccess;
    
    public User(String userName, MenuType defaultMenu) {
        this(userName, defaultMenu,false,false,false,false,false);
    }

    public User(String userName, MenuType defaultMenu, boolean reservationsAccess, 
            boolean frontDeskAccess, boolean roomsAccess, 
            boolean billingAccess, boolean adminAccess) {
        this.userName = userName;
        this.reservationsAccess = reservationsAccess;
        this.frontDeskAccess = frontDeskAccess;
        this.roomsAccess = roomsAccess;
        this.billingAccess = billingAccess;
        this.adminAccess = adminAccess;
        if (verifyMenuAccess(defaultMenu)) {
            this.defaultMenu = defaultMenu;
        } else {
            this.defaultMenu = MenuType.NONE;
        }
    }
    
    public User(String userName){
        this.userName = userName;
    }

    private boolean verifyMenuAccess(MenuType defaultMenu) {
        switch (defaultMenu) {
            case ADMIN:
                return hasAdminAccess();
            case ROOMS:
                return hasRoomsAccess();
            case RESERVATIONS:
                return hasReservationsAccess();
            case FRONTDESK:
                return hasFrontDeskAccess();
            case BILLING:
                return hasBillingAccess();
            default:
                break;
        }
        return false;
    }


    @Override
    public String toString() {
        return userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

     public MenuType getDefaultMenu() {
        return defaultMenu;
    }

    public void setDefaultMenu(MenuType defaultMenu) {
        this.defaultMenu = defaultMenu;
    }

    public boolean hasRoomsAccess() {
        return roomsAccess;
    }

    public void setRoomsAccess(boolean roomsAccess) {
        this.roomsAccess = roomsAccess;
    }

    public boolean hasFrontDeskAccess() {
        return frontDeskAccess;
    }

    public void setFrontDeskAccess(boolean frontDeskAccess) {
        this.frontDeskAccess = frontDeskAccess;
    }

    public boolean hasAdminAccess() {
        return adminAccess;
    }

    public void setAdminAccess(boolean adminAccess) {
        this.adminAccess = adminAccess;
    }

    public boolean hasReservationsAccess() {
        return reservationsAccess;
    }

    public void setReservationsAccess(boolean reservationsAccess) {
        this.reservationsAccess = reservationsAccess;
    }

    public boolean hasBillingAccess() {
        return billingAccess;
    }

    public void setBillingAccess(boolean billingAccess) {
        this.billingAccess = billingAccess;
    }

}
