/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

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
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author jgreene
 */
public class ReservationsProfileMenuController implements Initializable {
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
    private TextField memberID;
    @FXML
    private TextField txtEmail;
    @FXML
    private TableView<?> tblReservations;
    @FXML
    private TableColumn<?, ?> colFirstName;
    @FXML
    private TableColumn<?, ?> colLastName;
    @FXML
    private TableColumn<?, ?> colRoomNumber;
    @FXML
    private TableColumn<?, ?> colRoomType;
    @FXML
    private TableColumn<?, ?> colRoomRate;
    @FXML
    private TableColumn<?, ?> colNumberAdults;
    @FXML
    private TableColumn<?, ?> colNumberChildren;
    @FXML
    private TableColumn<?, ?> colArrival;
    @FXML
    private TableColumn<?, ?> colDeparture;
    @FXML
    private TableColumn<?, ?> colStatus;
    @FXML
    private TableColumn<?, ?> colComments;
    @FXML
    private TableView<?> tblProfiles;
    @FXML
    private TableColumn<?, ?> colMemberID;
    @FXML
    private TableColumn<?, ?> colTitle;
    @FXML
    private TableColumn<?, ?> colFirstNameProfile;
    @FXML
    private TableColumn<?, ?> colLastNameProfile;
    @FXML
    private TableColumn<?, ?> colVIP;
    @FXML
    private TableColumn<?, ?> colPhoneNumber;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colNotes;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickSearchReservation(ActionEvent event) {
    }

    @FXML
    private void onClickSearchProfile(ActionEvent event) {
    }

    @FXML
    private void onClickClear(ActionEvent event) {
    }

    @FXML
    private void onClickSearchReservations(ActionEvent event) {
    }

    @FXML
    private void onClickSearchProfiles(ActionEvent event) {
    }

    @FXML
    private void onClickSearchReservatin(ActionEvent event) {
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

    @FXML
    private void onClickNewProfile(ActionEvent event) {
    }

    @FXML
    private void onClickEditProfile(ActionEvent event) {
    }

    @FXML
    private void onClickDeleteProfile(ActionEvent event) {
    }
    
}
