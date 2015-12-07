/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.unused;

import hms.MainMenuController;
import hms.SubMenu;
import hms.model.BillingMenuDAO;
import hms.model.FolioCharge;
import hms.model.InvoiceDAO;
import hms.model.Reservation;
import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class InvoiceController implements Initializable, SubMenu {

    ObservableList<String> payTypeList = FXCollections
            .observableArrayList("Cash", "Credit Card", "Check");

    @FXML
    private AnchorPane invoiceStage;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblFirstName;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblState;
    @FXML
    private Label lblZip;
    @FXML
    private Label lblCheckInDate;
    @FXML
    private Label lblCheckOutDate;
    @FXML
    private Label lblRoomNum;
    @FXML
    private TableView<FolioCharge> tblInvoiceCharges;
    @FXML
    private Label lblBalanceOwed;
    @FXML
    private Label lblChargedAmt;
    @FXML
    private Label lblTotCharges;
    @FXML
    private ChoiceBox<String> payTypeMenu;
    @FXML
    private TableColumn<FolioCharge, String> colDescription;
    @FXML
    private TableColumn<FolioCharge, String> colDate;
    @FXML
    private TableColumn<FolioCharge, String> colAmount;

    Reservation reservation;
    InvoiceDAO dao;
    User user;
    MainMenuController main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new InvoiceDAO();
        setupColumns();
        getCharges();
        payTypeMenu.setItems(payTypeList);
    }

    private void setupColumns() {

        colDescription.setCellValueFactory(
                new PropertyValueFactory<>("Description"));
        colDate.setCellValueFactory(
                new PropertyValueFactory<>("Date"));
        colAmount.setCellValueFactory(
                new PropertyValueFactory<>("Amount"));
        colAmount.setCellValueFactory(cellData
                -> Bindings.format("%.2f", cellData.getValue().getAmount())
        );

    }

    public void getCharges() {
        ObservableList<FolioCharge> charges = FXCollections.observableArrayList();
        charges = dao.queryCharges(1234);
        tblInvoiceCharges.setItems(charges);

    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void setUser(User e) {
        this.user = e;
    }

    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

}
