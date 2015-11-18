/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.CreditCard;
import hms.model.User;
import hms.model.FrontDeskArrivalsDTO;
import hms.model.FrontDeskDAO;
import hms.model.FrontDeskArrivalsDTOBuilder;
import hms.model.MenuType;
import hms.model.Profile;
import hms.model.Reservation;
import hms.model.ReservationStatus;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author jgreene
 */
public class FrontDeskMenuController implements Initializable, SubMenu {

    @FXML
    private Button btnArrivals;
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

    }

    @FXML
    private void onClickArrivals(ActionEvent event) {
        main.displaySubMenu(MenuType.FRONTDESK);
        handleClear();
        dateArrival.setValue(LocalDate.now());
        handleSearch();
    }

    @FXML
    private void onClickInHouseGuests(ActionEvent event) {
        //Todo Create IN House Guests menu
    }

    @FXML
    private void onClickSearch(ActionEvent event) {
        handleSearch();
    }

    @FXML
    private void onClickClear(ActionEvent event) {
        handleClear();
    }

    @FXML
    private void onClickWalkIn(ActionEvent event) {
        // TODO
    }

    @FXML
    private void onClickEditReservation(ActionEvent event) {
        handleEditReservation();
    }

    @FXML
    private void onClickEditProfile(ActionEvent event) {
        handleEditProfile();
    }

    @FXML
    private void onClickCancelReservation(ActionEvent event) {
        handleCancel();
    }

    @FXML
    private void onClickCheckIn(ActionEvent event) {
        handleCheckIn();
    }

    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    @Override
    public void setUser(User user) {
        currentUser = user;
    }

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
        tblFrontDesk.setPlaceholder(msg);
    
        //Populate with data
        dateArrival.setValue(LocalDate.now());
        handleSearch();
    }

    private void handleSearch() {

        if (!validateFields()) {
            System.out.println("error validatiting fields");
            return;
        }

        ObservableList<Reservation> result;

        FrontDeskArrivalsDTO dto
                = new FrontDeskArrivalsDTOBuilder()
                .setFirstName(txtFirstName.getText())
                .setLastName(txtLastName.getText())
                .setCompanyName(txtCompanyName.getText())
                .setGroupName(txtGroupName.getText())
                .setConfirmation(txtConfirmation.getText())
                .setPhoneNumber(txtPhoneNumber.getText())
                .setArrivalDate(dateArrival.getValue() == null ? "" : dateArrival.getValue().toString())
                .setDepartureDate(dateDeparture.getValue() == null ? "" : dateArrival.getValue().toString())
                .createQueryArrivalsDTO();

        result = dao.queryArrivals(dto);

        if (result != null) {
            data.setAll(result);
        }

    }

    private boolean validateFields() {
        boolean valid = true;
//      try {
//          if (dateArrival.getValue().isBefore(LocalDate.now())){
//            return false;
//          } else if (dateArrival.getValue().isAfter(dateDeparture.getValue()) ) {
//       
//           return false;
//        }else{
//            return true;
//        }  
//    } catch (Exception e) {
//        
//        System.out.println("Exceptoin in searhc..");
//        return true;
//    }
//   
        return valid;
    }

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

    private void handleCheckIn() {
        try {
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

            //Get Credit Card information from DB
            CreditCard cc = dao.getCreditCard(r.getConfirmation());

            //Send Information to Rooms Form
            boolean result = false;
            try {
                result = Forms.displayCheckInForm(main, r.getRoomNumber(), cc, dao, r);
                if (result) {
                    dao.updateReservation(r);
                    r.setStatus(ReservationStatus.CHECKEDIN);
                }
            } catch (Exception e) {
                 System.out.println("Error updating reservation"); 
                 result = false;
            }
            
            if (result) {
                String msg = "Guest was successfully Checked in.\n\n" +
                        "Name: " + r.getFirstName() + " " + r.getLastName() + "\n" +
                        "Room: " + r.getRoomNumber() + "   " + "RoomType: " + r.getRoomType() + "\n" +
                        "Arrival Date: " + r.getCheckinDate() + "\n" +
                        "Departure Date: " + r.getCheckoutDate() + "\n\n" +
                        "Enjoy your stay!";
                                
                alert = new Alert(Alert.AlertType.INFORMATION, msg);
            } else {
                alert = new Alert(Alert.AlertType.ERROR,
                        "Guest could not be checked in at this time");
            }
            alert.showAndWait();

            //Print Results
            System.out.println("Result:" + result);
        } catch (Exception e) {
            System.out.println("Error Handling CheckIn");
        }
    }

    //Waiting on DB Connectivity
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
        
        if (profile!=null){
            profile = Forms.displayEditProfileForm(main, profile);
        }
        
        if (profile != null ) {
            alert = new Alert(Alert.AlertType.INFORMATION,
                    "Profile for " + profile.getFirstName() + 
                            profile.getLastName() + "edited Successfully.");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR,
                    "Profile could not be edited.");
            alert.showAndWait();
        }

    }

    private void handleEditReservation() {
        Reservation reservation = tblFrontDesk.getSelectionModel().getSelectedItem();
        if ( reservation == null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
            "Please select a reservation");
            alert.showAndWait();
            return;
        }
        boolean result = Forms.displayReserveRoomForm(main, reservation);
        
        System.out.println("Result from handle reservation:" + result);
    }

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

    private boolean validateReservationFields() {
        boolean valid = true;
        
        try {
        //Mark All Valid
        markValid(txtFirstName);
        markValid(txtLastName);
        markValid(txtConfirmation);
        
        
        //Check First Name
        
        //Check Last Name
        
        //Check Confirm Number
        
        //Check From Data < To Date
        } catch (Exception e){
            System.out.println("Error Validating Fields");
        }
        
       return valid;
    }

    private void markInvalid(TextField t){
        t.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        
    }
    
    private void markValid(TextField t) {
        t.setStyle("");
      
    }

}
