/*
 * Controls the Front Desk Menu View
 */
package hms;

import hms.model.User;
import hms.model.FrontDeskArrivalsDTO;
import hms.model.FrontDeskDAO;
import hms.model.FrontDeskArrivalsDTOBuilder;
import hms.model.Profile;
import hms.model.Reservation;
import hms.model.ReservationStatus;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 * FrontDeskMenuController class
 *
 * @author TeamSlam
 */
public class FrontDeskMenuController implements Initializable, SubMenu {

    @FXML
    private Button btnArrivals;
    @FXML
    private Button btnDepartures;
    @FXML
    private Button btnInHouseGuests;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtCompanyName;
    @FXML
    private TextField txtGroupName;
    @FXML
    private DatePicker dateArrival;
    @FXML
    private DatePicker dateDeparture;
    @FXML
    private TextField txtConfirmation;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TableView<Reservation> tblFrontDesk;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnWalkIn;
    @FXML
    private Button btnEditReservation;
    @FXML
    private Button btnEditProfile;
    @FXML
    private Button btnCancelReservation;
    @FXML
    private Button btnCheckIn;
    @FXML
    private BorderPane frontDeskPane;
    @FXML
    private TableColumn<Reservation, String> colFirstName, colLastName, colArrival,
            colDeparture, colStatus, colComments, colRoomType;

    @FXML
    private TableColumn<Reservation, Integer> colNumberAdults,
            colNumberChildren, colRoomNumber;

    @FXML
    private TableColumn<Reservation, Double> colRoomRate;

