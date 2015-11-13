/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author jgreene
 */
public class MenuMainController implements Initializable {

    private HMS HMSapp;
    private User user;
    private final HashMap<Enum, Node> subMenus = new HashMap<>();

    @FXML
    private BorderPane mainMenuPane;
    @FXML
    private Button btnReservations;
    @FXML
    private Button btnFrontDesk;
    @FXML
    private Button btnRooms;
    @FXML
    private Button btnBilling;
    @FXML
    private Button btnAdmin;
    @FXML
    private Label lblLoggedInUser;
    @FXML
    private Button btnExit;
    @FXML
    private StackPane displayPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //None
    }

    @FXML
    private void onClickReservations(ActionEvent event) {
        displaySubMenu(MenuType.RESERVATIONS);
    }

    @FXML
    private void onClickFrontDesk(ActionEvent event) {
        displaySubMenu(MenuType.FRONTDESK);
    }

    @FXML
    private void onClickRooms(ActionEvent event) {
        displaySubMenu(MenuType.ROOMS);
    }

    @FXML
    private void onClickBilling(ActionEvent event) {
        displaySubMenu(MenuType.BILLING);
    }

    @FXML
    private void onClickAdmin(ActionEvent event) {
        displaySubMenu(MenuType.ADMIN);
    }

    @FXML
    private void onClickExit(ActionEvent event) {
        HMSapp.doLogout();
    }

    void setHMSApp(HMS app) {
        this.HMSapp = app;
    }

    void setUser(User u) {
        user = u;
        //Set Logged In User Label
        lblLoggedInUser.setText(user.getLogin());

        //Set active Buttons
        btnAdmin.setDisable(!user.getAccessLevel().hasAdminAccess());
        btnRooms.setDisable(!user.getAccessLevel().hasRoomsAccess());
        btnFrontDesk.setDisable(!user.getAccessLevel().hasFrontDeskAccess());
        btnBilling.setDisable(!user.getAccessLevel().hasBillingAccess());
        btnReservations.setDisable(!user.getAccessLevel().hasReservationsAccess());
    }

    public void loadSubMenus(){
        try {
            addSubMenu(MenuType.FRONTDESK, "FrontDeskMenuFXML.fxml");
            addSubMenu(MenuType.BILLING, "MenuBillingFXML.fxml");
            addSubMenu(MenuType.RESERVATIONS, "MenuReservationsFXML.fxml");
            addSubMenu(MenuType.ROOMS, "MenuRoomsFXML.fxml");
            addSubMenu(MenuType.ADMIN, "MenuAdminFXML.fxml");
        } catch (Exception e) {
            System.out.println("Error loading menus:" + e.getMessage());
        }
    }
    
    private void addSubMenu(MenuType menu, String fxml) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent loadScreen = (Parent) myLoader.load();
        SubMenu submenu = ((SubMenu) myLoader.getController());
        submenu.setSubMenuParent(this);
        submenu.setUser(user);
        subMenus.put(menu, loadScreen);
    }
    
    public void setDefaultSubMenu(){
        
        switch (user.getDefaultMenu()) {
            case ADMIN:
                btnAdmin.fire();
                btnAdmin.requestFocus();
                break;
            case ROOMS:
                btnRooms.fire();
                btnRooms.requestFocus();
                break;
            case FRONTDESK:
                btnFrontDesk.fire();
                btnFrontDesk.requestFocus();
                break;
            case BILLING:
                btnBilling.fire();
                btnBilling.requestFocus();
                break;
            case RESERVATIONS:
                btnReservations.fire();
                btnReservations.requestFocus();
                break;
            default :
                break;
        }
    }
    
    private void displaySubMenu(MenuType subMenu){
        FadeTransition ft0 = new FadeTransition(Duration.millis(500), displayPane);
        ft0.setFromValue(1.0);
        ft0.setToValue(0.0);
        ft0.play();
        displayPane.getChildren().setAll(subMenus.get(subMenu));
        FadeTransition ft1 = new FadeTransition(Duration.millis(750), displayPane);
        ft1.setFromValue(0.0);
        ft1.setToValue(1.0);
        ft1.play();
    }

}
