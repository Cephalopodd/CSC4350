package hms;

import hms.model.AdminDAO;
import hms.model.User;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminMenuController implements Initializable, SubMenu {
    
    AdminDAO dao;
    int checkOutToday;
    int checkInToday;
    LocalDate today = LocalDate.now();
    
    private MainMenuController main;
    @FXML
    private TableView<?> userListTable;
    @FXML
    private TableColumn<?, ?> tblUn;
    @FXML
    private TableColumn<?, ?> tblEmployee;
    @FXML
    private TableColumn<?, ?> tblManager;
    @FXML
    private TableColumn<?, ?> tblAdmin;
    @FXML
    private LineChart<?, ?> weeklySales;
    @FXML
    private PieChart checkStatsPieChart;
    
    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dao = new AdminDAO();
        checkStatsPieChartDisplay();
    }    

    @Override
    public void setSubMenuParent(MainMenuController main) {
        this.main = main;
    }

    @Override
    public void setUser(User e) {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void onMouseClickedAddNewUser(ActionEvent event) {
        Forms.displayAddNewUser(main);
    }

    @FXML
    private void onClickResetPw(ActionEvent event) {
        Forms.displayResetPassword(main);
    }

    private void checkStatsPieChartDisplay() {
        
        //Adds the data to the pie chart.
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data("Check Outs", dao.getCheckOut(today)),
        new PieChart.Data("Check In", dao.getCheckIn(today)));
        checkStatsPieChart.setData(pieChartData);
    }
    
}
