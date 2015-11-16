/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.User;
import hms.model.FrontDeskArrivalsDTO;
import hms.model.FrontDeskDAO;
import hms.model.FrontDeskArrivalsDTOBuilder;
import hms.model.MenuType;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

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
    private Button btnCancel;
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
        dateArrival.setValue(LocalDate.now());
        setupTable();

    }

    @FXML
    private void onClickArrivals(ActionEvent event) {
        main.displaySubMenu(MenuType.FRONTDESK);
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
        data.clear();
        txtFirstName.setText("");
        txtLastName.setText("");
        txtGroupName.setText("");
        txtCompanyName.setText("");
        txtPhoneNumber.setText("");
        txtConfirmation.setText("");
        dateArrival.setValue(LocalDate.now());
        dateDeparture.setValue(null);
    }

    @FXML
    private void onClickWalkIn(ActionEvent event) {
        //Todo Handle Walk IN
    }

    @FXML
    private void onClickEditReservation(ActionEvent event) {
        handleEdit();

    }
    
    @FXML
    private void onClickEditProfile(ActionEvent event) {
        frontDeskPane.setOpacity(.3);
        Forms.displayProfileForm(main);
        frontDeskPane.setOpacity(1.0);
        
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        handleCancel();
    }

    @FXML
        private void onClickCheckIn(ActionEvent event) {
        frontDeskPane.setOpacity(.3);
        Forms.displayCheckInForm(main);
        frontDeskPane.setOpacity(1.0);
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
        return true;
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

    private void handleEdit() {

    }

}
