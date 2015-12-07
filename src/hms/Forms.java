/*
 * This file is used as a base to launch the forms used in the application
 */
package hms;

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
 * @author Team Slam
 */
class Forms {
    
    /**
     * This displays the Profile form in edit mode
     * @param main
     * @param p
     * @return 
     */
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
            controller.setProfileInformation(p);
            
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

    /**
     * This displays the Add New User form
     * @param main 
     */
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
    
    /**
     * This displays the profile creation form
     * @param main
     * @return 
     */
    static Profile displayCreateProfileForm(MainMenuController main) {
        boolean result = false;
        Profile newProfile = null;
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
            controller.setEditFlag(false);

            //Create Login and MainMenu Scene
            Scene scene = new Scene(parent);
            
            stage.setScene(scene);
            stage.showAndWait();
            
            result = controller.getResult();
            
            //Check Success
            if (controller.getResult()) {
                newProfile = controller.getNewProfile();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Your Profile was successful created");
                alert.showAndWait();
            } 

        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newProfile;
    }

    /**
     * This displays the Reset Password form
     * @param main 
     */
    static void displayResetPassword(MainMenuController main) {
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(HMS.stage);
            
            //Inject Link to HMSapp into Login And Main Menu Screen
            FXMLLoader loader = new FXMLLoader(main.getClass().getResource("ResetPassword.fxml"));
            Parent parent = (Parent) loader.load();
            
            ResetPasswordController controller = ((ResetPasswordController) loader.getController());
            controller.setStage(stage);
            
            //Create Login and MainMenu Scene
            Scene scene = new Scene(parent);
            
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This displays the Reservation form in create mode
     * @param main
     * @param p
     * @return 
     */
    static Reservation displayCreateReservationForm(MainMenuController main, Profile p) {
        
        Reservation newReservation = null;
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(HMS.stage);
            
            //Load Form and get the controller
            FXMLLoader loader = new FXMLLoader(main.getClass().getResource("ReserveRoomForm.fxml"));
            Parent parent = (Parent) loader.load();
            ReserveRoomFormController controller = ((ReserveRoomFormController) loader.getController());
            
            //Inject information into Form
            controller.setStage(stage);
            controller.setProfile(p);
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
                newReservation = controller.getNewReservation();
                System.out.println("Room is: " + newReservation.getRoomNumber());
            }

        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newReservation;
    }

    /**
     * This displays the reservation form in edit mode
     * @param main
     * @param reservation
     * @return 
     */
    static Reservation displayEditReservationForm(MainMenuController main, Reservation reservation) {
        
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(HMS.stage);
            
            //Load Form and get the controller
            FXMLLoader loader = new FXMLLoader(main.getClass().getResource("ReserveRoomForm.fxml"));
            Parent parent = (Parent) loader.load();
            ReserveRoomFormController controller = ((ReserveRoomFormController) loader.getController());
            
            //Inject information into Form
            controller.setStage(stage);
            controller.setReservation(reservation);
            controller.setReservationInformation(reservation);
            controller.setEditFlag(true);
            
            //Create Login and MainMenu Scene
            Scene scene = new Scene(parent);
            
            stage.setScene(scene);
            stage.showAndWait();
            
            //Check Success
            if (controller.getSuccess()) {
                reservation = controller.getNewReservation();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Your Reservation was successfully edited");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                "Your resservation could not be edited");
                alert.showAndWait();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Forms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservation;
    }

    /**
     * This displays the check in form
     * @param main
     * @param reservation
     * @return 
     */
    static boolean displayCheckInForm(MainMenuController main, Reservation reservation) {
        
        //boolean to see if CC verify was a success
        boolean result = false;
        
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(HMS.stage);
            
            //Load Form and get the controller
            FXMLLoader loader = new FXMLLoader(main.getClass().getResource("CheckInForm.fxml"));
            Parent parent = (Parent) loader.load();
            CheckInFormController controller = ((CheckInFormController) loader.getController());
            
            //Inject information into Form
            controller.setStage(stage);
            controller.setReservationInformation(reservation);

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
}
