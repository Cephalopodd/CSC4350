/**
 * LoginController 
 * 
 * This class is used to handle the initial login GUI 
 * 
 */
package hms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * 
 * @author Team SLAM
 */
public class LoginController implements Initializable {
    
    private Parent root;
    private GridPane loginPane;
    private Button btnExit, btnLogin;
    private PasswordField txtPassword;
    private TextField txtUserName;
    private Label lblErrorMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * This method exits the program.
     * precondition: none
     * postcondition: The program is terminated.
     * @param event 
     */
    private void onClickExit(ActionEvent event) {
        //TODO
    }

    /**
     * This method verifies the username and the password and launches
     * the main menu.
     * precondition: none
     * postcondition: If the username and password are valid, the main
     * menu launches and permissions are set.  If the username and 
     * password are not valid, then an error message is displayed.
     * @param event 
     */
    private void onClickLogin(ActionEvent event) {
        //TODO
    }
    
}
