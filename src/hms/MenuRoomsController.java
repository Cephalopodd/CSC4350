/**
 * MenuRoomsController class
 * 
 * This class is designed to handle the rooms management menu GUI. The
 * rooms management menu is designed for agents working in housekeeping
 * and engineering to set the condition of the room.
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
 * MenuRoomsController class
 *
 * @author HMS Team
 */
public class MenuRoomsController implements Initializable {
    
    private Parent root;  
    private GridPane menuRoomsControllerPane;
    private Button btnSearch, btnClear, btnEdit, btnGenerateReport;
    private ChoiceBox reports;
    private TextField roomNum, lastName, firstName;
    private TableView<Room> tblRooms;
    private ObservableList<Room> roomsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * This method searches the rooms list using the criteria
     * entered into the text boxes.
     * precondition: none
     * postcondition: The displayed rooms table is modified to include entries
     * matching the search criteria.
     * @param event 
     */
    private void onClickSearch(ActionEvent event) { 
        // TODO
    }

    /**
     * This method allows the room status to be changed.
     * precondition: a room is selected
     * postcondition: the room status will be updated
     * @param event 
     */   
    private void onClickEdit(ActionEvent event) { 
        // TODO
    }

    /**
     * This method allows to print a housekeeping report
     * precondition: A report is selected in the ChoiceBox.
     * postcondition: The selected report will be printed 
     * @param event 
     */
    private void onClickGenerateReport(ActionEvent event) {
        // TODO
    }
    
}
