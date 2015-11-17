package hms;

import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminMenuController implements Initializable, SubMenu {
    
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
    
    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
