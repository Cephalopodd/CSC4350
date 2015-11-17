/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class InvoiceController implements Initializable {
    @FXML
    private AnchorPane invoiceStage;
    @FXML
    private Label addressLbl;
    @FXML
    private Label lastNamelbl;
    @FXML
    private Label firstNamelbl;
    @FXML
    private Label cityLbl;
    @FXML
    private Label stateLbl;
    @FXML
    private Label zipLbl;
    @FXML
    private Label checkInLbl;
    @FXML
    private Label roomNumLbl;
    @FXML
    private TableView<?> invoiceCharges;
    @FXML
    private Label balanceOwed;
    @FXML
    private MenuButton paymentTypeMenu;
    @FXML
    private Label chargedAmt;
    @FXML
    private Label totCharges;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
