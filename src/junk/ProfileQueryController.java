/**
 * ProfileQueryController
 * 
 * This class is used to handle the Query Profile GUI events so that 
 * a profile of a guest can be located.  If the guest cannot be 
 * located, then a new profile can be generated.

 */
package junk;

import hms.Profile;
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
 * @author HMS Team
 */
public class ProfileQueryController implements Initializable {
    
    private Parent root;  
    private GridPane profileQueryPane;
    private Button btnSearch, btnClear, btnNew, btnEdit, btnClose;
    private TextField txtFirstName, txtLastName, txtCity, txtZip,
            txtMemberID, txtCompany;
    private TableView<Profile> tblProfiles;
    private ObservableList<Profile> profilesList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * This method searches the profile list using the criteria
     * entered into the text boxes.
     * precondition: none
     * postcondition: The profilesList is modified to include entries
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
     * This method launches the form to create a new Profile
     * preconditions: none
     * postconditions: The create new Profile Form is generated.
     * @param event 
     */
    private void onClickNew(ActionEvent event) { 
        // TODO
    }

    /**
     * This method launches the form to create a new Profile and
     * populates the data with the information from the selected profile.
     * preconditions: a profile is selected.
     * postconditions: The create new Profile Form is generated.
     * @param event 
     */
    private void onClickEdit(ActionEvent event) { 
        // TODO
    }

    /**
     * This method closes this menu.
     * preconditions: none
     * postconditions: the menu is closed
     * @param event 
     */
    private void onClickClose(ActionEvent event) {
        // TODO
    }
    
}
