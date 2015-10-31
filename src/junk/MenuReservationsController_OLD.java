/**
 * MenuReservationsController class
 * 
 * This class is designed to handle the reservations menu GUI. The
 * reservations menu is designed for to allow reservations agents
 * to create new reservations and to modify existing ones.
 * 
 */
package junk;

import hms.Reservation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**

 * @author HMS Team
 */
public class MenuReservationsController_OLD implements Initializable {
    
    private Parent root;  
    private GridPane reservationsPane;
    private Button btnSearch, btnClear, btnEdit, btnNew, btnDelete, btnProfile;
    private DatePicker arrivalDate, departureDate;
    private TextField firstName, lastName, group, memberID, confirmationNumber;
    private TableView<Reservation> tblReservations;
    private ObservableList<Reservation> reservations;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    /**
     * This method searches the reservation list using the criteria
     * entered into the text boxes.
     * precondition: none
     * postcondition: The displayed reservation table is modified 
     * to include entries matching the search criteria.
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
     * This method launches the create new Reservation GUI
     * preconditions: none
     * postconditions: The create new Reservation Form is generated.
     * @param event 
     */
    private void onClickNew(ActionEvent event) { 
        // TODO
    }
    
    /**
     * This method launches the create new Reservation GUI with 
     * the form populated with the information from the selected 
     * reservation
     * preconditions: an reservation is selected.
     * postconditions: the create new Reservation Form is generated with 
     * populated information
     * @param event 
     */
    private void onClickEdit(ActionEvent event) { 
        // TODO
    }
    
    /**
     * This method deletes the current reservation
     * preconditions: a reservation is selected
     * postconditions: the reservation is deleted.
     * @param event 
     */
     private void onClickDelete(ActionEvent event) { 
        // TODO
    }
     
     
    /**
     * This method allows to Query existing profiles.
     * preconditions: none
     * postconditions: The query will return the guest information, or
     * launch the createProfile to create a new profile.
     * @param event 
     */
     private void onClickProfile(ActionEvent event) { 
        // TODO
    }
 
    
}
