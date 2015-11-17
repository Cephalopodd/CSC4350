/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.CreditCard;
import hms.model.FrontDeskDAO;
import hms.model.Reservation;
import java.io.IOException;
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
class Forms {
    

    static boolean displayCheckInForm(MainMenuController main, int roomNum, CreditCard cc, FrontDeskDAO dao, Reservation r) {
        boolean result = false;
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(HMS.stage);
            stage.setAlwaysOnTop(true);
            stage.centerOnScreen();
            
            //Inject Link to HMSapp into Login And Main Menu Screen
            FXMLLoader checkInFormLoader = new FXMLLoader(main.getClass().getResource("CheckInForm.fxml"));
            Parent checkInFormView = (Parent) checkInFormLoader.load();
            CheckInFormController checkInFormController = ((CheckInFormController) checkInFormLoader.getController());
            
            checkInFormController.setStage(stage);
            checkInFormController.setCC(cc);
            checkInFormController.setRoomNumber(roomNum);
            
            //Create Login and MainMenu Scene
            Scene scene = new Scene(checkInFormView);
            stage.setScene(scene);
            stage.showAndWait();
            
            //Get Result
            try {
                CreditCard verifiedCC = checkInFormController.getCC();
                result = checkInFormController.getResult();
                if (result) {
                    //Send new CC information to DB
                    System.out.println("Sending new CC to DB: " + verifiedCC.getName());
                    dao.setCreditCard(r.getConfirmation(), verifiedCC);
                }
            } catch (Exception e) {
                System.out.println("Error reading form return");
            }
      
        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        
    }
    
    static boolean displayEditProfileForm(MainMenuController main, int profileID) {
        boolean result = false;
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(HMS.stage);
            
            //Load Form and get the controller
            FXMLLoader loader = new FXMLLoader(main.getClass().getResource("ProfileForm.fxml"));
            Parent parent = (Parent) loader.load();
            ProfileFormController controller = ((ProfileFormController) loader.getController());
            
            //Inject information into Form
            controller.setStage(stage);
            if (profileID>0) {
                controller.setProfileInformation(profileID);
            } else {
                controller.setNewProfile(true);
            }
            
            //Create Login and MainMenu Scene
            Scene scene = new Scene(parent);
            
            stage.setScene(scene);
            stage.showAndWait();
            result = controller.getResult();
        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    static void displayEditReservationForm(MainMenuController main) {
           try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(HMS.stage);
            
            //Inject Link to HMSapp into Login And Main Menu Screen
            FXMLLoader loader = new FXMLLoader(main.getClass().getResource("ReserveRoomForm.fxml"));
            Parent parent = (Parent) loader.load();
            
            ReserveRoomFormController controller = ((ReserveRoomFormController) loader.getController());
            controller.setStage(stage);
            
            //Create Login and MainMenu Scene
            Scene scene = new Scene(parent);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void displayAddNewUser(MainMenuController main) {
           try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(HMS.stage);
            
            //Inject Link to HMSapp into Login And Main Menu Screen
            FXMLLoader loader = new FXMLLoader(main.getClass().getResource("AddNewUser.fxml"));
            Parent parent = (Parent) loader.load();
            
            AddNewUserController controller = ((AddNewUserController) loader.getController());
            controller.setStage(stage);
            
            //Create Login and MainMenu Scene
            Scene scene = new Scene(parent);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
