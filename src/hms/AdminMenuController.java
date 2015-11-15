package hms;

import hms.model.AdminDAO;
import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class AdminMenuController implements Initializable, SubMenu {
    
    AdminDAO dao;
    double totalSales;
    int checkOutToday;
    int checkInToday;

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
        
        //Sets the Pie Chart Title.
        checkStatsPieChart.setTitle("Check In Stats");
        
        //Adds the data to the pie chart.
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data("Check Outs", dao.getCheckOut(checkOutToday)),
        new PieChart.Data("Check In", dao.getCheckIn(checkInToday)));
        checkStatsPieChart.setData(pieChartData);
        
        //Sets the name of the Line Chart.
        weeklySales.setTitle("Weekly Sales");
        
        //Defines series for the line chart.
        XYChart.Series currentWeek = new XYChart.Series();
        currentWeek.setName("Day of the Week");
        currentWeek.getData().add(new XYChart.Data("Monday", 230.00));
        currentWeek.getData().add(new XYChart.Data("Tuesday", 141.00));
        currentWeek.getData().add(new XYChart.Data("Wednesday", 152.00));
        currentWeek.getData().add(new XYChart.Data("Thursday", 248.23));
        currentWeek.getData().add(new XYChart.Data("Friday", 3400.33));
        currentWeek.getData().add(new XYChart.Data("Saturday", 3615.22));
        currentWeek.getData().add(new XYChart.Data("Sunday", 455.00));
        
        weeklySales.getData().add(currentWeek);
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
        String daoTotalSales = Double.toString(dao.getTotalSales(totalSales));
        txtTotalSales.setText("$" + daoTotalSales);
    }
}
