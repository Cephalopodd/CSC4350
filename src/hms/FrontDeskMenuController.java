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
import hms.model.Reservation;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Button btnEdit;
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

    private MainMenuController mainMenuController;
    private User currentUser;
    private ObservableList<Reservation> data;
    private FrontDeskDAO dao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dao = new FrontDeskDAO();
        data = FXCollections.observableArrayList();

        dateArrival.setValue(LocalDate.now());
        setupTable();

    }

    @FXML
    private void onClickArrivals(ActionEvent event) {
    }

    @FXML
    private void onClickInHouseGuests(ActionEvent event) {
    }

    @FXML
    private void onClickSearch(ActionEvent event) {
        handleSearch();
    }

    @FXML
    private void onClickClear(ActionEvent event) {
    }

    @FXML
    private void onClickWalkIn(ActionEvent event) {
    }

    @FXML
    private void onClickEdit(ActionEvent event) {
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
    }

    @FXML
    private void onClickCheckIn(ActionEvent event) {
        frontDeskPane.setOpacity(.3);
        CheckInFormController.display(mainMenuController);
        frontDeskPane.setOpacity(1.0);
    }

    @Override
    public void setSubMenuParent(MainMenuController main) {
        mainMenuController = main;
    }

    @Override
    public void setUser(User user) {
        currentUser = user;
    }

    private void setupTable() {

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

}
