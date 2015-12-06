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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.util.converter.CurrencyStringConverter;

/**
 * FXML Controller class
 *
 * @author jgreene
 */
public class BillingMenuController implements Initializable, SubMenu {
    @FXML
    private TableView<FolioCharge> tblCharges;
    @FXML
    private TableColumn<FolioCharge, String> colDate;
    @FXML
    private TableColumn<FolioCharge, String> colCode;
    @FXML
    private TableColumn<FolioCharge, String> colDescription;
    @FXML
    private TableColumn<FolioCharge, String> colAmount;
    @FXML
    private Button btnCashPayment;
    @FXML
    private Label lblSubTotal;
    @FXML
    private Label lblTaxes;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblGuestName;
    @FXML
    private TextField txtAmount;
    @FXML
    private Button btnDeleteCharge;
    @FXML
    private TableView<Reservation> tblGuests;
    @FXML
    private TableColumn<Reservation, String> colFirst;
    @FXML
    private TableColumn<Reservation, String> colLast;
    @FXML
    private TableColumn<Reservation, Integer> colRoom;
    @FXML
    private Button btnSelectGuest;
    @FXML
    private Button btnPrintFolio;
    @FXML
    private Button btnCheckOut;
    @FXML
    private Label lblPrice;
    @FXML
    private Label lblItem;
    @FXML
    private Label lblDescription;
    @FXML
    private ScrollPane spItems;
    @FXML
    private TilePane tilePane;
    
    private MainMenuController main;
    private User user;
    private Reservation currentReservation;
    private BillingMenuDAO dao;
    private double taxRate = .08;
    
    private ObservableList charges;
    private ObservableList guests;
    private ObservableList items;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dao = new BillingMenuDAO();
        initGuestsTable();
        initChargesTable();
        initTilePane();
    }    

    @FXML
    private void onClickCashPayment(ActionEvent event) {
    }

    @FXML
    private void onClickDeleteCharge(ActionEvent event) {
    }

    @FXML
    private void onClickSelectGuest(ActionEvent event) {
        handleSelectGuest();
    }

    @FXML
    private void onClickPrintFolio(ActionEvent event) {
    }

    @FXML
    private void onClickCheckOut(ActionEvent event) {
    }

    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
    
    public void setCurrentReservation(Reservation currentReservation) {
        this.currentReservation = currentReservation;
    }
    

    private void initGuestsTable() {
        
        guests = FXCollections.observableArrayList();

        tblGuests.setItems(guests);

        colFirst.setCellValueFactory(
                new PropertyValueFactory<>("FirstName"));
        colLast.setCellValueFactory(
                new PropertyValueFactory<>("LastName"));
        colRoom.setCellValueFactory(
                new PropertyValueFactory<>("RoomNumber"));

        //Set Default Message
        Label msg = new Label("Guests");
        msg.setFont(new Font(24));
        msg.setOpacity(.5);
        tblGuests.setPlaceholder(msg);

        //Populate with data
        updateCurrentGuests();
        
    }

    private void initChargesTable() {
        charges = FXCollections.observableArrayList();
        
        tblCharges.setItems(charges);

        colCode.setCellValueFactory(
                new PropertyValueFactory<>("Code"));
        colDescription.setCellValueFactory(
                new PropertyValueFactory<>("Description"));
        colDate.setCellValueFactory(
                new PropertyValueFactory<>("Date"));
        colAmount.setCellValueFactory(
                new PropertyValueFactory<>("Amount"));
        colAmount.setCellValueFactory( cellData ->
            Bindings.format("%.2f", cellData.getValue().getAmount())
        );

        //Set Default Message
        Label msg = new Label("Guests");
        msg.setFont(new Font(24));
        msg.setOpacity(.5);
        tblGuests.setPlaceholder(msg);

        //Populate with data
        updateCurrentGuests();
    }
    
    private void initTilePane() {
        
        items = FXCollections.observableArrayList();
        
        tilePane.setVgap(12.0);
        tilePane.setHgap(12.0);
        tilePane.setPrefColumns(4);
        
        for (int i = 0 ; i< 100; i++) {
            Button btnTemp = new Button(" Item "+i+"\n");
            btnTemp.setOnMouseEntered( e-> {
                lblItem.textProperty().bind(btnTemp.textProperty());
            });
            btnTemp.setOnMouseDragOver( e-> {
                
            });
            btnTemp.setOnMouseExited( e-> {
                lblItem.textProperty().unbind();
            });
            btnTemp.setStyle("-fx-background-color: #ff4e50; -fx-font-size: 16px; -fx-text-fill: WHITE;");
            tilePane.getChildren().add(btnTemp);
        }
        
    }

    private void handleSelectGuest() {
        charges = dao.queryCharges(1234);
        tblCharges.setItems(charges);
        calculateTotals();
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

    private void updateCurrentGuests() {
        
    }
    
    
    
   

  
    
}
