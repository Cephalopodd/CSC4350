/**
 * MenuAdminController class
 * 
 * This class is designed to handle the administrator menu GUI. The
 * administrator menu is designed to handle adding and 
 * modifying employees.
 * 
 */
package junk;

import hms.Employee;
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
 *
 * @author HMS Team
 */
public class MenuAdminController_OLD implements Initializable {
    
    private Parent root;  
    private GridPane adminPane;
    private Button btnSearch, btnClear, btnNew, btnEdit, btnDelete;
    private TextField txtFirstName, txtLastName, txtEmpNum;
    private TableView<Employee> tblEmployees;
    private ObservableList<Reservation> employeesList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * This method searches the employee list using the criteria
     * entered into the text boxes.
     * precondition: none
     * postcondition: The displayed employeeList is modified to include entries
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
     * This method launches the create new Employee GUI
     * preconditions: none
     * postconditions: The create new Employee Form is generated.
     * @param event 
     */
    private void onClickNew(ActionEvent event) { 
        // TODO
    }
    
    /**
     * This method launches the create new Employee GUI with 
     * with the form populated with the information from the selected 
     * employee
     * preconditions: an employee is selected.
     * postconditions: the create new Employee Form is generated with 
     * populated information
     * @param event 
     */
    private void onClickEdit(ActionEvent event) { 
        // TODO
    }
    
    /**
     * This method deletes the current employee
     * preconditions: an employee is selected
     * postconditions: the employee is deleted.
     * @param event 
     */
     private void onClickDelete(ActionEvent event) { 
        // TODO
    }
    
}
