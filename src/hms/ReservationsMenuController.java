/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.MenuType;
import hms.model.Reservation;
import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<Reservation> tblFrontDesk;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickSearch(ActionEvent event) {
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
    }

    @FXML
    private void onClickNewReservation(ActionEvent event) {
    }

    @FXML
    private void onClickEditReservation(ActionEvent event) {
    }

    @FXML
    private void onClickCancelReservation(ActionEvent event) {
    }

    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
    
}
