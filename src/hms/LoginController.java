package hms;

import hms.model.User;
import hms.model.LoginDAO;
import hms.model.NewUserDAO;
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
    NewUserDAO userCheck;
    
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
        
        if(isValid() == true){
            processLogin(user);
        }
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
    
    private boolean isValid(){
        
        userCheck = new NewUserDAO();
        dao = new LoginDAO();
        
        boolean unFieldBlank = unTxtField.getText().isEmpty();
        boolean pwdFieldBlank = pwdTxtField.getText().isEmpty();
        boolean passedValidation = true;
        
        //If field are blank, prompts user to enter in.
        if(unFieldBlank && pwdFieldBlank){
            unLabel.setText("Please enter in an username.");
            pwdLabel.setText("Please enter in a password.");
            passedValidation = false;
        }
        if(!unFieldBlank){
        if(!userCheck.unTaken(unTxtField.getText())){
            unLabel.setText("Username does not exist! Contact Admin!");
            pwdLabel.setText("Account does not exist! Contact Admin!");
            unTxtField.setText("");
            pwdTxtField.setText("");
            passedValidation = false;
        }else{
            unLabel.setText("");
            pwdLabel.setText("");
        }}
        
        if(!dao.matchPassword(unTxtField.getText(), pwdTxtField.getText().hashCode())){
            pwdLabel.setText("Invalid password, please try again!");
            passedValidation = false;
        }
        
        
        return passedValidation;
    }
    
    
    
}
