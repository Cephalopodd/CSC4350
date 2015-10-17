/**
 * MenuFrontDeskController class
 * 
 * This class is designed to handle the main front desk menu GUI. The
 * front desk menu is designed for agents working at the front desk of the 
 * hotel to list arriving reservations, and allow the agent to handle the 
 * check in and check out events for guests with reservations and for 
 * walk up guests.
 * 
 */
package hms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Team SLAM
 */
public class MenuFrontDeskController implements Initializable {
    
    private Parent root;  
    private GridPane frontDeskMenuPane;
    private Button btnSearch, btnClear, btnWalkIn, btnProfile, btnEdit,
            btnCheckIn;
    private TextField txtFirstName, txtLastName, txtConfNum,
            txtMemberID, txtCompany;
    private TableView<Reservation> tblReservations;
    private ObservableList<Reservation> reservationsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * This method searches the reservations list using the criteria
     * entered into the text boxes.
     * precondition: none
     * postcondition: The reservationsList is modified to include entries
     * matching the search criteria.
     * @param event 
     */
    private void onClickSearch(ActionEvent event) { 
        // TODO
    }
    
    /**
     * This method clears the search criteria.
     * preconditions: none
     * postconditions: the text boxes in the GUI are cleared to default
     * values.
     * @param event 
     */
    private void onClickClear(ActionEvent event) {
        // TODO
    }
    
    /**
     * This method allows the front desk to check in a walk in customer.
     * precondition: none
     * postcondition: A guest reservation is created and the guest is
     * checked in.
     * @param event 
     */
    private void onClickWalkIn(ActionEvent event) { 
        // TODO
    }

    /**
     * This method allows the front desk to edit the 
     * selected reservation.
     * precondition: none
     * postcondition: the guest reservation will be edited.
     * @param event 
     */
    private void onClickEdit(ActionEvent event) { 
        // TODO
    }
    
    /**
     * This method allows the front desk to edit the profile of the 
     * selected reservation.
     * precondition: none
     * postcondition: the guest profile will be edited.
     * @param event 
     */
     private void onClickProfile(ActionEvent event) { 
        // TODO
    }

    /**
     * This method is used when the guest arrives and is ready to be
     * checked into the hotel.
     * preconditions: an existing reservation is selected
     * postcondition: the guest is checked into the room, the rooms
     * database is updated, and a guest folio is created.
     * @param event 
     */
    private void onClickCheckIn(ActionEvent event) { 
        // TODO
    }
    
}
