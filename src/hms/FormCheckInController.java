/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import com.sun.media.jfxmediaimpl.platform.Platform;
import java.io.IOException;
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
public class FormCheckInController implements Initializable {
    @FXML
    private GridPane loginPane;
    @FXML
    private TextField txtFirstName;
    @FXML
    private ChoiceBox<CreditCardType> cbxCCType;
    @FXML
    private TextField txtCC;
    @FXML
    private TextField txtLastName;
    @FXML
    private ChoiceBox<Integer> cbxMonth;
    @FXML
    private ChoiceBox<Integer> cbxYear;
    @FXML
    private Button btnOK;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtCCID;
    
    private ObservableList<Integer> months;
    private ObservableList<Integer> years;
    private ObservableList<CreditCardType> ccTypes;
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
                CreditCardType.DISC, CreditCardType.MAST, CreditCardType.VISA);
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
   
    public static void display(MenuMainController p) {
        
        stage = new Stage();
        Parent root = null;
        
        try {
            root = FXMLLoader.load(p.getClass()
                    .getResource("FormCheckInFXML.fxml"));
        } catch (Exception ex) {
            Logger.getLogger(FormCheckInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        //This should fix fullscreen but I need link to hmsApp in subMenu.
        //  stage.initOwner(hmsApp);
        
        stage.setScene(scene);
        stage.showAndWait();
    }
}
