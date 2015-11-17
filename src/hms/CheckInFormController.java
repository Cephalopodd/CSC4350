/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.CreditCardType;
import hms.model.CreditCard;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jgreene
 */
public class CheckInFormController implements Initializable {
    
    @FXML
    private GridPane checkInFormPane;

    @FXML
    private TextField txtCCNumber;
    @FXML
    private TextField txtNameOnCC;
    @FXML
    private TextField txtCCID;
    @FXML
    private Label lblRoomNumber;
    @FXML
    private ChoiceBox<Integer> cbxMonth;
    @FXML
    private ChoiceBox<Integer> cbxYear;
    @FXML
    private ChoiceBox<String> cbxCCType;
    
    @FXML
    private Button btnOK;
    @FXML
    private Button btnCancel;
    
    private ObservableList<Integer> months;
    private ObservableList<Integer> years;
    private ObservableList<String> ccTypes;
    
    private Stage stage;
    private boolean accepted;
    private CreditCard cc;
    private int roomNumber;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accepted = false;
        months = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
        years = FXCollections.observableArrayList(2015,2016,2017,2018,2019,
                2020,2021,2022,2023,2024,2025);
        ccTypes = FXCollections.observableArrayList(CreditCardType.AMEX,
                CreditCardType.DISC, CreditCardType.MC, CreditCardType.VISA);
        cbxMonth.setItems(months);
        cbxYear.setItems(years);
        cbxCCType.setItems(ccTypes);
        cbxMonth.setValue(1);
        cbxYear.setValue(2015);
        cbxCCType.setValue(CreditCardType.AMEX);
    }    

    @FXML
    private void onClickOK(ActionEvent event) {
        accepted = true;
        stage.close();
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        accepted = false;
        stage.close();
    }
  
    void setStage(Stage stage) {
        this.stage = stage;
    }
    
    void setCC(CreditCard cc) {
        this.cc = cc;
        if (cc == null)
            return;
        
        txtCCNumber.setText("");
        txtNameOnCC.setText(cc.getName());
        txtCCID.setText(cc.getCode());
        cbxCCType.setValue(cc.getType());
        cbxMonth.setValue(cc.getExpMonth());
        cbxYear.setValue(cc.getExpYear());
    }
    
    CreditCard getCC() {
        return new CreditCard(
                txtNameOnCC.getText(),
                txtCCNumber.getText(),
                txtCCID.getText(),
                cbxCCType.getValue(),
                cbxMonth.getValue(),
                cbxYear.getValue()
        );
    }
    
    void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
        lblRoomNumber.setText(roomNumber+"");
    }
    
    boolean getResult() {
        return accepted;
    }
    
}
