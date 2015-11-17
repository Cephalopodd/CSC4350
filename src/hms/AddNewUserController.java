package hms;

import hms.model.NewUserDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewUserController implements Initializable {
    
    private Stage stage;
    NewUserDAO user;
    int count = 0;
    
    @FXML
    private TextField unTxtField;
    @FXML
    private PasswordField pwdTxtField;
    @FXML
    private PasswordField confirmPwdTxtField;
    @FXML
    private CheckBox chkManager;
    @FXML
    private CheckBox chkEmployee;
    @FXML
    private CheckBox chkAdministrator;
    @FXML
    private Button btnFinish;
    @FXML
    private Button btnCancel;
    @FXML
    private Label unLabel;
    @FXML
    private Label pwdLabel;
    @FXML
    private Label confirmLabel;
    @FXML
    private Label permissionLabel;

    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void onClickFinish(ActionEvent event) {
        
        if(addNewUserValidation() == true){
            createNewUser();
        }
        
    }
    
    public boolean addNewUserValidation(){
        //Boolean bindings.
        boolean passwordNotMatch = !pwdTxtField.getText().equals(confirmPwdTxtField.getText());
        boolean unTxtFieldBlank = unTxtField.getText().isEmpty();
        boolean pwdTxtFieldBlank = pwdTxtField.getText().isEmpty();
        boolean confirmPwdTxtFieldBlank = confirmPwdTxtField.getText().isEmpty();       
        boolean unFieldPassed = true, pwdFieldPassed = true, confirmFieldPassed = true, notMatchedPassed = true, chkBoxPassed = true;
        user = new NewUserDAO();
        
        if(unTxtFieldBlank){
            unLabel.setText("Username must be entered!");
            unFieldPassed = false;
        }else if(user.unTaken(unTxtField.getText())){
            unLabel.setText("Username is already taken!\n Please choose another.");
            unFieldPassed = false;
        }else{
            unLabel.setText(null);
        }
        
        if(pwdTxtFieldBlank){
            pwdLabel.setText("Password must be entered!");
            pwdFieldPassed = false;
        }else{
            pwdLabel.setText(null);
        }
        
        if(confirmPwdTxtFieldBlank){
            confirmLabel.setText("Password must be entered!");
            confirmFieldPassed = false;
        }else{
            confirmLabel.setText(null);
        }
        
        if(passwordNotMatch){
            pwdLabel.setText("Passwords do not match!");
            confirmLabel.setText("Passwords do not match!");
            notMatchedPassed = false;
        }
        
        if(chkManager.isSelected()){
            count++;
        }else if(chkEmployee.isSelected()){
            count++;
        }else if(chkAdministrator.isSelected()){
            count++;
        }
        
        if(count != 1){
            permissionLabel.setText("One permission level must be chosen!");
            chkBoxPassed = false;
        }else if(count == 1){
            permissionLabel.setText(null);
        }
        
        if(unFieldPassed && pwdFieldPassed && confirmFieldPassed && notMatchedPassed && chkBoxPassed){
            return true;
        } else{
            return false;
        }
        
    }
    
    public void createNewUser(){
        user = new NewUserDAO();
        if(chkAdministrator.isSelected()){
            user.createAdministratorUser(unTxtField.getText(), pwdTxtField.getText());
        }else if(chkManager.isSelected()){
            user.createManagerUser(unTxtField.getText(), pwdTxtField.getText());
        }else if(chkEmployee.isSelected()){
            user.createEmployeeUser(unTxtField.getText(), pwdTxtField.getText());
        }
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        stage.close();
    }
    
    void setStage(Stage stage){
        this.stage = stage;
    }
    
    
}
