/**
 * HMS class
 * 
 * This is the main class that launches the
 * Hotel Management System application.
 *
 */
package hms;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Team SLAM
 */
public class HMS extends Application {
    
    private Stage stage;
    private Scene loginScene;
    private Scene mainMenuScene;
    private LoginFXMLController loginController;
    private MainMenuFXMLController mainMenuController;
    
    /**
    * This method starts our application.
    * precondition: database is available with correct schema
    * postcondition: the HMS application is launched
     * @throws java.lang.Exception
    */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        
        //Inject Link to HMSapp into Login And Main Menu Screen
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent loginView = (Parent) loginLoader.load();
        loginController = ((LoginFXMLController) loginLoader.getController());
        loginController.setHMSApp(this);
       
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("MainMenuFXML.fxml"));
        Parent mainMenuView = (Parent) mainMenuLoader.load();
        mainMenuController = ((MainMenuFXMLController) mainMenuLoader.getController());
        mainMenuController.setHMSApp(this);
       
        //Create Login and MainMenu Scene
        loginScene = new Scene(loginView);
        mainMenuScene = new Scene(mainMenuView);
        
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setScene(loginScene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SQLtest();
        launch(args);
    }

    void doLogin(User user) {
        //inject current user into MainMenu
        mainMenuController.setUser(user);
        
        //Create SubMenus
        mainMenuController.loadSubMenus();
        mainMenuController.setDefaultSubMenu();
        
        //Set scene to Main Menu
        stage.setScene(mainMenuScene);
        stage.setFullScreen(true);
    }
    
    void doLogout(){
        stage.setScene(loginScene);
        stage.setFullScreen(true);
    }

    void exit() {
        Platform.exit();
    }
}
