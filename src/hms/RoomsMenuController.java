package hms;

import hms.model.RoomsDAO;
import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class RoomsMenuController implements Initializable,SubMenu {
    
    RoomsDAO dao;
    int activeCount, occupiedCount, mainCount, hkCount, totalCount;
    
    @FXML
    private Rectangle rm100;
    @FXML
    private Label activeRmCount;
    @FXML
    private Label occupiedRmCount;
    @FXML
    private Label maintenanceRmCount;
    @FXML
    private Label hkRmCount;
    @FXML
    private Label totalRoomCount;
    
    //Initializes the controller class.

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dao = new RoomsDAO();
        activeRmCount();
        occupiedRmCount();
        maintenanceRmCount();
        hkRmCount();
        totalRoomCount();
        
    }
    
    @Override
    public void setSubMenuParent(MainMenuController main) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUser(User e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void onMouseClick(MouseEvent event) {
        
    }
    
    public void activeRmCount(){
        activeRmCount.setText(Integer.toString(dao.getActiveRmCount()));
    }
    
    public void occupiedRmCount(){
        occupiedRmCount.setText(Integer.toString(dao.getOccupiedRmCount()));
    }
    
    public void maintenanceRmCount(){
        maintenanceRmCount.setText(Integer.toString(dao.getMaintenanceRmCount()));
    }
    
    public void hkRmCount(){
        hkRmCount.setText(Integer.toString(dao.getHkRmCount()));
    }
    
    public void totalRoomCount(){
        totalRoomCount.setText(Integer.toString(dao.getTotalRoomCount()));
    }
    
}
