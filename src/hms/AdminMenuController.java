package hms;

import hms.model.AdminDAO;
import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Fenil
 */
public class AdminMenuController implements Initializable, SubMenu {
    
    AdminDAO dao;
    double totalSales;

    @FXML
    private Label txtTotalSales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dao = new AdminDAO();
        setTotalSales();
    }    

    @Override
    public void setSubMenuParent(MainMenuController main) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUser(User e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void setTotalSales(){
        String daoTotalSales = Double.toString(dao.getTotalSales(totalSales));
        txtTotalSales.setText("$" + daoTotalSales);
    }
}
