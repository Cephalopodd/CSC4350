package hms;

import hms.model.AdminDAO;
import hms.model.User;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class AdminMenuController implements Initializable, SubMenu {
    
    AdminDAO dao;
    int checkOutToday;
    int checkInToday;
    LocalDate today = LocalDate.now();

    @FXML
    private Label txtTotalSales;
    @FXML
    private PieChart checkStatsPieChart;
    @FXML
    private LineChart<?, ?> weeklySales;

    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dao = new AdminDAO();
        setTotalSales();
        checkStatsPieChartDisplay();
        weeklySalesLineChartDisplay();
        
    }    

    @Override
    public void setSubMenuParent(MainMenuController main) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUser(User e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Method that calls DAO to get total sales of the day to display in the txtTotalSales label.
    private void setTotalSales(){
        txtTotalSales.setText("$" + Double.toString(dao.getDailySales(today)));
    }
    
    //Mehtod that gets data for pie chart and displays it.
    private void checkStatsPieChartDisplay(){
        //Sets the Pie Chart Title.
        checkStatsPieChart.setTitle("Check In Stats");
        
        //Adds the data to the pie chart.
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data("Check Outs", dao.getCheckOut(today)),
        new PieChart.Data("Check In", dao.getCheckIn(today)));
        checkStatsPieChart.setData(pieChartData);
    }
    
    //Method that gets data for line chart and displays it.
    private void weeklySalesLineChartDisplay(){
        
        //Sets the name of the Line Chart.
        weeklySales.setTitle("Weekly Sales");
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
}
