/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.util.Objects;

/**
 *
 * @author jgreene
 */
public class User {

    private String login;
    private String password;
    private AccessLevel accessLevel;
    private MenuType defaultMenu;

    public User(String login, String password, AccessLevel accessLevel, MenuType defaultMenu) {
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
        if (verifyMenuAccess(defaultMenu)) {
            this.defaultMenu = defaultMenu;
        } else {
            this.defaultMenu = MenuType.NONE;
        }
    }

    private boolean verifyMenuAccess(MenuType defaultMenu) {
        switch (defaultMenu) {
            case ADMIN:
                return getAccessLevel().hasAdminAccess();
            case ROOMS:
                return getAccessLevel().hasRoomsAccess();
            case RESERVATIONS:
                return getAccessLevel().hasReservationsAccess();
            case FRONTDESK:
                return getAccessLevel().hasFrontDeskAccess();
            case BILLING:
                return getAccessLevel().hasBillingAccess();
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        User otherUser = (User) other;
        
        return this.login.equals(otherUser.login)
                && this.password.equals(otherUser.password)
                && this.accessLevel == otherUser.getAccessLevel()
                && this.defaultMenu == otherUser.getDefaultMenu();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.login);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + Objects.hashCode(this.accessLevel);
        hash = 67 * hash + Objects.hashCode(this.defaultMenu);
        return hash;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * @return the defaultMenu
     */
    public MenuType getDefaultMenu() {
        return defaultMenu;
    }

    /**
     * @param defaultMenu the defaultMenu to set
     */
    public void setDefaultMenu(MenuType defaultMenu) {
        this.defaultMenu = defaultMenu;
    }

}
