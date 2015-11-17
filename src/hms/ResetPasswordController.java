package hms;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    @FXML
    private ChoiceBox<?> unDropDown;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickFinish(ActionEvent event) {
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        stage.close();
    }
    
    void setStage(Stage stage){
        this.stage = stage;
    }
    
}
