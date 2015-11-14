/**
 * LoginController
 *
 * This class is used to handle the initial login GUI
 *
 */
package hms;

import hms.model.User;
import hms.model.LoginDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Team SLAM
 */
public class LoginController implements Initializable {
    
    HMS HMSapp;
    LoginDAO dao;
    User user;

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnExit;
    @FXML
    private Label lblLoginError;
    @FXML
    private GridPane loginPane;
    

    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dao = new LoginDAO();
        txtUserName.requestFocus();
    }

   /**
     * Sets the HMS main app
     * @param HMSapp
     */
    public void setHMSApp(HMS HMSapp) {
        this.HMSapp = HMSapp;
    }
    
    /**
     * This method exits the program. precondition: none postcondition: The
     * program is terminated.
     *
     * @param event
     */
    @FXML
    private void onClickExit(ActionEvent event) {
        HMSapp.exit();
    }

    /**
     * This method verifies the username and the password and launches the main
     * menu. precondition: none postcondition: If the username and password are
     * valid, the main menu launches and permissions are set. If the username
     * and password are not valid, then an error message is displayed.
     *
     * @param event
     */
    @FXML
    private void onClickLogin(ActionEvent event) {
       
        //Authenticate User
        user = dao.authenticateUser(txtUserName.getText(), txtPassword.getText().hashCode());
        
        //Process Login
        if (user == null) {
            processLoginError();
        } else {    
            processLogin(user);
        }
    }
    
    private void processLoginError() {
        //Clear Login and Password and set Cusor back in Login field
        txtUserName.setText("");
        txtPassword.setText("");
        txtUserName.requestFocus();
 
        //Set Error Message
        lblLoginError.setText("Incorrect Login name or Password");
        
        //Fade the error message in and back out
        FadeTransition ft0 = new FadeTransition(Duration.millis(250), lblLoginError);
        FadeTransition ft1 = new FadeTransition(Duration.millis(3000), lblLoginError);
        FadeTransition ft2 = new FadeTransition(Duration.millis(250), lblLoginError);
        ft0.setFromValue(0.0);
        ft0.setToValue(1.0);
        ft1.setFromValue(1.0);
        ft1.setToValue(1.0);
        ft2.setFromValue(1.0);
        ft2.setToValue(0.0);
        SequentialTransition st = new SequentialTransition(ft0,ft1,ft2);
        st.play();
        
    }
    
    private void processLogin(User user) {
        //Clear user Name and Password
        txtUserName.setText("");
        txtPassword.setText("");
        HMSapp.doLogin(user);
    }
    
}
