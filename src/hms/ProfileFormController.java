/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jgreene
 */
public class ProfileFormController implements Initializable {
    @FXML
    private Text lblHeading;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtGroupName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtCompanyName;
    @FXML
    private ChoiceBox<String> cbxTitle;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtApt;
    @FXML
    private TextField txtCountry;
    @FXML
    private ChoiceBox<String> cbxState;
    @FXML
    private CheckBox chkVIP;
    @FXML
    private TextArea txtNotes;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    private static Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionFirstName(ActionEvent event) {
    }

    @FXML
    private void onActionStreet(ActionEvent event) {
    }

    @FXML
    private void onActionLastName(ActionEvent event) {
    }

    @FXML
    private void onActionGroupName(ActionEvent event) {
    }

    @FXML
    private void onActionEmail(ActionEvent event) {
    }

    @FXML
    private void onActionPhoneNumber(ActionEvent event) {
    }

    @FXML
    private void onActionCompanyName(ActionEvent event) {
    }

    @FXML
    private void onActionCity(ActionEvent event) {
    }

    @FXML
    private void onActionApt(ActionEvent event) {
    }

    @FXML
    private void onActionCountry(ActionEvent event) {
    }

    @FXML
    private void onActionVIP(ActionEvent event) {
    }

    @FXML
    private void onActionNotes(MouseEvent event) {
    }

    @FXML
    private void onActionSave(ActionEvent event) {
    }

    @FXML
    private void onActionCancel(ActionEvent event) {
        stage.close();
    }
    
    
    public void display(MainMenuController main) {
        
        stage = new Stage();
        Parent root = null;
        
        try {
            root = FXMLLoader.load(main.getClass()
                    .getResource("ProfileForm.fxml"));
        } catch (Exception ex) {
            Logger.getLogger(CheckInFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        
        stage.setResizable(false);
        //stage.setAlwaysOnTop(true);   //cannot find symbol error
        stage.toFront();    //stop gap fix for above
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        //This should fix fullscreen but I need link to hmsApp in subMenu.
        //  stage.initOwner(hmsApp);
        
        stage.setScene(scene);
        stage.showAndWait();
    }
    
}