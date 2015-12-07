/**
 * This interface is for creating sub menus
 * It provides methods for injecting
 * dependency links to main menu controller
 * and to the current user
 * 
 */
package hms;

import hms.model.User;

/**
 *
 * @author Team Slam
 */
public interface SubMenu {
    
    public void setSubMenuParent(MainMenuController main);
    public void setUser(User e);
  
}
