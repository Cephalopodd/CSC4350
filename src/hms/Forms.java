/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.CreditCard;
import hms.model.FrontDeskDAO;
import hms.model.Profile;
import hms.model.Reservation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    
    static Profile displayEditProfileForm(MainMenuController main, Profile p) {
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
            controller.setEditFlag(true);
            controller.setProfile(p);
            
            //Create Login and MainMenu Scene
            Scene scene = new Scene(parent);
            
            stage.setScene(scene);
            stage.showAndWait();
            result = controller.getResult();
            
            //Check Success
            if (controller.getResult()) {
                p = controller.getNewProfile();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Your Profile was successful edited");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                "Your profile could not be edited");
                alert.showAndWait();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    static void displayReserveRoomForm(MainMenuController main, Reservation reservation) {
           try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(HMS.stage);
            
            //Inject Link to HMSapp into Login And Main Menu Screen
            FXMLLoader loader = new FXMLLoader(main.getClass().getResource("ReserveRoomForm.fxml"));
            Parent parent = (Parent) loader.load();
            
            ReserveRoomFormController controller = ((ReserveRoomFormController) loader.getController());
            controller.setStage(stage);
            controller.setReservation(reservation);
            controller.setEditFlag(true);
            
            //Create Login and MainMenu Scene
            Scene scene = new Scene(parent);
            
            stage.setScene(scene);
            stage.showAndWait();
            
            //Check Success
            if (controller.getSuccess()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Your Reservation was successful completed\n"
                        + "Confirmation Number: " + controller.getNewReservationNumber() + "\n"
                        + "Room Number: " + controller.getNewRoomNumber()
                );
                alert.showAndWait();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void displayReserveRoomForm(MainMenuController main, Profile profile) {
           try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(HMS.stage);
            
            //Inject Link to HMSapp into Login And Main Menu Screen
            FXMLLoader loader = new FXMLLoader(main.getClass().getResource("ReserveRoomForm.fxml"));
            Parent parent = (Parent) loader.load();
            
            ReserveRoomFormController controller = ((ReserveRoomFormController) loader.getController());
            controller.setStage(stage);
            controller.setProfile(profile);
            controller.setEditFlag(false);
            
            //Create Login and MainMenu Scene
            Scene scene = new Scene(parent);
            
            stage.setScene(scene);
            stage.showAndWait();
            
             //Check Success
            if (controller.getSuccess()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Your new reservation was successfully created\n"
                        + "Confirmation Number: " + controller.getNewReservationNumber() + "\n"
                        + "Room Number: " + controller.getNewRoomNumber()
                );
                alert.showAndWait();
            }
            
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
    
    static Profile displayCreateProfileForm(MainMenuController main) {
        boolean result = false;
        Profile p = null;
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

            //Create Login and MainMenu Scene
            Scene scene = new Scene(parent);
            
            stage.setScene(scene);
            stage.showAndWait();
            result = controller.getResult();
            Profile newProfile = controller.getNewProfile();
            
            //Check Success
            if (controller.getResult()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Your Profile was successful created");
                alert.showAndWait();
                p = newProfile;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                "Your profile could not be created");
                alert.showAndWait();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }
}
