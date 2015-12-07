/*
 * Controller for the Reservations Profile view
 */
package hms;

import hms.model.FrontDeskArrivalsDTO;
import hms.model.FrontDeskArrivalsDTOBuilder;
import hms.model.ProfileSearchDTOBuilder;
import hms.model.ProfileSearchDTO;
import hms.model.FrontDeskDAO;
import hms.model.Profile;
import hms.model.Reservation;
import hms.model.ReservationStatus;
import hms.model.User;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Team SLAM
 */
public class ReservationsProfileMenuController implements Initializable, SubMenu {

    @FXML
    private BorderPane reservationsProfilePane;
    @FXML
    private GridPane reservationsGridPane;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtConfirmation;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private DatePicker dateArrival;
    @FXML
    private DatePicker dateDeparture;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnSearchReservations;
    @FXML
    private Button btnSearchProfiles;
    @FXML
    private TextField txtMemberID;
    @FXML
    private TextField txtEmail;
    @FXML
    private TableView<Reservation> tblReservations;
    @FXML
    private TableColumn<Reservation, Integer> colConfirmation;
    @FXML
    private TableColumn<Reservation, String> colFirstName;
    @FXML
    private TableColumn<Reservation, String> colLastName;
    @FXML
    private TableColumn<Reservation, Integer> colRoomNumber;
    @FXML
    private TableColumn<Reservation, String> colRoomType;
    @FXML
    private TableColumn<Reservation, Double> colRoomRate;
    @FXML
    private TableColumn<Reservation, Integer> colNumberAdults;
    @FXML
    private TableColumn<Reservation, Integer> colNumberChildren;
    @FXML
    private TableColumn<Reservation, String> colArrival;
    @FXML
    private TableColumn<Reservation, String> colDeparture;
    @FXML
    private TableColumn<Reservation, String> colStatus;
    @FXML
    private TableColumn<Reservation, String> colComments;
    @FXML
    private TableView<Profile> tblProfiles;
    @FXML
    private TableColumn<Profile, Integer> colMemberID;
    @FXML
    private TableColumn<Profile, String> colFirstNameProfile;
    @FXML
    private TableColumn<Profile, Integer> colLastNameProfile;
    @FXML
    private TableColumn<Profile, Boolean> colVIP;
    @FXML
    private TableColumn<Profile, String> colPhoneNumber;
    @FXML
    private TableColumn<Profile, String> colEmail;
    @FXML
    private TableColumn<Profile, String> colNotes;
    @FXML
    private Button btnNewReservation;
    @FXML
    private Button btnEditReservation;
    @FXML
    private Button btnCancelReservation;
    @FXML
    private Button btnNewProfile;
    @FXML
    private Button btnEditProfile;
    @FXML
    private Button btnDeleteProfile;

    private MainMenuController main;
    private User user;

    private ObservableList<Reservation> reservations;
    private ObservableList<Profile> profiles;
    private FrontDeskDAO dao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dao = new FrontDeskDAO();

        setupReservationsTable();
        setupProfilesTable();
        handleSearch();

