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
                System.out.print("1");
                //Update Reservation
                reservation.setCheckinDate(dateArrival.getValue().toString());
                reservation.setCheckoutDate(dateDeparture.getValue().toString());
                reservation.setRoomNumber(room.getNumber());
                reservation.setNumberAdults(cbxAdults.getValue());
                reservation.setNumberChildren(cbxChildren.getValue());
                reservation.setRoomType(cbxRoomType.getValue());
                reservation.setComments(txtNotes.getText());

                System.out.print("2");
                
                //Send to DB
                boolean result = dao.updateReservation(reservation);
                if (result){
                    System.out.print("3");
                
                    SUCCESS = true;
                    newReservation = reservation;
                    System.out.println("Editing successful");
                    //Dont get update from db..
                 //   newRoomNumber = room.getNumber();
                 //   newReservationNumber = reservation.getConfirmation();
                 //   newReservation = reservation;
                 //   System.out.println("New Res: " + newReservation.getFirstName());
                }
                
            } else {
                System.out.print("4");
                
                int resNo = dao.createReservation(profile, room, searchedArrival, searchedDeparture);
                if (resNo > 0) {
                    System.out.print("5");
                    System.out.print("The resNo returned: " + resNo);
                
                    SUCCESS = true;
                    newRoomNumber = room.getNumber();
                    newReservationNumber = resNo;
                    newReservation = dao.getReservation(resNo);
                    System.out.println("New Res: " + newReservation.getFirstName());
                }
            }
        } catch (Exception e) {
                
            System.out.println("Error in DB creating");
        }
        
        System.out.print("1");
                
        //CLose window
        stage.close();
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
                    + "for errors");
            alert.showAndWait();
            return;
        }
        
//        if (dateArrival.getValue().isBefore(LocalDate.now()))
//        {
//            System.out.println("time cannot flow backward");
//        }
//        if (dateArrival.getValue().isAfter(dateDeparture.getValue()) )
//        {
//           System.out.println("cannot checkout in the past");
//        }
        
        handleFindRoom();
    }

    private void initChoiceBoxes() {
        roomTypes = FXCollections.observableArrayList(RoomType.getRoomTypes());
        numAdults = FXCollections.observableArrayList(1, 2, 3, 4);
        numChildren = FXCollections.observableArrayList(0, 1, 2, 3);

        cbxRoomType.setItems(roomTypes);
        cbxAdults.setItems(numAdults);
        cbxChildren.setItems(numChildren);

        cbxRoomType.setValue(RoomType.DNN);
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
        if (EDIT) {
            lblHeading.setText(HEADING_EDIT);
        } else {
            lblHeading.setText(HEADING_NEW);
        }
    }

    private boolean verifyInput() {
        //set lblErrorMsg ...
        return true;
    }

    private void handleFindRoom() {

        ObservableList<Room> results = FXCollections.observableArrayList();

        try {
            //Remember search criteria...
            searchedArrival = dateArrival.getValue().toString();
            searchedDeparture = dateDeparture.getValue().toString();

            //Query Database
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

    void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    
    void setReservationInformation(Reservation reservation){
        
        cbxRoomType.setValue(reservation.getRoomType());
        cbxAdults.setValue(reservation.getNumberAdults());
        cbxChildren.setValue(reservation.getNumberChildren());
        txtNotes.setText(reservation.getComments());

        lblGuestName.setText(reservation.getFirstName()
                + " " + reservation.getLastName());
        //No way to set Accessible

        //Set Room Type?
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

    void setProfile(Profile profile) {
        this.profile = profile;
        lblGuestName.setText(profile.getFirstName()
                + " " + profile.getLastName());

    }

    int getNewReservationNumber() {
        return newReservationNumber;
    }
    
    boolean getSuccess() {
        return SUCCESS;
    }
    
    int getNewRoomNumber() {
        return newRoomNumber;
    }
    
    public void setCheckInCalendar(){
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
                
		@Override
		public DateCell call(final DatePicker datePicker) {
			return new DateCell(){
			
				@Override
				public void updateItem(LocalDate item, boolean empty){
					
					super.updateItem(item, empty);
				   
					if(item.isBefore(LocalDate.now())){
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
			return new DateCell(){
			
				@Override
				public void updateItem(LocalDate item, boolean empty){
					
					super.updateItem(item, empty);
				   
					if(item.isBefore(LocalDate.now().plusDays(1))){
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

    @FXML
    private void arrivalDateClick(ActionEvent event) {
        
        final Callback<DatePicker, DateCell> dayCellFactory2 = new Callback<DatePicker, DateCell>() {
                
		@Override
		public DateCell call(final DatePicker datePicker) {
			return new DateCell(){
			
				@Override
				public void updateItem(LocalDate item, boolean empty){
					
					super.updateItem(item, empty);
				   
					if(item.isBefore(dateArrival.getValue().plusDays(1))){
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
					}   
				}	
			};
		}
        };
        
        dateDeparture.setDayCellFactory(dayCellFactory2);
    }

    @FXML
    private void deptDateClicked(ActionEvent event) {
        
        final Callback<DatePicker, DateCell> dayCellFactory2 = new Callback<DatePicker, DateCell>() {
                
		@Override
		public DateCell call(final DatePicker datePicker) {
			return new DateCell(){
			
				@Override
				public void updateItem(LocalDate item, boolean empty){
					
					super.updateItem(item, empty);
				   
					if(item.isAfter(dateDeparture.getValue())){
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
					}
                                        
                                        if(item.isBefore(LocalDate.now())){
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
					}
                                        
				}	
			};
		}
        };
        
        dateArrival.setDayCellFactory(dayCellFactory2);
        
    }

    public Reservation getNewReservation() {
        return newReservation;
    }

    boolean getResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
