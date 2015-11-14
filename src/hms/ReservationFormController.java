/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.Reservation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 *
 * @author jgreene
 */
class ReservationFormController {
    
    static Stage stage;

    static void display(Reservation r, MainMenuController main) {
        //Display Reservation Edit Form.
        stage = new Stage();
        
        Parent root = null;
        try {
            root = FXMLLoader.load(main.getClass()
                    .getResource("ReservationForm.fxml"));
        } catch (Exception ex) {
            Logger.getLogger(CheckInFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        
        stage.setResizable(false);
        //stage.setAlwaysOnTop(true);   //cannot find symbol error
        stage.toFront();    //stop gap fix for above
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        //This should fix fullscreen but I need link to hmsApp in subMenu.
        //  stage.initOwner(hmsApp);
        
        stage.setScene(scene);
        stage.showAndWait();
    }
    
}
