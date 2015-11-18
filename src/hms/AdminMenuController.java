package hms;

import hms.model.AdminDAO;
import hms.model.NewUserDAO;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class AdminMenuController implements Initializable, SubMenu {
    
    AdminDAO dao;
    int checkOutToday;
    int checkInToday;
    LocalDate today = LocalDate.now();
    //User user;
    //user = new User();
    
    //final ObservalbleList<user> userTableData = FXCollections.observableArrayList(new User));
    
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
        weeklyLineChartDisplay();
        userTableDisplay();
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

    private void weeklyLineChartDisplay() {

        //Removes the legend of the weekly sales line chart.
        weeklySales.setLegendVisible(false);
        
        //Defines series for the line chart.
        XYChart.Series currentWeek = new XYChart.Series();
        
        //Gets Monday's date of the current week.
        LocalDate monday = today.with(previousOrSame(MONDAY));
        
        //Loops through each day of the week and gets the daily sales total for that day.
        for(int i = 0; i <= 6; i++){
            String weekDayDate = monday.plusDays(i).toString();
            currentWeek.getData().add(new XYChart.Data(weekDayDate, dao.getDailySales(monday.plusDays(i))));
        }
        //Adds the current week series to the line chart.
        weeklySales.getData().add(currentWeek);
    }

    private void userTableDisplay(){
        //user = new NewUserDAO();
        //permissionlevelCol.getColumns().adllAll(tblEmployee, tblManager);       
        //userListTable.setItems(FXCollections.observableArrayList(user.getUserList()));
    }
    
}
