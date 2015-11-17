package hms;

import hms.model.User;
import hms.model.LoginDAO;
import hms.model.User;
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

//FXML controller class. ..
public class LoginController implements Initializable {

    HMS HMSapp;
    LoginDAO dao;
    User user;
    
    //Button used in the login form GUI.
    //Located in the Login.fxml
    @FXML
    private TextField unTxtField;
    @FXML
    private PasswordField pwdTxtField;
    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label lblLoginError;
    @FXML
    private Label unLabel;
    @FXML
    private Label pwdLabel;
    
    //Initalize the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Creates a new database access object called LoginDAO.
        dao = new LoginDAO();
        unTxtField.requestFocus();
    }
    
    //Sets the HMS main application.
    public void setHMSApp(HMS HMSapp) {
        this.HMSapp = HMSapp;
    }
    
    //Method to exit the program if the user presses exit.
    @FXML
    private void onClickExit(ActionEvent event) {
        HMSapp.exit();
    }
    
    //Method if the users clicks login.
    //Post: The user user is taken to the appropiate page according to their permission levels.
    @FXML
    private void onClickLogin(ActionEvent event) {
       
        //Authenticate User
        user = dao.authenticateUser(unTxtField.getText(), pwdTxtField.getText().hashCode());
        
        //Process Login
        if (user == null){
            processLoginError();
        } else{    
            processLogin(user);
        }
    }
    
    private void processLoginError() {
        //Clear Login and Password and set Cusor back in Login field
        unTxtField.setText("");
        pwdTxtField.setText("");
        unTxtField.requestFocus();
 
        //Set Error Message
        lblLoginError.setText("Incorrect Login name or Password");
        
        //Commented out the fading. Do not need it.
        /*/Fade the error message in and back out
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
        st.play();*/
        
    }
    
    //Process login method. Gets the username and password enterned in to the text fields.
    private void processLogin(User user){
        //Clear user Name and Password
        unTxtField.setText("");
        pwdTxtField.setText("");
        HMSapp.doLogin(user);
        
        //If login successfull sets the message to blank.
        //This way the message reset after login.
        lblLoginError.setText(" ");
    }
    
}
