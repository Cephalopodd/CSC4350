/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.FrontDeskDAO;
import hms.model.Profile;
import hms.model.ProfileBuilder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private TextField txtLastName;
    
    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private ChoiceBox<String> cbxTitles;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtZip;
    @FXML
    private TextField txtApt;
    @FXML
    private TextField txtCountry;
    @FXML
    private ChoiceBox<String> cbxStates;
    @FXML
    private CheckBox chkVIP;
    @FXML
    private TextArea txtNotes;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    
    private Stage stage;
    private FrontDeskDAO dao;
    private boolean result = false;
    
    private ObservableList<String> states;
    private ObservableList<String> titles;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new FrontDeskDAO();
        initChoiceBoxes();
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
    private void onActionEmail(ActionEvent event) {
    }

    @FXML
    private void onActionPhoneNumber(ActionEvent event) {
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
    private void onActionZip(ActionEvent event){
        
    }

    @FXML
    private void onActionSave(ActionEvent event) {
        handleSave();
    }

    @FXML
    private void onActionCancel(ActionEvent event) {
        stage.close();
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setProfileInformation(int profileID) {
        
        
        Profile p = dao.getProfile(profileID);
        if (p == null) {
            return;
        }
        
        txtFirstName.setText(p.getFirstName());
        txtLastName.setText(p.getLastName());
        txtPhoneNumber.setText(p.getPhoneNumber());
        txtEmail.setText(p.getEmail());
        txtNotes.setText(p.getNotes());
        cbxTitles.setValue(p.getTitle());
        cbxStates.setValue(p.getState());
        chkVIP.setSelected(p.isVIP());
        txtStreet.setText(p.getStreet());
        txtApt.setText(p.getApt());
        txtCity.setText(p.getCity());
        txtZip.setText(p.getZip());
        txtCountry.setText(p.getCountry());
  
    }

    private void initChoiceBoxes() {
        states = FXCollections.observableArrayList();
        titles = FXCollections.observableArrayList();
        states.addAll("AZ", "GA", "FL");
        titles.addAll("Mr", "Ms", "Mrs", "Dr");
        cbxStates.setItems(states);
        cbxTitles.setItems(titles);
        cbxStates.setValue("GA");
        cbxTitles.setValue("");
    }

    private boolean verifyFields() {
        return true;
    }

    boolean getResult() {
        return result;
    }

    private void handleSave() {
            
        if (!verifyFields()){
            result = false;
            stage.close();
        }

        Profile p = new ProfileBuilder()
                .setFirstName(txtFirstName.getText())
                .setLastName(txtLastName.getText())
                .setEmail(txtEmail.getText())
                .setPhoneNumber(txtPhoneNumber.getText())
                .setStreet(txtStreet.getText())
                .setApt(txtApt.getText())
                .setCity(txtCity.getText())
                .setState(cbxStates.getValue())
                .setZip(txtZip.getText())
                .setCountry(txtCountry.getText())
                .setVIP(chkVIP.isSelected())
                .setNotes(txtNotes.getText())
                .setTitle(cbxTitles.getValue())
                .createProfile();
        
        if ( p == null ) {
            result = false;
            stage.close();
        }
        
        result = dao.updateProfile(p);
    
        stage.close();
    }
   
}
