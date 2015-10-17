/**
 * CreateGroupController class
 * 
 * CreateGroupController is the controller for the createGroup GUI
 * which is used to create new Groups. 
 * 
 */
package hms;

import java.net.URL;
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
public class FormCreateGroupController implements Initializable {
    
    private Parent root;  
    private GridPane createGroupPane;
    private Button btnSave, btnCancel;
    private TextField txtGroupName, txtContactName, txtContactNumber,
            txtEmail, txtStreet, txtApt, txtCity, txtState,
            txtCountry, txtZip, txtCompany;
    private Label lblGroupID;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * This method will create a new Group object, 
     * and store the object information in the database.
     * Precondition: Fields are verified to be valid
     * Postcondition: Group information is stored in the DB.
     */
    private void onClickSave(ActionEvent event) { 
        // TODO
    }

    /**
     * This method cancels the create group event, and closes
     * the form.
     * Precondition: none
     * Postcondition: createGroupPane is closed.
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
