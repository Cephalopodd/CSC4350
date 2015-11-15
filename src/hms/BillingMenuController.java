/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.BillingMenuDAO;
import hms.model.FolioCharge;
import hms.model.Reservation;
import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class BillingMenuController implements Initializable, SubMenu {
    @FXML
    private TableView<FolioCharge> tblCharges;
    @FXML
    private TableColumn<FolioCharge, String> colCode;
    @FXML
    private TableColumn<FolioCharge, String> colDescription;
    @FXML
    private TableColumn<FolioCharge, String> colQuantity;
    @FXML
    private TableColumn<FolioCharge, Double> colAmount;
    
    Reservation reservation;
    BillingMenuDAO dao;
    User user;
    MainMenuController main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new BillingMenuDAO();
        setupColumns();
        getCharges();
    }    

    @FXML
    private void onClickPrintMenu(ActionEvent event) {
    }

    @FXML
    private void onClickMakePayment(ActionEvent event) {
    }
   
    public void getCharges() {
        ObservableList charges = FXCollections.observableArrayList();
        charges = dao.queryCharges(1234);
        tblCharges.setItems(charges);
    }

    private void setupColumns() {

        colCode.setCellValueFactory(
                new PropertyValueFactory<>("Code"));
        colDescription.setCellValueFactory(
                new PropertyValueFactory<>("Description"));
        colQuantity.setCellValueFactory(
                new PropertyValueFactory<>("Date"));
        colAmount.setCellValueFactory(
                new PropertyValueFactory<>("Amount"));
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    @Override
    public void setUser(User e) {
        this.user = e;
    }
    
}
