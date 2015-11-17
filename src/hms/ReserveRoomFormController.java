/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.FrontDeskDAO;
import hms.model.Profile;
import hms.model.Reservation;
import hms.model.Room;
import hms.model.RoomType;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jgreene
 */
public class ReserveRoomFormController implements Initializable {

    public static String HEADING_NEW = "Create Room Reservation";
    public static String HEADING_EDIT = "Edit Room Reservation";
    
    @FXML
    private Text lblHeading;
    @FXML
    private Label lblErrorMsg;
    @FXML
    private Label lblGuestName;
    @FXML
    private TextArea txtNotes;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private DatePicker dateArrival;
    @FXML
    private TableView<Room> tblRooms;
    @FXML
    private TableColumn<Room, Integer> colRoom;
    @FXML
    private TableColumn<Room, String> colType;
    @FXML
    private TableColumn<Room, String> colCost;
    @FXML
    private DatePicker dateDeparture;
    @FXML
    private CheckBox chkAccessible;
    @FXML
    private CheckBox chkNonSmoking;
    @FXML
    private Button btnFindRooms;
    @FXML
    private ChoiceBox<String> cbxRoomType;
    @FXML
    private ChoiceBox<Integer> cbxAdults;
    @FXML
    private ChoiceBox<Integer> cbxChildren;

    private Stage stage;
    private FrontDeskDAO dao;
    private Reservation reservation;
    private Profile profile;
            
    private boolean EDIT = false;
    private boolean SUCCESS = false;

    private ObservableList<String> roomTypes;
    private ObservableList<Integer> numAdults;
    private ObservableList<Integer> numChildren;
    private ObservableList<Room> roomList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new FrontDeskDAO();
        initChoiceBoxes();
        initTableColumns();

    }

    @FXML
    private void onActionSave(ActionEvent event) {
        
        Room room = tblRooms.getSelectionModel().getSelectedItem();
        
        //Check for null room
        if ( room == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
            "Please search for and select a room");
            alert.showAndWait();
            return;
        }
        
        if (EDIT) {
            
            handleUpdateReservation();
        } else {        
       //     dao.createReservation(profile, room, arrivalDate, departureDate);
    
        }
            
    }

    @FXML
    private void onActionCancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    private void onClickFindRooms(ActionEvent event) {
        //Checking input
        if (!verifyInput()) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
            "Input not valid, please check the form"
                    + "for errors" );
            alert.showAndWait();
            return;
        }
        handleFindRoom();
    }

    private void initChoiceBoxes() {
        roomTypes = FXCollections.observableArrayList(RoomType.DBLNS,
                RoomType.DBLS, RoomType.KINGNS, RoomType.KINGS,
                RoomType.QUEENNS, RoomType.QUEENS);
        numAdults = FXCollections.observableArrayList(1, 2, 3, 4);
        numChildren = FXCollections.observableArrayList(0, 1, 2, 3);

        cbxRoomType.setItems(roomTypes);
        cbxAdults.setItems(numAdults);
        cbxChildren.setItems(numChildren);

        cbxRoomType.setValue(RoomType.KINGNS);
        cbxAdults.setValue(1);
        cbxChildren.setValue(0);

    }

    private void initTableColumns() {

        tblRooms.setItems(roomList);

        colRoom.setCellValueFactory(
                new PropertyValueFactory<>("Number"));
        colType.setCellValueFactory(
                new PropertyValueFactory<>("Type"));
        colCost.setCellValueFactory(
                new PropertyValueFactory<>("Cost"));
        colCost.setCellValueFactory(cellData
                -> Bindings.format("%.2f", cellData.getValue().getCost())
        );
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setEditFlag(boolean value) {
        EDIT = value;
        if (EDIT)
            lblHeading.setText(HEADING_EDIT);
        else
            lblHeading.setText(HEADING_NEW);
    }

    private boolean verifyInput() {
        //set lblErrorMsg ...
        return true;
    }
   
    private void handleFindRoom(){
        
        ObservableList<Room> results = FXCollections.observableArrayList();
        
        try {
            results = dao.queryRoomAvailability(
                    cbxRoomType.getValue(),
                    dateArrival.getValue().toString(),
                    dateDeparture.getValue().toString()
            );
            
        } catch (Exception e) {
            System.out.println("Error finding a room");
        }
        
        if (results != null) {
            tblRooms.setItems(results);
        }
        
    }

    private void handleUpdateReservation() {
        System.out.println("Updated Reservation");
    }

    private void handleCreateReservation() {
        System.out.println("Created Reservation");
    }

    void setReservation(Reservation reservation) {
        this.reservation = reservation;
        cbxRoomType.setValue(reservation.getRoomType());
        cbxAdults.setValue(reservation.getNumberAdults());
        cbxChildren.setValue(reservation.getNumberChildren());
        txtNotes.setText(reservation.getComments());
        
        lblGuestName.setText(reservation.getFirstName() 
                + " " + reservation.getLastName());
        //No way to set Accessible
        
        //Set Room Type?
        System.out.println(reservation.getRoomType() + " roomtype");
        try {
            dateArrival.setValue(LocalDate.parse(reservation.getCheckinDate()));
            dateDeparture.setValue(LocalDate.parse(reservation.getCheckoutDate()));
        } catch (Exception e) {
            System.out.println("Could not set date fields");
        }
        
    }

    void setProfile(Profile profile) {
        this.profile = profile;
        lblGuestName.setText(profile.getFirstName() 
                + " " + profile.getLastName());
        
    }
}
