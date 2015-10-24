/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jgreene
 */
public class MenuFrontDeskFXMLController implements Initializable,SubMenu {
    @FXML
    private Button btnArrivals;
    @FXML
    private Button btnInHouseGuests;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtCompany;
    @FXML
    private TextField txtGroup;
    @FXML
    private DatePicker dateArrival;
    @FXML
    private DatePicker dataDeparture;
    @FXML
    private TextField txtConfirmation;
    @FXML
    private TextField btnPhone;
    @FXML
    private TableView<?> tblFrontDesk;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnWalkIn;
    @FXML
    private Button btnCheckIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickArrivals(ActionEvent event) {
    }

    @FXML
    private void onClickInHouseGuests(ActionEvent event) {
    }

    @FXML
    private void onClickSearch(ActionEvent event) {
    }

    @FXML
    private void onClickClear(ActionEvent event) {
    }

    @FXML
    private void onClickWalkIn(ActionEvent event) {
    }

    @FXML
    private void onClickCheckIn(ActionEvent event) {
    }

    @Override
    public void setSubMenuParent(MenuMainController main) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUser(User e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
