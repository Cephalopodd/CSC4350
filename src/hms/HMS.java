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
    private Scene menuMainScene;
    private LoginController loginController;
    private MenuMainController menuMainController;
    
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
        loginController = ((LoginController) loginLoader.getController());
        loginController.setHMSApp(this);
       
        FXMLLoader menuMainLoader = new FXMLLoader(getClass().getResource("MenuMainFXML.fxml"));
        Parent menuMainView = (Parent) menuMainLoader.load();
        menuMainController = ((MenuMainController) menuMainLoader.getController());
        menuMainController.setHMSApp(this);
       
        //Create Login and MenuMain Scene
        loginScene = new Scene(loginView);
        menuMainScene = new Scene(menuMainView);
        
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
        menuMainController.setUser(user);
        
        //Create SubMenus
        menuMainController.loadSubMenus();
        menuMainController.setDefaultSubMenu();
        
        //Set scene to Main Menu
        stage.setScene(menuMainScene);
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
