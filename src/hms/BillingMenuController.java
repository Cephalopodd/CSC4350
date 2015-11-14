/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseDragEvent;

/**
 * FXML Controller class
 *
 * @author jgreene
 */
public class BillingMenuController implements Initializable, SubMenu {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setSubMenuParent(MainMenuController main) {
        //
    }

    @Override
    public void setUser(User e) {
        //
    }

    @FXML
    private void onClickPrintMenu(ActionEvent event) {
    }

    @FXML
    private void onClickMakePayment(ActionEvent event) {
    }

    @FXML
    private void credit(MouseDragEvent event) {
    }
    
}
