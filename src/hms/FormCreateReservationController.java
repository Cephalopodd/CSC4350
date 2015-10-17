/**
 * CreateReservationController class
 * 
 * This is the controller for the reservation 
 * GUI which is used to create guest reservations. 
 * Agents can input the desired date and profile information,
 * and the new reservation will be created.
 * 
 */
package hms;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Team SLAM
 */
public class FormCreateReservationController implements Initializable {
    
    private Parent root;  
    private GridPane createReservationPane;
    private Button btnSave, btnCancel;
    private TextField numAdults, numChildren, numRooms;
    private TextArea comments;
    private ChoiceBox paymentType, roomType, rateCode;
    private LocalDate checkin, checkout;
    private Label lblFirstName,lblLastName, lblMemberID;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * This method will create a new reservation object, 
     * and store the object information in the database.
     * Precondition: Fields are verified to be valid
     * Postcondition: Reservations information is stored in the DB.
     */
    private void onClickSave(ActionEvent event) { 
        // TODO
    }

    /**
     * This method cancels the create reservation event, and closes
     * the form.
     * Precondition: none
     * Postcondition: Reservations form is closed.
     */
    private void onClickCancel(ActionEvent event) {
        // TODO
    }
    
    /**
     * This method verifies the information in the input fields.
     * Precondition: none
     * Postcondition: returns true if the field values are valid or
     * returns false if the field values are not valid.
     */
    private boolean verifyInput(){
        // TODO
        return false;
    }
    
}
