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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.CurrencyStringConverter;

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
    private TableColumn<FolioCharge, String> colAmount;
    @FXML
    private Label lblTotal, lblSubTotal,lblTaxes;
 
    
    Reservation reservation;
    BillingMenuDAO dao;
    User user;
    MainMenuController main;
    double taxRate = .07;

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
    private void onClickPrintInvoice(ActionEvent event) {
    }

    @FXML
    private void onClickMakePayment(ActionEvent event) {
    }
   
    public void getCharges() {
        ObservableList<FolioCharge> charges = FXCollections.observableArrayList();
        charges = dao.queryCharges(1234);
        tblCharges.setItems(charges);
        calculateTotals();
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
        colAmount.setCellValueFactory( cellData ->
            Bindings.format("%.2f", cellData.getValue().getAmount())
        );
        
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

    private void calculateTotals() {
        CurrencyStringConverter csc = new CurrencyStringConverter();
        double subtotal = 0, total = 0, taxes = 0;
        subtotal = tblCharges.getItems().stream().mapToDouble(FolioCharge::getAmount).sum();
        taxes = subtotal * taxRate;
        total = subtotal + taxes;
        lblTotal.setText(csc.toString(total));
        lblTaxes.setText(csc.toString(taxes));
        lblSubTotal.setText(csc.toString(subtotal));
    }
    
}
