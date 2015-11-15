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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private static Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        stage.close();
    }
   
    public void setFields(int room, CreditCard cc) {
        
    }
    
    public void display(MainMenuController main) {
        
        stage = new Stage();
        Parent root = null;
        
        try {
            root = FXMLLoader.load(main.getClass()
                    .getResource("CheckInForm.fxml"));
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