    private MainMenuController main;
    private User currentUser;
    private ObservableList<Reservation> data;
    private FrontDeskDAO dao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dao = new FrontDeskDAO();
        setupTable();
        initTableValues();

    }

    /**
     * Handles the click on Departures button
     * @param event 
     */
    @FXML
    private void onClickDepartures(ActionEvent event) {
        handleClear();
        dateDeparture.setValue(LocalDate.now());
        handleSearch();
    }
    
    /**
     * Handles the click on the Arrivals button
     * @param event 
     */
    @FXML
    public void onClickArrivals(ActionEvent event) {
        handleClear();
        dateArrival.setValue(LocalDate.now());
        handleSearch();
    }

    /**
     * Handles the click on the HouseGuests Button
     * @param event 
     */
    @FXML
    private void onClickInHouseGuests(ActionEvent event) {
        //Todo Create IN House Guests menu
    }

    /**
     * Click on Search Button
     * @param event 
     */
    @FXML
    private void onClickSearch(ActionEvent event) {
        handleSearch();
    }

    /**
     * Click on Clear Button
     * @param event 
     */
    @FXML
    private void onClickClear(ActionEvent event) {
        handleClear();
    }

    /**
     * Click on WalkIns button
     * @param event 
     */
    @FXML
    private void onClickWalkIn(ActionEvent event) {
        // TODO
    }

    /**
     * Click on Reservations button
     * @param event 
     */
    @FXML
    private void onClickEditReservation(ActionEvent event) {
        handleEditReservation();
    }

    /**
     * Click on Edit Profile Button
     * @param event 
     */
    @FXML
    private void onClickEditProfile(ActionEvent event) {
        handleEditProfile();
    }

    /**
     * Click on Cancel reservation button
     * @param event 
     */
    @FXML
    private void onClickCancelReservation(ActionEvent event) {
        handleCancel();
    }

    /**
     * Click on checkin button
     * @param event 
     */
    @FXML
    private void onClickCheckIn(ActionEvent event) {
        handleCheckIn();
    }

    /**
     * Injects link to main menu controller
     * @param main 
     */
    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    /**
     * Injects link to current logged in user
     * @param user 
     */
    @Override
    public void setUser(User user) {
        currentUser = user;
    }

    /**
     * Initializes the front desk table
     */
    private void setupTable() {

        data = FXCollections.observableArrayList();

        tblFrontDesk.setItems(data);

        colFirstName.setCellValueFactory(
                new PropertyValueFactory<>("FirstName"));
        colLastName.setCellValueFactory(
                new PropertyValueFactory<>("LastName"));
        colRoomNumber.setCellValueFactory(
                new PropertyValueFactory<>("RoomNumber"));
        colRoomType.setCellValueFactory(
                new PropertyValueFactory<>("RoomType"));
        colRoomRate.setCellValueFactory(
                new PropertyValueFactory<>("RoomRate"));
        colNumberChildren.setCellValueFactory(
                new PropertyValueFactory<>("NumberChildren"));
        colNumberAdults.setCellValueFactory(
                new PropertyValueFactory<>("NumberAdults"));
        colArrival.setCellValueFactory(
                new PropertyValueFactory<>("CheckinDate"));
        colDeparture.setCellValueFactory(
                new PropertyValueFactory<>("CheckoutDate"));
        colStatus.setCellValueFactory(
                new PropertyValueFactory<>("Status"));
        colComments.setCellValueFactory(
                new PropertyValueFactory<>("Comments"));

        //Set Default Message
        Label msg = new Label("Reservations");
        msg.setFont(new Font(24));
        msg.setOpacity(.5);
        msg.setStyle("-fx-font-size: 24;");
        tblFrontDesk.setPlaceholder(msg);

        //Populate with data
        dateArrival.setValue(LocalDate.now());
        handleSearch();
    }

    /**
     * handles searching for guests that match the criteria
     */
    private void handleSearch() {

        //Validate the fields
        if (!validateFields()) {
            System.out.println("Error validatting fields");
            return;
        }


        //Build FrontDeskDAO and query database
        ObservableList<Reservation> result;
            String arriveDate;
            String departDate;

            try {
                arriveDate = dateArrival.getValue().toString();
            } catch (Exception ex) {
                arriveDate = "";
            }

            try {
                departDate = dateDeparture.getValue().toString();
            } catch (Exception e) {
                departDate = "";
            }
        
        System.out.println("Building FrontDeskArrivals DTO");
        FrontDeskArrivalsDTO dto
                = new FrontDeskArrivalsDTOBuilder()
                .setFirstName(txtFirstName.getText())
                .setLastName(txtLastName.getText())
                .setCompanyName(txtCompanyName.getText())
                .setGroupName(txtGroupName.getText())
                .setConfirmation(txtConfirmation.getText())
                .setPhoneNumber(txtPhoneNumber.getText())
                .setArrivalDate(arriveDate)
                .setDepartureDate(departDate)
                .createQueryArrivalsDTO();

        System.out.println("Fetching reservations from db");
        result = dao.queryArrivals(dto);
        
        if (result != null) {
            System.out.println("Setting Result");
            data.setAll(result);
        }

    }

    /**
     * Validate the user fields
     * @return 
     */
    private boolean validateFields() {
        boolean valid = true;
            
        try {
            
            //validate phone number
            System.out.println("Validating phone");
            if (validatePhoneNumber(txtPhoneNumber.getText())) {
                markValid(txtPhoneNumber);
            } else {
                markInvalid(txtPhoneNumber);
                valid = false;
            }
            
            //validate date field
            
            if (dateArrival.getValue().isAfter(dateDeparture.getValue())) {
                dateArrival.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                dateDeparture.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                valid = false;
            } else {
                dateArrival.setStyle("");
                dateDeparture.setStyle("");
            }
                    
        } catch (Exception e) {
            System.out.println("Error Validating Fields");
        }

        return valid;
    }

    /**
     * Handles the cancel reservation
     */
    private void handleCancel() {

        Alert alert;
        Optional<ButtonType> response;

        //Get Selected Reservation
        Reservation r = tblFrontDesk.getSelectionModel().getSelectedItem();

        //If reservation is null, display message and return
        if (r == null) {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Reservation not Selected");
            alert.showAndWait();
            return;
        }

        //Check to make sure that the status is pending
        if (!r.getStatus().equals(ReservationStatus.PENDING)) {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Only reservations that are PENDING \n"
                    + "can be cancelled");
            alert.showAndWait();
            return;
        }

        //Confirm the delete of the reservation
        alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you would like to cancel the\n"
                + "reservation for " + r.getFirstName()
                + " " + r.getLastName());
        response = alert.showAndWait();

        //Response is not yes, return
        if (response.get() != ButtonType.OK) {
            return;
        }

        //Cancel the reservation
        boolean result = false;
        result = dao.cancelReservation(r.getConfirmation());

        if (result) {
            r.setStatus("Cancelled");
            tblFrontDesk.getItems().remove(r);
        } else {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Record can not be deleted at this time");
            alert.showAndWait();
        }

    }

    /**
     * Handles the Edit Profile Event
     */
    private void handleEditProfile() {
        Alert alert;
        Optional<ButtonType> response;

        //Get Selected Reservation
        Reservation r = tblFrontDesk.getSelectionModel().getSelectedItem();

        //If reservation is null, display message and return
        if (r == null) {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Reservation not Selected");
            alert.showAndWait();
            return;
        }

        //Get Profile From DB
        Profile profile = null;
        try {
            profile = dao.getProfile(r.getProfileID());
        } catch (Exception e) {
            System.out.println("Could not get Profile from db");
        }

        if (profile != null) {
            profile = Forms.displayEditProfileForm(main, profile);
        }

        if (profile != null) {
            alert = new Alert(Alert.AlertType.INFORMATION,
                    "Profile for " + profile.getFirstName()
                    + profile.getLastName() + "edited Successfully.");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Profile could not be edited.");
            alert.showAndWait();
        }

    }

    /**
     * Handle edit reservation event
     */
    private void handleEditReservation() {

        //Get Selected Reservation
        Reservation r = tblFrontDesk.getSelectionModel().getSelectedItem();

        //Prompt and return if a reservation is not selected.
        if (r == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Reservation not Selected");
            alert.showAndWait();
            return;
        }

        //Forms.displayReserveRoomForm(main, r);
        Reservation newReservation = Forms.displayEditReservationForm(main, r);
        if (newReservation != null) {
            System.out.println("Not null, replacing in table");
            tblFrontDesk.getItems().add(newReservation);
            tblFrontDesk.getItems().remove(r);
        } else {
            System.out.println("Null. not changing table");
        }
    }

    /**
     * Handle clear table event
     */
    private void handleClear() {
        data.clear();
        txtFirstName.setText("");
        txtLastName.setText("");
        txtGroupName.setText("");
        txtCompanyName.setText("");
        txtPhoneNumber.setText("");
        txtConfirmation.setText("");
        dateArrival.setValue(null);
        dateDeparture.setValue(null);
    }

    /**
     * Helper method to mark a field invalid
     * @param t 
     */
    private void markInvalid(TextField t) {
        t.setStyle("-fx-border-color: red ; -fx-border-width: 3px ;");

    }

    /**
     * Helper method to mark field valid
     * @param t 
     */
    private void markValid(TextField t) {
        t.setStyle("");

    }

    /**
     * Helper method to validate a phone number
     * @param phoneNo
     * @return 
     */
    private boolean validatePhoneNumber(String phoneNo) {
        
        //returns true if there is no phone number in the field.
        if (phoneNo.matches("")) {
            return true;
        // returns true if phone number is correct     
        } else if (phoneNo.matches("\\d{3}[-]\\d{3}[-]\\d{4}")) {
            return true;
        } //return false if nothing matches the input
        else {
            return false;
        }

    }
    
    /**
     * Handle check in event
     */
    private void handleCheckIn() {
        Alert alert;
        Optional<ButtonType> response;

        //Get Selected Reservation
        Reservation r = tblFrontDesk.getSelectionModel().getSelectedItem();
        
        System.out.println("Handling Checking reservation:" + r);
        
        //If reservation is null, display message and return
        if (r == null) {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Reservation not Selected");
            alert.showAndWait();
            return;
        }

        //Check to make sure that the status is pending
        if (!r.getStatus().equals(ReservationStatus.PENDING)) {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Only reservations that are PENDING \n"
                    + "can be CheckedIn");
            alert.showAndWait();
            return;
        }

        //Check to make sure that the checkIn Date is today
        if (!r.getCheckinDate().equals(LocalDate.now().toString())) {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Only reservations with today's arrival \n"
                    + "date can be CheckedIn");
            alert.showAndWait();
            return;
        }

        boolean result = Forms.displayCheckInForm(main, r);

        if (result) {
            String msg = "Guest was successfully Checked in.\n\n"
                    + "Name: " + r.getFirstName() + " " + r.getLastName() + "\n"
                    + "Room: " + r.getRoomNumber() + "   " + "RoomType: " + r.getRoomType() + "\n"
                    + "Arrival Date: " + r.getCheckinDate() + "\n"
                    + "Departure Date: " + r.getCheckoutDate() + "\n\n"
                    + "Enjoy your stay!";

            alert = new Alert(Alert.AlertType.INFORMATION, msg);
        } else {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Guest could not be checked in at this time");
        }
        alert.showAndWait();
        btnArrivals.fire();
    }

    /**
     * Initialize front desk table with current date values
     */
    private void initTableValues() {
        dateArrival.setValue(LocalDate.now());
        this.btnSearch.fire();
    }

}
