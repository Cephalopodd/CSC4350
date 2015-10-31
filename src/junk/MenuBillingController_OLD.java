/**
 * MenuBillingController class
 * 
 * This class is designed to handle the billing menu GUI. The
 * billing menu is designed for agents to be able to settle
 * accounts, post charges to guest folios, and check guests out
 * of rooms, and print reports.
 * 
 */
package junk;

import hms.Folio;
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
public class MenuBillingController_OLD implements Initializable {
    
    private Parent root;  
    private GridPane billingMenuPane;
    private Button btnSearch, btnClear, btnAddDelCharge, btnCheckOut, 
            btnGenerateReport;
    private RadioButton email, print;
    private TextField txtFirstName, txtLastName, txtGroupName, 
            txtRoomNum, txtEmail;
    private ChoiceBox report;
    private TableView<Folio> tblFolios;
    private ObservableList<Folio> folioList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * This method searches the folio list using the criteria
     * entered into the text boxes.
     * precondition: none
     * postcondition: The foliosList is modified to include entries
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
     * This method will add or delete charges from the guest folio.
     * precondition: A folio is selected
     * postcondition: The PostCharge form is generated.
     * @param event 
     */
    private void onClickAddDelCharge(ActionEvent event) { 
        // TODO
    }

    /**
     * This method allows to check out a guest.
     * precondition: none
     * postcondition: the guest will be checked out of the hotel. The room
     * status will be updated and the transaction will be posted.
     * @param event 
     */
    private void onClickCheckOut(ActionEvent event) { 
        // TODO
    }
    
    /**
     * This method allows to print or email a report
     * precondition: A report is selected in the ChoiceBox.
     * postcondition: The selected report will be printed or sent 
     * to email depending on the radio selection.
     * @param event 
     */
    private void onClickGenerateReport(ActionEvent event) { 
        // TODO
    }
    
}
