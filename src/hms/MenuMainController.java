/**
 * MainMenuController 
 * 
 * This class is used to handle the GUI layout for the main window. This
 * layout will allow the user to switch between the available Menus:
 * Front Desk, Administration, Rooms Management, Billing, and Reservations.
 * 
 */
package hms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Team SLAM
 */
public class MenuMainController implements Initializable {
    
    private BorderPane mainMenuPane;
    private BorderPane displayPane;
    private Button btnExit, btnFrontDesk, btnRooms, 
            btnReservations, btnAdmin, btnBilling;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * This method exits the program back to the login screen.
     * precondition: none
     * postcondition: The user is logged out, and the GUI is returned to
     * the login window.
     * @param event 
     */
    private void onClickExit(ActionEvent event) { 
        // TODO
    }
    
    /**
     * This method creates the front desk interface, and assigns
     * it to the displayPane
     * preconditions: none
     * postconditions: the displayPane is updated
     * @param event 
     */
    private void onClickFrontDesk(ActionEvent event) {
        // TODO
    }
    
    /**
     * This method creates the rooms management interface, and assigns
     * it to the displayPane
     * preconditions: none
     * postconditions: the displayPane is updated
     * @param event 
     */
    private void onClickRooms(ActionEvent event) {
        // TODO
    }

    /**
     * This method creates the administration interface, and assigns
     * it to the displayPane
     * preconditions: none
     * postconditions: the displayPane is updated
     * @param event 
     */
    private void onClickAdmin(ActionEvent event) {
        // TODO
    }
    
    /**
     * This method creates the reservations interface, and assigns
     * it to the displayPane
     * preconditions: none
     * postconditions: the displayPane is updated
     * @param event 
     */
    private void onClickReservations(ActionEvent event) {
        // TODO
    }
    
    /**
     * This method creates the billing interface, and assigns
     * it to the displayPane
     * preconditions: none
     * postconditions: the displayPane is updated
     * @param event 
     */
    private void onClickBilling(ActionEvent event) {
        // TODO
    }
    
}
