package hms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewUserController implements Initializable {
    
    @FXML
    private TextField unTxtField;
    @FXML
    private TextField pwdTxtField1;
    @FXML
    private Button btnFinish;
    @FXML
    private Button btnCancel;
    @FXML
    private CheckBox chkManager;
    @FXML
    private CheckBox chkEmployee;
    @FXML
    private TextField confirmPwdTxtField;
    @FXML
    private CheckBox chkAdministrator;
    
    private Stage stage;
    
    
    
    //Initializes the controller class.
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
    
    void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
