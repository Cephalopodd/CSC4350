/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.FrontDeskDAO;
import hms.model.Room;
import hms.model.RoomType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jgreene
 */
public class ReserveRoomFormController implements Initializable {

    @FXML
    private Text lblHeading;
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
        stage.close();
    }

    @FXML
    private void onActionCancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    private void onClickFindRooms(ActionEvent event) {
    }

    void setStage(Stage stage) {
        this.stage = stage;
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

}
