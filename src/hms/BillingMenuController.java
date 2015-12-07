/**
 * The class is the controller for the billing menu view
 */
package hms;

import hms.model.BillingCode;
import hms.model.BillingMenuDAO;
import hms.model.BillingMenuDTO;
import hms.model.FolioCharge;
import hms.model.Item;
import hms.model.Reservation;
import hms.model.User;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.util.converter.CurrencyStringConverter;

/**
 * BillingMenuController class
 *
 * @author Team Slam
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
    private TableView<BillingMenuDTO> tblGuests;
    @FXML
    private TableColumn<BillingMenuDTO, String> colFirst;
    @FXML
    private TableColumn<BillingMenuDTO, String> colLast;
    @FXML
    private TableColumn<BillingMenuDTO, Integer> colRoom;
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
    private FlowPane itemPane;
    @FXML
    private AnchorPane itemDetailPane;
    @FXML
    private GridPane chargesPane;
    @FXML
    public RadioButton radioActive;
    @FXML
    private RadioButton radioPast;

    private MainMenuController main;
    private User user;
    private Reservation currentReservation;
    private BillingMenuDAO dao;

    private ObservableList<FolioCharge> charges;
    private ObservableList<BillingMenuDTO> guests;
    private ObservableList<Item> items;

    private final double TAXRATE = .08;
    private double total;
    private double taxes;

    /**
     * Initializes the view
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dao = new BillingMenuDAO();
        initGuestsTable();
        initChargesTable();
        initItemPane();
    }

    /**
     * Handles the click event on Cash Payment button
     *
     * @param event
     */
    @FXML
    private void onClickCashPayment(ActionEvent event) {
        try {
            System.out.println("Starting cash payment");

            double cashAmount = Double.parseDouble(txtAmount.getText());
            double owedAmount = total;

            System.out.println("cashAmount:" + cashAmount);
            System.out.println("owedAmount:" + owedAmount);

            if (cashAmount > owedAmount) {
                return;
            }

            Item item = new Item("Cash", "Cash Deposit", BillingCode.OTHER, cashAmount * -1);

            addCharge(item);

        } catch (Exception e) {
            System.out.println("Error processing cash payment");
        }
    }

    /**
     * Handles the click event on Delete Button
     *
     * @param event
     */
    @FXML
    private void onClickDeleteCharge(ActionEvent event) {
        FolioCharge charge = tblCharges.getSelectionModel().getSelectedItem();
        if (charge == null) {
            return;
        }
        try {

            dao.delCharge(charge.getId());
        } catch (Exception t) {
            System.out.println("error deleting charge");
        }
        handleSelectGuest();
    }

    /**
     * Handles the click event on the select guest button
     *
     * @param event
     */
    @FXML
    private void onClickSelectGuest(ActionEvent event) {
        charges.clear();
        Label msg = new Label("Loading Guest Folio...");
        msg.setFont(new Font(24));
        msg.setOpacity(.5);
        tblCharges.setPlaceholder(msg);
        btnSelectGuest.setDisable(true);
        handleSelectGuest();
    }

    /**
     * Handles the click event on the print folio button
     *
     * @param event
     */
    @FXML
    private void onClickPrintFolio(ActionEvent event) {

//        PrinterJob printerJob = PrinterJob.createPrinterJob();
//        if (printerJob == null) {
//            System.out.println("Null");
//        }
//        printerJob.showPrintDialog(HMS.stage);
    }

    /**
     * Handles the click event on the click checkout button
     *
     * @param event
     */
    @FXML
    private void onClickCheckOut(ActionEvent event) {
        handleCheckOut();
    }

    /**
     * Handles the selection event on the radio button to select active guests.
     *
     * @param event
     */
    @FXML
    public void onSelectRadioActive(ActionEvent event) {
        charges.clear();
        btnCheckOut.setDisable(false);
        btnDeleteCharge.setDisable(false);
        btnCashPayment.setDisable(false);
        txtAmount.setDisable(false);
        spItems.setVisible(true);
        itemDetailPane.setVisible(true);
        calculateTotals();
        lblGuestName.setText("");
        updateActiveGuests();
    }

    /**
     * Handles the selection event on the radio button to select past guests.
     *
     * @param event
     */
    @FXML
    private void onSelectRadioPast(ActionEvent event) {
        charges.clear();
        btnCheckOut.setDisable(true);
        btnDeleteCharge.setDisable(true);
        btnCashPayment.setDisable(true);
        txtAmount.setDisable(true);
        spItems.setVisible(false);
        itemDetailPane.setVisible(false);
        lblGuestName.setText("");
        calculateTotals();
        updatePastGuests();
    }

    /**
     * Method to inject a link to the main menu controller
     *
     * @param main
     */
    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    /**
     * Method to inject the link to the current logged in user account
     *
     * @param user
     */
    @Override
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Method to inject the link to the current reservation
     *
     * @param currentReservation
     */
    public void setCurrentReservation(Reservation currentReservation) {
        this.currentReservation = currentReservation;
    }

    /**
     * Initializes the guests table
     */
    private void initGuestsTable() {

        guests = FXCollections.observableArrayList();
        tblGuests.setItems(guests);

        //Set Column Values
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

        //Add Listener
        tblGuests.setOnMouseClicked(e -> {
            BillingMenuDTO selectedGuest = tblGuests.getSelectionModel().getSelectedItem();
            if (selectedGuest != null) {
                lblGuestName.setText(selectedGuest.getLastName() + ", " + selectedGuest.getFirstName());
                updateCharges(selectedGuest);
            }
        });

        //Populate with data
        updateActiveGuests();
    }

    /**
     * Initializes the charges table
     */
    private void initChargesTable() {

        charges = FXCollections.observableArrayList();
        tblCharges.setItems(charges);

        //Set Column Values
        colCode.setCellValueFactory(
                new PropertyValueFactory<>("Code"));
        colDescription.setCellValueFactory(
                new PropertyValueFactory<>("Description"));
        colDate.setCellValueFactory(
                new PropertyValueFactory<>("Date"));
        colAmount.setCellValueFactory(
                new PropertyValueFactory<>("Amount"));
        colAmount.setCellValueFactory(cellData
                -> Bindings.format("%.2f", cellData.getValue().getAmount())
        );

        //Set Default Message
        Label msg = new Label("Charges");
        msg.setFont(new Font(24));
        msg.setOpacity(.5);
        tblCharges.setPlaceholder(msg);

    }

    /**
     * Initializes the items pane
     */
    private void initItemPane() {

        items = FXCollections.observableArrayList();

        //Retrieves list of items from BillingMenuDAO
        System.out.println("Retrieving list of Items from BillingMenuDAO");
        try {
            ObservableList result = dao.queryItems();
            if (result != null) {
                items = result;
            }

        } catch (Exception e) {
            System.out.println("Error getting current item list");
        }

        //Adds items to item pane
        for (Item item : items) {
            item.setOnMouseEntered(e -> {
                lblItem.textProperty().bind(item.nameProperty());
                lblDescription.textProperty().bind(item.descriptionProperty());
                lblPrice.textProperty().bind(item.priceProperty().asString("$%4.2f"));
            });
            item.setOnMouseExited(e -> {
                lblItem.textProperty().unbind();
                lblDescription.textProperty().unbind();
                lblPrice.textProperty().unbind();
            });
            item.setOnMouseClicked(e -> {
                addCharge(item);
            });
            itemPane.getChildren().add(item);
        }
    }

    /**
     * Handles the select guest event
     */
    private void handleSelectGuest() {
        BillingMenuDTO selectedGuest = tblGuests.getSelectionModel().getSelectedItem();

        if (selectedGuest == null) {
            Alert alert = new Alert(AlertType.WARNING, "Please select a guest from the list");
            alert.showAndWait();
            btnSelectGuest.setDisable(false);
            return;
        }

        lblGuestName.setText(selectedGuest.getLastName() + ", " + selectedGuest.getFirstName());
        updateCharges(selectedGuest);
    }

    /**
     * Calculates the folio totals
     */
    private void calculateTotals() {
        CurrencyStringConverter csc = new CurrencyStringConverter();
        total = 0;
        taxes = 0;
        double subtotal = 0;
        
        subtotal = tblCharges.getItems().stream().mapToDouble(FolioCharge::getAmount).sum();
        taxes = subtotal * TAXRATE;
        if (radioPast.isSelected()){
            taxes = 0;
        }
        total = subtotal + taxes;
        lblTotal.setText(csc.toString(total));
        lblTaxes.setText(csc.toString(taxes));
        lblSubTotal.setText(csc.toString(subtotal));
    }

    /**
     * Refreshes the tables to display active guests only
     */
    public void updateActiveGuests() {
        try {
            guests.clear();
            charges.clear();
            System.out.println("Retrieving list of Active Guests from BillingMenuDAO");
            ObservableList<BillingMenuDTO> result = dao.queryCurrentGuests();
            if (result != null) {
                guests.setAll(result);
            }
            for (BillingMenuDTO d : result) {
                System.out.println(d);
            }
        } catch (Exception e) {
            System.out.println("Error getting current guest list");
        }
    }

    /**
     * Refreshes the tables to display past guests only
     */
    public void updatePastGuests() {
        guests.clear();
        try {
            System.out.println("Retrieving list of Past Guests from BillingMenuDAO");
            ObservableList<BillingMenuDTO> result = dao.queryPastGuests();
            if (result != null) {
                guests.setAll(result);
            }
            for (BillingMenuDTO d : result) {
                System.out.println(d);
            }
        } catch (Exception e) {
            System.out.println("Error getting current guest list");
        }

    }

    /**
     * Updates the charges in the charges table
     *
     * @param selectedGuest
     */
    private void updateCharges(BillingMenuDTO selectedGuest) {
        try {

            //First calculate the room charges for the guest dynamically
            postRoomCharges(selectedGuest);

            //Next grab the line items for the guest from the dao
            System.out.println("Retriving list of charges for guest: "
                    + selectedGuest.getConfirmation()
                    + " "
                    + selectedGuest.getFirstName()
                    + " "
                    + selectedGuest.getLastName()
                    + " "
                    + selectedGuest.getRoomNumber());
            charges = dao.queryCharges(selectedGuest.getConfirmation());
            tblCharges.setItems(charges);
            calculateTotals();
        } catch (Exception e) {
            System.out.println("Error retrieving current guest list");
        }
        if (radioActive.isSelected()) {
            postRoomCharges(selectedGuest);
        }
    }

    /**
     * Adds a charge for the guest to the database
     *
     * @param item
     */
    private void addCharge(Item item) {
        try {

            //Get Confirmation Number
            int confirmationNumber = tblGuests
                    .getSelectionModel()
                    .getSelectedItem()
                    .getConfirmation();

            //Create Charge 
            FolioCharge charge = new FolioCharge(
                    0,
                    item.getCode(),
                    item.getDescription(),
                    LocalDate.now().toString(),
                    item.getPrice()
            );
            System.out.println("Adding Charge...");
            dao.addCharge(confirmationNumber, charge);
        } catch (Exception e) {
            System.out.println("Error adding Charge");
        }
        //Refresh the tables
        handleSelectGuest();
    }

    /**
     * Calculate and post the room charges
     *
     * @param guest
     */
    private void postRoomCharges(BillingMenuDTO guest) {
        try {

            //Delete Old Room Charges
            System.out.println("Deleting old Room Charges...");
            dao.deleteRoomDays(guest.getConfirmation());

            //Get Cost of Room
            System.out.println("Getting Cost of the room");
            double rate = dao.getRoomRate(guest.getRoomtype(), guest.getRatecode());
            if (rate == 0) {
                rate = 201.00;
            }

            //Add Room Charges from start of reservation to todays date
            LocalDate today = LocalDate.now();
            LocalDate start = LocalDate.parse(guest.getArrivalDate());
            long x = ChronoUnit.DAYS.between(start, today);

            for (; x >= 0; x--) {
                String tmpDate = start.toString();

                //Create Charge 
                FolioCharge charge = new FolioCharge(
                        0,
                        BillingCode.ROOM,
                        "Room#" + guest.getRoomNumber(),
                        tmpDate,
                        rate);

                System.out.println("Posting charge for : " + tmpDate);
                dao.addCharge(guest.getConfirmation(), charge);

                //Increment the day
                start = start.plusDays(1);
            }

        } catch (Exception e) {
            System.out.println("Error posting room transactions");
        }

        //Re-Enable the button
        Label msg = new Label("Guest Charges");
        msg.setFont(new Font(24));
        msg.setOpacity(.5);
        tblCharges.setPlaceholder(msg);
        btnSelectGuest.setDisable(false);

    }

    /**
     * Handle the guest checkout
     */
    private void handleCheckOut() {
        BillingMenuDTO guest = tblGuests.getSelectionModel().getSelectedItem();
        if (guest == null) {
            return;
        }

        //Make sure the table calculates the current values
        handleSelectGuest();

        //Call database to checkout the guset
        boolean result = false;
        double billedTax = taxes;
        double billedTotal = total;
        try {
            System.out.println("Checking Out Guest");
            result = dao.checkOutGuest(guest.getConfirmation(),
                    guest.getRoomNumber(),
                    billedTotal,
                    guest.getCc_last4());
            
            //Add Billing for tax
            Item tax = new Item("Taxes", "Tax", BillingCode.OTHER, billedTax);
            addCharge(tax);
        } catch (Exception e) {
            System.out.println("Error while checking out the guest");
        }
        if (result) {
            updateActiveGuests();
            Alert alert = new Alert(AlertType.INFORMATION,
                    String.format(
                            "You are checked out of room %s\nYour "
                            + "credit card ending in %s\n"
                            + "has been billed $%.2f",
                            guest.getRoomNumber(),
                            guest.getCc_last4(),
                            billedTotal));
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR,
                    "You're checkout could not be processed at this time");
            alert.showAndWait();
        }
        
        calculateTotals();
        lblGuestName.setText("");
    }

}
