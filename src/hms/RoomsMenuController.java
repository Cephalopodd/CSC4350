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
import javafx.scene.layout.TilePane;
import java.util.HashMap;
import java.util.ArrayList;
import javafx.event.EventHandler;

public class RoomsMenuController implements Initializable,SubMenu {
    
    RoomsDAO dao;
    int activeCount, occupiedCount, mainCount, hkCount, totalCount;
    
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
    @FXML
    private TilePane tilePane;
    
    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dao = new RoomsDAO();
        activeRmCount();
        occupiedRmCount();
        maintenanceRmCount();
        hkRmCount();
        totalRoomCount();
        tilePaneDisplay();
        
    }
    
    @Override
    public void setSubMenuParent(MainMenuController main) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUser(User e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    public void tilePaneDisplay(){
        tilePane.setVgap(12.0);
        tilePane.setHgap(12.0);
        tilePane.setPrefColumns(5);
        
        HashMap<Integer, Button> roomBtn = new HashMap(dao.getTotalRoomCount());
        ArrayList active = dao.getActiveRmList(), occ = dao.getOccupiedRmList(),
                maint = dao.getMaintRmList(), hk = dao.getHkRmList();
        for (int i : dao.getTotalRmList()) {
            roomBtn.put(i, new Button("Room\n" + i));
            roomBtn.get(i).setStyle("-fx-font-size: 16px; -fx-text-fill: BLACK;");
            if(occ.contains(i)){
                roomBtn.get(i).setStyle("-fx-background-color: #ff4e50; -fx-font-size: 16px; -fx-text-fill: WHITE;");
                //roomBtn.get(i).getStylesheets().add(getClass().getClassLoader().getResource("resource/styling/button.css").toExternalForm());
            }else if(active.contains(i)){
                roomBtn.get(i).setStyle("-fx-background-color: #84aa05; -fx-font-size: 16px; -fx-text-fill: WHITE;");
            }else if(maint.contains(i)){
                roomBtn.get(i).setStyle("-fx-background-color: #524656; -fx-font-size: 16px; -fx-text-fill: WHITE;");
            }else if(hk.contains(i)){
                roomBtn.get(i).setStyle("-fx-background-color: #49708a; -fx-font-size: 16px; -fx-text-fill: WHITE;");
            }
            
            tilePane.getChildren().add(roomBtn.get(i));
        }
    }
    
}