        initAutoSearch();
    }

    /**
     * Clear Button
     *
     * @param event
     */
    @FXML
    private void onClickClear(ActionEvent event) {
        handleClear();
    }

    /**
     * New Reservation button click
     *
     * @param event
     */
    @FXML
    private void onClickNewReservation(ActionEvent event) {
        handleNewReservation();
    }

    /**
     * Edit Reservation button click
     *
     * @param event
     */
    @FXML
    private void onClickEditReservation(ActionEvent event) {
        handleEditReservation();
    }

    /**
     * Cancel Reservation button click
     *
     * @param event
     */
    @FXML
    private void onClickCancelReservation(ActionEvent event) {
        handleCancelReservation();

    }

    /**
     * New Profile button click
     *
     * @param event
     */
    @FXML
    private void onClickNewProfile(ActionEvent event) {
        handleNewProfile();
    }

    /**
     * Edit Profile button click
     *
     * @param event
     */
    @FXML
    private void onClickEditProfile(ActionEvent event) {
        handleEditProfile();
    }

    /**
     * Delete profile button click
     *
     * @param event
     */
    @FXML
    private void onClickDeleteProfile(ActionEvent event) {
        handleDeleteProfile();
    }

    /**
     * Search Profile button click
     *
     * @param event
     */
    @FXML
    private void onClickSearchProfiles(ActionEvent event) {
        reservations.clear();
        profiles.clear();
        handleSearchProfiles();
    }

    /**
     * Search reservations button click
     *
     * @param event
     */
    @FXML
    private void onClickSearchReservations(ActionEvent event) {
        reservations.clear();
        profiles.clear();
        handleSearchReservations();
    }

    /**
     * Search button click
     *
     * @param event
     */
    @FXML
    private void onClickSearch(ActionEvent event) {
        reservations.clear();
        profiles.clear();
        handleSearch();
    }

    /**
     * injects link to main menu controller
     *
     * @param main
     */
    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    /**
     * injects link to current user
     *
     * @param e
     */
    @Override
    public void setUser(User e) {
        this.user = user;
    }

    /**
     * initializes the reservations table
     */
    private void setupReservationsTable() {

        reservations = FXCollections.observableArrayList();
        tblReservations.setItems(reservations);

        colConfirmation.setCellValueFactory(
                new PropertyValueFactory<>("Confirmation"));
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

        Label msg = new Label("Reservations");
        msg.setFont(new Font(48));
        msg.setOpacity(.5);
        tblReservations.setPlaceholder(msg);
    }

    /**
     * Initializes the profiles table
     */
    private void setupProfilesTable() {

        profiles = FXCollections.observableArrayList();
        tblProfiles.setItems(profiles);

        colMemberID.setCellValueFactory(
                new PropertyValueFactory<>("MemberID"));
        colVIP.setCellValueFactory(
                new PropertyValueFactory<>("VIP"));
        colFirstNameProfile.setCellValueFactory(
                new PropertyValueFactory<>("FirstName"));
        colLastNameProfile.setCellValueFactory(
                new PropertyValueFactory<>("LastName"));
        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("Email"));
        colPhoneNumber.setCellValueFactory(
                new PropertyValueFactory<>("PhoneNumber"));
        colNotes.setCellValueFactory(
                new PropertyValueFactory<>("Notes"));

        Label msg = new Label("Profiles");
        msg.setFont(new Font(48));
        msg.setOpacity(.5);
        tblProfiles.setPlaceholder(msg);
    }

    /**
     * Clear button click
     */
    private void handleClear() {

        handleClearReservations();
        handleClearProfiles();

    }

    /**
     * Handles clearing the reservations table
     */
    private void handleClearReservations() {

        //Clear Garbage Text from Date Field
        dateArrival.setValue(LocalDate.now());
        dateArrival.setValue(LocalDate.now());

        //Clear Reservations
        reservations.clear();
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPhoneNumber.setText("");
        txtConfirmation.setText("");
        dateArrival.setValue(null);
        dateDeparture.setValue(null);
    }

    /**
     * Handles clearing the profiles table
     */
    private void handleClearProfiles() {
        //Clear Profiles
        profiles.clear();
        txtEmail.setText("");
        txtPhoneNumber.setText("");
        txtMemberID.setText("");
    }

    /**
     * Handles searching for reservations
     */
    private void handleSearchReservations() {

        //validate fields
        if (!validateReservationFields()) {
            System.out.println("Error validating reservation fields");
            return;
        }

        try {

            ObservableList<Reservation> result;

            String arriveDate;
            String departDate;

            //Check dates and convert to string
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

            //Build frontdeskarrivals DTO
            FrontDeskArrivalsDTO dto
                    = new FrontDeskArrivalsDTOBuilder()
                    .setFirstName(txtFirstName.getText())
                    .setLastName(txtLastName.getText())
                    .setCompanyName("")
                    .setGroupName("")
                    .setConfirmation(txtConfirmation.getText())
                    .setPhoneNumber(txtPhoneNumber.getText())
                    .setArrivalDate(arriveDate)
                    .setDepartureDate(departDate)
                    .createQueryArrivalsDTO();

            //Query database
            System.out.println("Query arrivals");
            result = dao.queryArrivals(dto);

            if (result != null) {
                reservations.setAll(result);
            }
        } catch (Exception e) {
            System.out.println("Error processing query");
        }
    }

    /**
     * Handles creating a new reservation
     */
    private void handleNewReservation() {
        Profile p = tblProfiles.getSelectionModel().getSelectedItem();

        //Prompt and return if a reservation is not selected.
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please select a guest profile to \n"
                    + "attach to the reservation");
            alert.showAndWait();
            return;
        }
        //Forms.displayReserveRoomForm(main, p);
        Reservation newReservation = Forms.displayCreateReservationForm(main, p);
        if (newReservation != null) {
            //tblReservations.getItems().add(newReservation);
            reservations.clear();
            txtConfirmation.setText(newReservation.getConfirmation() + "");
            btnSearch.fire();
            txtConfirmation.setText("");
        } else {
            System.out.println("It was null");
        }
    }

    /**
     * handles canceling a reservation
     */
    private void handleCancelReservation() {

        Alert alert;
        Optional<ButtonType> response;

        //Get Selected Reservation
        Reservation r = tblReservations.getSelectionModel().getSelectedItem();

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
            System.out.println("Delete Cancelled");
            return;
        }

        //Cancel the reservation
        boolean result = false;
        result = dao.cancelReservation(r.getConfirmation());

        if (result) {
            r.setStatus("Cancelled");
            tblReservations.getItems().remove(r);
            System.out.println("Reservation cancelled" + r.getLastName());
        } else {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Record can not be deleted at this time");
            alert.showAndWait();
        }

    }

    /**
     * handles editing a reservation
     */
    private void handleEditReservation() {

        //Get Selected Reservation
        Reservation r = tblReservations.getSelectionModel().getSelectedItem();

        //Prompt and return if a reservation is not selected.
        if (r == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Reservation not Selected");
            alert.showAndWait();
            return;
        }

        //Check to make sure that the status is pending
        if (!r.getStatus().equals(ReservationStatus.PENDING)) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Only reservations that are PENDING \n"
                    + "can be edited");
            alert.showAndWait();
            return;
        }

        //Launch reservation edit form
        Reservation newReservation = Forms.displayEditReservationForm(main, r);
        if (newReservation != null) {
            System.out.println("Not null, replacing in table");
            tblReservations.getItems().remove(r);
            tblReservations.getItems().add(newReservation);
        } else {
            System.out.println("Null. not changing table");
        }
    }

    /**
     * handles searching for profiles
     */
    private void handleSearchProfiles() {

        try {

            //Validate fields
            if (!validateProfileFields()) {
                System.out.println("Error validating profile fields");
                return;
            }

            ObservableList<Profile> result;

            //Build DTO
            ProfileSearchDTO dto = new ProfileSearchDTOBuilder()
                    .setFirstName(txtFirstName.getText())
                    .setLastName(txtLastName.getText())
                    .setEmail(txtEmail.getText())
                    .setMemberID(txtMemberID.getText())
                    .setPhoneNumber(txtPhoneNumber.getText())
                    .createProfileSearchDTO();

            //Query Database
            System.out.println("Query Profiles");
            result = dao.queryProfiles(dto);

            if (result != null) {
                profiles.setAll(result);
            }

        } catch (Exception e) {
            System.out.println("Error processing query");
        }
    }

    /**
     * Displays the create new profile form
     */
    private void handleNewProfile() {
        Profile newProfile = Forms.displayCreateProfileForm(main);
        if (newProfile != null) {
            tblProfiles.getItems().add(newProfile);
        }
    }

    /**
     * Deletes a profile
     */
    private void handleDeleteProfile() {
        //TO BE IMPLEMENTED - Shouldn't delete with Checked in guest....
    }

    /**
     * Displays the edit profile form
     */
    private void handleEditProfile() {
        Profile p = tblProfiles.getSelectionModel().getSelectedItem();

        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please select a profile to edit");
            alert.showAndWait();
            return;
        }
        Profile newProfile = Forms.displayEditProfileForm(main, p);
        if (newProfile != null) {
            tblProfiles.getItems().remove(p);
            tblProfiles.getItems().add(newProfile);
        }
    }

    /**
     * Validates reservation fields
     * @return 
     */
    private boolean validateReservationFields() {
        boolean valid = true;

        try {

            //validate phone number
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
     * Validates Profile Fields
     * @return 
     */
    private boolean validateProfileFields() {
        boolean valid = true;

        try {

            //validate phone number
            if (validatePhoneNumber(txtPhoneNumber.getText())) {
                markValid(txtPhoneNumber);
            } else {
                markInvalid(txtPhoneNumber);
                valid = false;
            }
            
            //validate email
            final Pattern p = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
            if (txtEmail.getText().equals("")) {
                markValid(txtEmail);
            }
            else if (p.matcher(txtEmail.getText()).matches()) {
                markValid(txtEmail);
            } else {
                markInvalid(txtEmail);
            }
            
        } catch (Exception e) {
            System.out.println("Error Validating Fields");
        }

        return valid;
    }

    /**
     * Helper method to validate a phone number
     *
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
     * Helper method to mark a field invalid
     * @param t 
     */
    private void markInvalid(TextField t) {
        t.setStyle("-fx-border-color: red ; -fx-border-width: 3px ;");

    }

    /**
     * Helper method to mark a field valid
     * @param t 
     */
    private void markValid(TextField t) {
        t.setStyle("");

    }

    /**
     * Handles search for both reservations and profiles
     */
    private void handleSearch() {
        reservations.clear();
        profiles.clear();
        handleSearchProfiles();
        handleSearchReservations();
    }

    /**
     * sets up fields to search the correct criteria on enter
     */
    private void initAutoSearch() {
        txtFirstName.setOnAction(e -> {
            handleSearch();
        });
        txtLastName.setOnAction(e -> {
            handleSearch();
        });
        txtMemberID.setOnAction(e -> {
            handleSearchProfiles();
        });
        txtEmail.setOnAction(e -> {
            handleSearchProfiles();
        });
        txtPhoneNumber.setOnAction(e -> {
            handleSearch();
        });
    }
}
