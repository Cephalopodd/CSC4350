/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.junk;

import hms.MainMenuController;
import hms.MainMenuController;
import hms.SubMenu;
import hms.SubMenu;
import hms.model.FrontDeskArrivalsDTO;
import hms.model.FrontDeskArrivalsDTOBuilder;
import hms.model.FrontDeskDAO;
import hms.model.MenuType;
import hms.model.Reservation;
import hms.model.ReservationStatus;
import hms.model.User;
import java.net.URL;
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
public class ReservationsMenuController implements Initializable, SubMenu {

    @FXML
    private BorderPane frontDeskPane;
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
    private Button btnReservations;
    @FXML
    private Button btnProfiles;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnClear;
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
    private Button btnNewReservation;
    @FXML
    private Button btnEditReservation;
    @FXML
    private Button btnCancelReservation;
    private MainMenuController main;
    private User user;

    private ObservableList<Reservation> reservations;
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
    private void onClickSearch(ActionEvent event) {
        handleSearch();
    }

    @FXML
    private void onClickReservations(ActionEvent event) {
        main.displaySubMenu(MenuType.RESERVATIONS);
    }

    @FXML
    private void onClickProfiles(ActionEvent event) {
        main.displaySubMenu(MenuType.PROFILE);
    }

    @FXML
    private void onClickClear(ActionEvent event) {
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

    @FXML
    private void onClickNewReservation(ActionEvent event) {
        handleEdit(null);
    }

    @FXML
    private void onClickEditReservation(ActionEvent event) {
         //Get Selected Reservation
        Reservation r = tblReservations.getSelectionModel().getSelectedItem();
    
        //Prompt and return if a reservation is not selected.
        if (r == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Reservation not Selected");
            alert.showAndWait();
            return;
        }
       
        handleEdit(r);
    }

    @FXML
    private void onClickCancelReservation(ActionEvent event) {
        handleCancel();
    }

    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    private void setupTable() {
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

    private void handleSearch() {
        if (!validateFields()) {
            System.out.println("error validating fields");
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
            reservations.setAll(result);
        }
    }

    private boolean validateFields() {
        return true;
    }

    private void handleCancel() {

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

    private void handleEdit( Reservation r ) {

    }

}
