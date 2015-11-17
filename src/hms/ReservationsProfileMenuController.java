/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author jgreene
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
    private TextField txtCompanyName;
    @FXML
    private TextField txtGroupName;
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
    private TableColumn<Profile, String> colTitle;
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

    }

    @FXML
    private void onClickClear(ActionEvent event) {
        handleClear();
    }

    @FXML
    private void onClickSearchReservations(ActionEvent event) {
        handleSearchReservations();
    }

    @FXML
    private void onClickNewReservation(ActionEvent event) {
        handleNewReservation();
    }

    @FXML
    private void onClickEditReservation(ActionEvent event) {

        handleEditReservations();
    }

    @FXML
    private void onClickCancelReservation(ActionEvent event) {
        handleCancelReservation();

    }

    @FXML
    private void onClickNewProfile(ActionEvent event) {
        Forms.displayEditProfileForm(main, 0);
    }

    @FXML
    private void onClickEditProfile(ActionEvent event) {
        Forms.displayEditProfileForm(main, 0);
    }

    @FXML
    private void onClickDeleteProfile(ActionEvent event) {
        //TODO
    }

    @FXML
    private void onClickSearchProfiles(ActionEvent event) {
        handleSearchProfiles();
    }

    private void setupReservationsTable() {

        reservations = FXCollections.observableArrayList();
        tblReservations.setItems(reservations);

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
    }

    private void setupProfilesTable() {

        profiles = FXCollections.observableArrayList();
        tblProfiles.setItems(profiles);

        colMemberID.setCellValueFactory(
                new PropertyValueFactory<>("MemberID"));
        colTitle.setCellValueFactory(
                new PropertyValueFactory<>("Title"));
        colFirstNameProfile.setCellValueFactory(
                new PropertyValueFactory<>("FirstName"));
        colLastNameProfile.setCellValueFactory(
                new PropertyValueFactory<>("LastName"));
        colVIP.setCellValueFactory(
                new PropertyValueFactory<>("VIP"));
        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("Email"));
        colPhoneNumber.setCellValueFactory(
                new PropertyValueFactory<>("PhoneNumber"));
        colNotes.setCellValueFactory(
                new PropertyValueFactory<>("Notes"));
    }

    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    @Override
    public void setUser(User e) {
        this.user = user;
    }

    private void handleSearchReservations() {

        try {
            if (!validateReservationFields()) {
                System.out.println("error validatiting fields");
                return;
            }

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

            result = dao.queryArrivals(dto);

            if (result != null) {
                reservations.setAll(result);
            }

        } catch (Exception e) {
            System.out.println("Error processing query");
        }
    }

    private void handleClear() {

        //Clear Garbage Text from Date Field
        dateArrival.setValue(LocalDate.now());
        dateArrival.setValue(LocalDate.now());

        //Clear Reservations
        reservations.clear();
        txtFirstName.setText("");
        txtLastName.setText("");
        txtGroupName.setText("");
        txtCompanyName.setText("");
        txtPhoneNumber.setText("");
        txtConfirmation.setText("");
        dateArrival.setValue(null);
        dateDeparture.setValue(null);

        //Clear Profiles
        profiles.clear();
        txtEmail.clear();
        txtPhoneNumber.clear();
        txtMemberID.clear();
    }

    private boolean validateReservationFields() {
        return true;
    }

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

    private void handleEditReservations() {
        
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
        
        Forms.displayReserveRoomForm(main, r);
        
    }

    private void handleSearchProfiles() {

        try {
            if (!validateProfileFields()) {
                System.out.println("error validatiting fields");
                return;
            }

            ObservableList<Profile> result;

            ProfileSearchDTO dto = new ProfileSearchDTOBuilder()
                    .setFirstName(txtFirstName.getText())
                    .setLastName(txtLastName.getText())
                    .setMemberID(txtEmail.getText())
                    .setPhoneNumber(txtPhoneNumber.getText())
                    .createProfileSearchDTO();

            result = dao.queryProfiles(dto);

            if (result != null) {
                profiles.setAll(result);
            }

        } catch (Exception e) {
            System.out.println("Error processing query");
        }
    }

    private boolean validateProfileFields() {
        return true;
    }

    private void handleNewReservation() {
        Profile p = tblProfiles.getSelectionModel().getSelectedItem();
        
        //Prompt and return if a reservation is not selected.
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please select a guest profile for \n"
                            + "for the reservation");
            alert.showAndWait();
            return;
        }
        Forms.displayReserveRoomForm(main, p);
    }

}
