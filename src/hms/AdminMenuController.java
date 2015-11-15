package hms;

import hms.model.AdminDAO;
import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
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

    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dao = new AdminDAO();
        setTotalSales();
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data("Check Outs", dao.getCheckOut(checkOutToday)),
        new PieChart.Data("Check In", dao.getCheckIn(checkInToday))); 
        
        checkStatsPieChart.setTitle("Check In Stats");
        checkStatsPieChart.setData(pieChartData);
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
