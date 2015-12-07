/**
 * Controller for the main menu bar
 */
package hms;

import hms.model.User;
import hms.model.MenuType;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * MainMenuController class
 *
 * @author team slam
 */
public class MainMenuController implements Initializable {
    
    private HMS HMSapp;
    private User user;
    private final HashMap<Enum, Node> subMenus = new HashMap<>();
    private final HashMap<Enum, SubMenu> subMenuControllers = new HashMap<>();
      
    @FXML
    private BorderPane mainMenuPane;
    @FXML
    private Button btnReservations;
    @FXML
    private Button btnFrontDesk;
    @FXML
    private Button btnRooms;
    @FXML
    private Button btnBilling;
    @FXML
    private Button btnAdmin;
    @FXML
    private Label lblLoggedInUser;
    @FXML
    private Button btnExit;
    @FXML
    private StackPane displayPane;
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // not used
    }

    /**
     * Reservation button click
     * @param event 
     */
    @FXML
    private void onClickReservations(ActionEvent event) {
        displaySubMenu(MenuType.RESERVATIONS);
    }

    /**
     * Front Desk button click
     * @param event 
     */
    @FXML
    private void onClickFrontDesk(ActionEvent event) {
        FrontDeskMenuController c =
                (FrontDeskMenuController) subMenuControllers.get(MenuType.FRONTDESK);
        c.onClickArrivals(event);
        displaySubMenu(MenuType.FRONTDESK);
    }

    /**
     * Rooms button click
     * @param event 
     */
    @FXML
    private void onClickRooms(ActionEvent event) {
        RoomsMenuController c = 
                    (RoomsMenuController) subMenuControllers.get(MenuType.ROOMS);
        c.updateView();
        displaySubMenu(MenuType.ROOMS);
    }

    /**
     * Billing button click
     * @param event 
     */
    @FXML
    private void onClickBilling(ActionEvent event) {
        BillingMenuController c =
                (BillingMenuController) subMenuControllers.get(MenuType.BILLING);
        c.radioActive.setSelected(true);
        c.updateActiveGuests();
        displaySubMenu(MenuType.BILLING);
    }

    /**
     * Admin button click
     * @param event 
     */
    @FXML
    private void onClickAdmin(ActionEvent event) {
        displaySubMenu(MenuType.ADMIN);
    }

    /**
     * Exit button click
     * @param event 
     */
    @FXML
    private void onClickExit(ActionEvent event) {
        HMSapp.doLogout();
    }

    /**
     * Injects link to HMS app
     * @param app 
     */
    void setHMSApp(HMS app) {
        this.HMSapp = app;
    }

    /**
     * Injects link to current user and sets access rights
     * @param user 
     */
    void setUser(User user) {
        
        //Set logged in User
        this.user = user;
        
        //Set Logged In User Label
        lblLoggedInUser.setText(user.getUserName());

        //Set active Buttons
        btnAdmin.setDisable(!user.hasAdminAccess());
        btnRooms.setDisable(!user.hasRoomsAccess());
        btnFrontDesk.setDisable(!user.hasFrontDeskAccess());
        btnBilling.setDisable(!user.hasBillingAccess());
        btnReservations.setDisable(!user.hasReservationsAccess());
    }

    /**
     * Loads the sub menus
     */
    public void loadSubMenus(){
        try {
            addSubMenu(MenuType.FRONTDESK, "FrontDeskMenu.fxml");
            addSubMenu(MenuType.BILLING, "BillingMenu.fxml");
            addSubMenu(MenuType.RESERVATIONS, "ReservationsProfileMenu.fxml");
            addSubMenu(MenuType.ROOMS, "RoomsMenu.fxml");
            addSubMenu(MenuType.ADMIN, "AdminMenu.fxml");
            addSubMenu(MenuType.PROFILE, "ProfileMenu.fxml");
        } catch (Exception e) {
            System.out.println("Error loading menus:" + e.getMessage());
        }
    }
    
    /**
     * Creates the sub menus
     * @param menu
     * @param fxml 
     */
    private void addSubMenu(MenuType menu, String fxml) {
        Parent loadScreen;
        SubMenu submenu = null;
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(fxml));
            loadScreen = (Parent) myLoader.load();
            submenu = ((SubMenu) myLoader.getController());
            submenu.setSubMenuParent(this);
            submenu.setUser(user);
            
        } catch (Exception ex) {
            String msg = "Error Loading menu " + menu.toString() + " from " + fxml;
            System.out.println(msg);
            loadScreen = new BorderPane();
            ( (BorderPane) loadScreen).setCenter(new Label(msg));
        }
        subMenus.put(menu, loadScreen);
        subMenuControllers.put(menu,submenu);
        
    }
    
    /**
     * Sets the default menu on login
     */
    public void setDefaultSubMenu(){
        
        switch (user.getDefaultMenu()) {
            case ADMIN:
                btnAdmin.fire();
                btnAdmin.requestFocus();
                break;
            case ROOMS:
                btnRooms.fire();
                btnRooms.requestFocus();
                break;
            case FRONTDESK:
                btnFrontDesk.fire();
                btnFrontDesk.requestFocus();
                break;
            case BILLING:
                btnBilling.fire();
                btnBilling.requestFocus();
                break;
            case RESERVATIONS:
                btnReservations.fire();
                btnReservations.requestFocus();
                break;
            default :
                break;
        }
    }
    
    /**
     * Adds fade transition to menu switches
     * @param subMenu 
     */
    public void displaySubMenu(MenuType subMenu){
        FadeTransition ft0 = new FadeTransition(Duration.millis(250), displayPane);
        ft0.setFromValue(1.0);
        ft0.setToValue(0.0);
        ft0.play();
        displayPane.getChildren().setAll(subMenus.get(subMenu));
        FadeTransition ft1 = new FadeTransition(Duration.millis(500), displayPane);
        ft1.setFromValue(0.0);
        ft1.setToValue(1.0);
        ft1.play();
    }

}
