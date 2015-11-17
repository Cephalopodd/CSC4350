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
            processNoUserLoginError();
        }else{
            processLogin(user);
        }
    }
    
    private void processNoUserLoginError() {
        //Clear Login and Password and set Cusor back in Login field
        unTxtField.setText("");
        pwdTxtField.setText("");
        unTxtField.requestFocus();
        unLabel.setText("Username not found.\n Please ask admin to create account.");
        
    }
    
    //Process login method. Gets the username and password enterned in to the text fields.
    private void processLogin(User user){
        //Clear username, password and error message. Then login user.
        unTxtField.setText("");
        pwdTxtField.setText("");
        unLabel.setText("");
        pwdLabel.setText("");
        HMSapp.doLogin(user);
    }

    private void processPasswordIncorrectError() {
        //Clean 
        unTxtField.setText("");
        pwdTxtField.setText("");
        unTxtField.requestFocus();
        pwdLabel.setText("Incorrect Password! Try Again!");
    }
    
}
