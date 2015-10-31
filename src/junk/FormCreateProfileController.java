/**
 * CreateProfileController class
 * 
 * CreateProfileController is the controller for the createProfile GUI
 * which is used to create guest profiles. 
 * 
 */
package junk;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * CreateProfileController class
 *
 * @author Team SLAM
 */
public class FormCreateProfileController implements Initializable {
    
    private Parent root;  
    private GridPane createProfilePane;
    private Button btnSave, btnCancel;
    private TextField txtFirstName, txtLastName, txtTitle, txtPhone,
            txtEmail, txtNotes, txtStreet, txtApt, txtCity, txtState,
            txtCountry, txtZip, txtCompany;
    private Label lblMemberID;
    private CheckBox chkVIP;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * This method will create a new profile object, 
     * and store the object information in the database.
     * Precondition: Fields are verified to be valid
     * Postcondition: Profile information is stored in the DB.
     */
    private void onClickSave(ActionEvent event) { 
        // TODO
    }

    /**
     * This method cancels the create profile event, and closes
     * the form.
     * Precondition: none
     * Postcondition: createProfilePane is closed.
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
