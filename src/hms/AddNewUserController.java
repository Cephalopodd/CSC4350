package hms;

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
        // TODO
    }    

    @FXML
    private void onClickFinish(ActionEvent event) {
        
        addNewUserValidation();
        
    }
    
    public void addNewUserValidation(){
        
        //Boolean bindings.
        boolean passwordNotMatch = !pwdTxtField.getText().equals(confirmPwdTxtField.getText());
        boolean unTxtFieldBlank = unTxtField.getText().isEmpty();
        boolean pwdTxtFieldBlank = pwdTxtField.getText().isEmpty();
        boolean confirmPwdTxtFieldBlank = confirmPwdTxtField.getText().isEmpty();
        boolean noCheckBoxSelected = !chkManager.isSelected() && !chkEmployee.isSelected() && !chkAdministrator.isSelected();
        
        if(unTxtFieldBlank){
            unLabel.setText("Username must be entered!");
        }else{
            unLabel.setText(null);
        }
        
        if(pwdTxtFieldBlank){
            pwdLabel.setText("Password must be entered!");
        }else{
            pwdLabel.setText(null);
        }
        
        if(confirmPwdTxtFieldBlank){
            confirmLabel.setText("Password must be entered!");
        }else{
            confirmLabel.setText(null);
        }
        
        if(passwordNotMatch){
            pwdLabel.setText("Passwords do not match!");
            confirmLabel.setText("Passwords do not match!");
        }
        
        if(noCheckBoxSelected){
            permissionLabel.setText("Permission level must be checked.");
        }else{
            permissionLabel.setText(null);
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
