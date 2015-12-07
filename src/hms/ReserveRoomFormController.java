/**
 * Controller for the rooms reservation form
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * ReserveRoomFormController Class
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
    private String searchedArrival;
    private String searchedDeparture;
    private int newReservationNumber = 0;
    private int newRoomNumber = 0;
    private Reservation newReservation = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new FrontDeskDAO();
        initChoiceBoxes();
        initTableColumns();
        setCheckInCalendar();

    }

    /**
     * Saves Reservations
     *
     * @param event
     */
    @FXML
    private void onActionSave(ActionEvent event) {

        Room room = tblRooms.getSelectionModel().getSelectedItem();

        //Check for null room
        if (room == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please search for and select a room");
            alert.showAndWait();
            return;
        }

        try {
            if (EDIT) {
                //Update Reservation
                reservation.setCheckinDate(dateArrival.getValue().toString());
                reservation.setCheckoutDate(dateDeparture.getValue().toString());
                reservation.setRoomNumber(room.getNumber());
                reservation.setNumberAdults(cbxAdults.getValue());
                reservation.setNumberChildren(cbxChildren.getValue());
                reservation.setRoomType(cbxRoomType.getValue());
                reservation.setComments(txtNotes.getText());

                //Send to DB
                boolean result = dao.updateReservation(reservation);
                if (result) {
                    SUCCESS = true;
                    newReservation = reservation;
                    System.out.println("Editing successful - setting new reservation");
                }

            } else {
                int resNo = dao.createReservation(profile, room, searchedArrival, searchedDeparture);
                if (resNo > 0) {
                    System.out.println("The resNo returned: " + resNo);
                    SUCCESS = true;
                    newRoomNumber = room.getNumber();
                    newReservationNumber = resNo;
                    
                    newReservation = dao.getReservation(resNo);
                    //Does not populate room number...
                    newReservation.setRoomNumber(room.getNumber());
                }
            }
        } catch (Exception e) {

            System.out.println("Error in DB creating");
        }
        stage.close();
    }

    /**
     * Cancel Button click
     * @param event 
     */
    @FXML
    private void onActionCancel(ActionEvent event) {
        stage.close();
    }

    /**
     * Find Rooms Button click
     * @param event 
     */
    @FXML
    private void onClickFindRooms(ActionEvent event) {
        handleFindRoom();
    }

    /**
     * Initialize Choice boxes
     */
    private void initChoiceBoxes() {
        roomTypes = FXCollections.observableArrayList(RoomType.getRoomTypes());
        numAdults = FXCollections.observableArrayList(1, 2, 3, 4);
        numChildren = FXCollections.observableArrayList(0, 1, 2, 3);

        cbxRoomType.setItems(roomTypes);
        cbxAdults.setItems(numAdults);
        cbxChildren.setItems(numChildren);

        cbxRoomType.setValue(RoomType.DNH);
        cbxAdults.setValue(1);
        cbxChildren.setValue(0);

    }

    /**
     * Initialize table columns
     */
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

    /**
     * Inject link to main stage     
    */
    void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Set the form as an edit or create form
     * @param value 
     */
    void setEditFlag(boolean value) {
        EDIT = value;
        if (EDIT) {
            lblHeading.setText(HEADING_EDIT);
        } else {
            lblHeading.setText(HEADING_NEW);
        }
    }

    /**
     * Queries db for available rooms and updates the table
     */
    private void handleFindRoom() {

        ObservableList<Room> results = FXCollections.observableArrayList();

        try {
            searchedArrival = dateArrival.getValue().toString();
            searchedDeparture = dateDeparture.getValue().toString();

            //Query Database
            System.out.println("DB Query: avaialble rooms");
            results = dao.queryRoomAvailability(
                    cbxRoomType.getValue(),
                    searchedArrival,
                    searchedDeparture);

        } catch (Exception e) {
            System.out.println("Error finding a room");
        }

        if (results != null) {
            tblRooms.setItems(results);
        }

    }

    /**
     * Injects reservation information
     * @param reservation 
     */
    void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * Populates reservation information
     * @param reservation 
     */
    void setReservationInformation(Reservation reservation) {

        cbxRoomType.setValue(reservation.getRoomType());
        cbxAdults.setValue(reservation.getNumberAdults());
        cbxChildren.setValue(reservation.getNumberChildren());
        txtNotes.setText(reservation.getComments());

        lblGuestName.setText(reservation.getFirstName()
                + " " + reservation.getLastName());
        
        System.out.println(reservation.getRoomType() + " roomtype");
        System.out.println(reservation.getCheckinDate());
        try {
            dateArrival.setValue(LocalDate.parse(reservation.getCheckinDate()));
            dateDeparture.setValue(LocalDate.parse(reservation.getCheckoutDate()));
        } catch (Exception e) {
            System.out.println("Could not set date fields");
            dateArrival.setValue(LocalDate.now());
            dateArrival.setValue(LocalDate.now());
        }
    }

    /**
     * Injects profile information
     * @param profile 
     */
    void setProfile(Profile profile) {
        this.profile = profile;
        lblGuestName.setText(profile.getFirstName()
                + " " + profile.getLastName());

    }

    /**
     * returns new reservation
     * @return 
     */
    int getNewReservationNumber() {
        return newReservationNumber;
    }

    /**
     * returns success of create
     * @return 
     */
    boolean getSuccess() {
        return SUCCESS;
    }

    /**
     * return new Room Number
     * @return 
     */
    int getNewRoomNumber() {
        return newRoomNumber;
    }

    /**
     * Initializes date validation checking
     */
    public void setCheckInCalendar() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {

                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };

        final Callback<DatePicker, DateCell> dayCellFactory2 = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {

                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now().plusDays(1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };

        dateArrival.setDayCellFactory(dayCellFactory);
        dateDeparture.setDayCellFactory(dayCellFactory2);
    }

    /**
     * Sets validation checking on arrival date
     * @param event 
     */
    @FXML
    private void arrivalDateClick(ActionEvent event) {

        final Callback<DatePicker, DateCell> dayCellFactory2 = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {

                        super.updateItem(item, empty);

                        if (item.isBefore(dateArrival.getValue().plusDays(1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };

        dateDeparture.setDayCellFactory(dayCellFactory2);
    }

    /**
     * Sets validation checking on departure date
     * @param event 
     */
    @FXML
    private void deptDateClicked(ActionEvent event) {

        final Callback<DatePicker, DateCell> dayCellFactory2 = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {

                        super.updateItem(item, empty);

                        if (item.isAfter(dateDeparture.getValue())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }

                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }

                    }
                };
            }
        };

        dateArrival.setDayCellFactory(dayCellFactory2);

    }

    /**
     * returns new reservation
     * @return 
     */
    public Reservation getNewReservation() {
        return newReservation;
    }

}
