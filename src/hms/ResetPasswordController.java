package hms;

import hms.model.NewUserDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ResetPasswordController implements Initializable {
    
    private Stage stage;
    NewUserDAO user;
    
    @FXML
    private ChoiceBox<String> unDropDown;
    @FXML
    private PasswordField pwdTxtField;
    @FXML
    private PasswordField confirmPwdTxtField;
    @FXML
    private Button btnFinish;
    @FXML
    private Button btnCancel;
    @FXML
    private Label unLabel;
    @FXML
    private Label pwdLabel;
    @FXML
    private Label confirmPwdLabel;

    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new NewUserDAO();
        unDropDown.setItems(FXCollections.observableArrayList(user.getUserList()));
    }    

    @FXML
    private void onClickFinish(ActionEvent event) {
        if(formValidation()){
            resetPassword();
            stage.close();
        }
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        stage.close();
    }
    
    public boolean formValidation(){
        boolean passwordNotMatch = !pwdTxtField.getText().equals(confirmPwdTxtField.getText());
        boolean passwordFieldBlank = pwdTxtField.getText().isEmpty();
        boolean confirmPwdTxtFieldBlank = confirmPwdTxtField.getText().isEmpty();
        boolean unFieldPassed = true, pwdFieldPassed = true, confirmFieldPassed = true, notMatchedPassed = true;
        
        /*if(unDropDown.getValue().toString().equals("")){
            unLabel.setText("Please pick an username!");
            unFieldPassed = false;
        }*/
        
        if(passwordFieldBlank){
            pwdLabel.setText("Password must be entered!");
            pwdFieldPassed = false;
        }else{
            pwdLabel.setText(null);
        }
        
        if(confirmPwdTxtFieldBlank){
            confirmPwdLabel.setText("Password must be entered!");
            confirmFieldPassed = false;
        }else{
            confirmPwdLabel.setText(null);
        }
        
        if(passwordNotMatch){
            pwdLabel.setText("Passwords do not match!");
            confirmPwdLabel.setText("Passwords do not match!");
            notMatchedPassed = false;
        }
        if(unFieldPassed && pwdFieldPassed && confirmFieldPassed && notMatchedPassed){
            return true;
        } else{
            return false;
        }
    }
    
    private void resetPassword() {
        user = new NewUserDAO();
        user.resetPassword(unDropDown.getValue().toString(), pwdTxtField.getText());
    }
    
    void setStage(Stage stage){
        this.stage = stage;
    }
    
}
