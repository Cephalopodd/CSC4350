package hms;

import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class AdminMenuController implements Initializable, SubMenu {
    
    private MainMenuController main;
    
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
    
    //Launches the add new user form when button is clicked.
    @FXML
    private void onMouseClickedAddNewUser(MouseEvent event) {
        Forms.displayAddNewUser(main);
    }
    
}
