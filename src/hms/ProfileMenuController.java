/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.MenuType;
import hms.model.Profile;
import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class ProfileMenuController implements Initializable, SubMenu {
    @FXML
    private BorderPane profileMenuPane;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtCompanyName;
    @FXML
    private TextField txtConfirmation;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private Button btnReservations;
    @FXML
    private Button btnProfiles;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnClear;
    @FXML
    private TableView<Profile> tblProfiles;
    @FXML
    private TableColumn<Profile, Integer> colMemberID;
    @FXML
    private TableColumn<Profile, String> colTitle;
    @FXML
    private TableColumn<Profile, String> colFirstName;
    @FXML
    private TableColumn<Profile, String> colLastName;
    @FXML
    private TableColumn<Profile, Boolean> colVIP;
    @FXML
    private TableColumn<Profile, String> colPhoneNumber;
    @FXML
    private TableColumn<Profile, String> colEmail;
    @FXML
    private TableColumn<Profile, String> colNotes;
    @FXML
    private Button btnNewProfile;
    @FXML
    private Button btnEditProfile;
    @FXML
    private Button btnDeleteProfile;
    
    private MainMenuController main;
    private User User;
    ObservableList<Profile> profiles;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Init Table
        profiles = FXCollections.observableArrayList();
        tblProfiles.setItems(profiles);

        //Init Columns
        setupColumns();
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
    private void onClickNewProfile(ActionEvent event) {

    }

    @FXML
    private void onClickEditProfile(ActionEvent event) {
    }

    @FXML
    private void onClickDeleteProfile(ActionEvent event) {
    }

    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    @Override
    public void setUser(User user) {
        this.User = user;
    }

    private void setupColumns() {
        
        colMemberID.setCellValueFactory(
            new PropertyValueFactory<>("MemberID"));
        colTitle.setCellValueFactory(
                new PropertyValueFactory<>("Title"));
        colFirstName.setCellValueFactory(
                new PropertyValueFactory<>("FirstName"));
        colLastName.setCellValueFactory(
                new PropertyValueFactory<>("LastName"));
        colVIP.setCellValueFactory(
                new PropertyValueFactory<>("VIP"));
        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("Email"));
        colPhoneNumber.setCellValueFactory(
                new PropertyValueFactory<>("PhoneNumber"));
        colNotes.setCellValueFactory(
                new PropertyValueFactory<>("Notes"));      
    
    }
    
}
