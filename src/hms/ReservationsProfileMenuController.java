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

    @FXML
    private void onClickClear(ActionEvent event) {
        handleClear();
    }


    @FXML
    private void onClickNewReservation(ActionEvent event) {
        handleNewReservation();
    }

    @FXML
    private void onClickEditReservation(ActionEvent event) {
        handleEditReservation();
    }

    @FXML
    private void onClickCancelReservation(ActionEvent event) {
        handleCancelReservation();

    }

    @FXML
    private void onClickNewProfile(ActionEvent event) {
        handleNewProfile();
    }

    @FXML
    private void onClickEditProfile(ActionEvent event) {
        handleEditProfile();
    }

    @FXML
    private void onClickDeleteProfile(ActionEvent event) {
        handleDeleteProfile();
    }

    @FXML
    private void onClickSearchProfiles(ActionEvent event) {
        handleSearchProfiles();
    }
    
    @FXML
    private void onClickSearchReservations(ActionEvent event) {
        handleSearchReservations();
    }
    
    @FXML
    private void onClickSearch(ActionEvent event) {
        handleSearch();
    }
    
    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    @Override
    public void setUser(User e) {
        this.user = user;
    }

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
        msg.setFont(new Font(24));
        msg.setOpacity(.5);
        tblReservations.setPlaceholder(msg);
    }

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
        msg.setFont(new Font(24));
        msg.setOpacity(.5);
        tblProfiles.setPlaceholder(msg);
    }

    private void handleClear() {

        handleClearReservations();
        handleClearProfiles();

    }

    private void handleClearReservations() {

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
    }

    private void handleClearProfiles() {
        //Clear Profiles
        profiles.clear();
        txtEmail.clear();
        txtPhoneNumber.clear();
        txtMemberID.clear();
    }

    private void handleSearchReservations() {

        if (!validateReservationFields()) {
            System.out.println("error validatiting fields");
            return;
        }

        try {

            ObservableList<Reservation> result;

            String arriveDate;
            String departDate;

            System.out.println("1");
            try {
                arriveDate = dateArrival.getValue().toString();
            } catch (Exception ex) {
                arriveDate = "";
            }

            System.out.println("2");
            try {
                departDate = dateDeparture.getValue().toString();
            } catch (Exception e) {
                departDate = "";
            }

            System.out.println("3");
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

            System.out.println("3.5");
            result = dao.queryArrivals(dto);

            System.out.println("4");
            if (result != null) {
                reservations.setAll(result);
            }
            System.out.println("5");
        } catch (Exception e) {
            System.out.println("Error processing query");
        }
    }

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
            tblReservations.getItems().add(newReservation);
        } else {
            System.out.println("It was null");
        }
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

        //Forms.displayReserveRoomForm(main, r);
        //Forms.displayReserveRoomForm(main, r);
        Reservation newReservation = Forms.displayEditReservationForm(main, r);
        if (newReservation != null) {
            System.out.println("Not null, replacing in table");
            tblReservations.getItems().remove(r);
            tblReservations.getItems().add(newReservation);
        } else {
            System.out.println("Null. not changing table");
        }
    }

    private void handleSearchProfiles() {

        try {

            System.out.println("1");
            if (!validateProfileFields()) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "error validatiting fields");
                alert.showAndWait();
                return;
            }

            System.out.println("2");
            ObservableList<Profile> result;

            System.out.println("3");
            ProfileSearchDTO dto = new ProfileSearchDTOBuilder()
                    .setFirstName(txtFirstName.getText())
                    .setLastName(txtLastName.getText())
                    .setEmail(txtEmail.getText())
                    .setMemberID(txtMemberID.getText())
                    .setPhoneNumber(txtPhoneNumber.getText())
                    .createProfileSearchDTO();

            System.out.println("4");
            result = dao.queryProfiles(dto);

            System.out.println("5");
            if (result != null) {

                System.out.println("6");
                profiles.setAll(result);

                System.out.println("7");
            }

            System.out.println("8");
        } catch (Exception e) {
            System.out.println("Error processing query");
        }
    }

    private void handleNewProfile() {
        Profile newProfile = Forms.displayCreateProfileForm(main);
        if (newProfile != null) {
            tblProfiles.getItems().add(newProfile);
        }
    }

    private void handleDeleteProfile() {
        //TO BE IMPLEMENTED - Shouldn't delete with Checked in guest....
    }

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

    private boolean validateReservationFields() {
        boolean valid = true;

        //Check From Data < To Date
        //Check First Name
        //Check Last Name
        //Check Confirm Number
        return valid;
    }

    private boolean validateProfileFields() {
        boolean valid = true;

        //Check email
        //Check PhoneNumber
        //Check MemberID
        //Check FirstName
        //Check LastName
        return valid;
    }

    private void markInvalid(TextField t) {
        t.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

    }

    private void markValid(TextField t) {
        t.setStyle("");

    }

    private void handleSearch() {
        handleSearchProfiles();
        handleSearchReservations();
    }

    private void initAutoSearch() {
        txtFirstName.setOnAction( e-> { 
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
