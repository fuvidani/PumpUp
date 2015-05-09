package sepm.ss15.grp16.gui.controller.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.grp16.gui.controller.Controller;
import sepm.ss15.grp16.gui.controller.Exercises.ExercisesController;
import sepm.ss15.grp16.gui.controller.Main.Main_CalendarController;
import sepm.ss15.grp16.gui.controller.Main.Main_UserChartController;
import sepm.ss15.grp16.gui.controller.Main.Main_UserDataController;
import sepm.ss15.grp16.gui.controller.StageTransitionLoader;
import sepm.ss15.grp16.gui.controller.User.LoginController;
import sepm.ss15.grp16.gui.controller.WorkoutPlans.WorkoutPlansController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Daniel Fuevesi on 05.05.15.
 * Controller of the main stage.
 *
 */
public class MainController extends Controller implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger();
    private StageTransitionLoader transitionLoader;
    private Main_UserDataController userDataController;
    private Main_UserChartController userChartController;
    private Main_CalendarController calendarController;

    @FXML
    private Label currentTrainingTypeLabel;

    @FXML
    private ImageView userImgView;

    @FXML
    private Label usernameLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.transitionLoader = new StageTransitionLoader(this);
        this.userChartController = new Main_UserChartController();
        this.userDataController = new Main_UserDataController();
        this.calendarController = new Main_CalendarController();
        this.userChartController.initialize(location,resources);
        this.userDataController.initialize(location,resources);
        this.calendarController.initialize(location,resources);

    }

   /* public void openLogin(){
       // transitionLoader.openStage("fxml/Login.fxml", (Stage) userImgView.getScene().getWindow(), "Willkommen!", 353, 216, false);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginController.class.getClassLoader().getResource("fxml/Login.fxml"));
            Pane pane = (Pane) loader.load(LoginController.class.getClassLoader().getResourceAsStream("fxml/Login.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            //dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.userImgView.getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            Controller to = loader.getController();
            to.setStage(dialogStage);

            // Show the dialog and wait until the user closes it
            //dialogStage.setMinWidth(minWidth);
            //dialogStage.setMinHeight(minHeight);
            //if(maximized){
            //    dialogStage.setMaximized(true);
            //}
            LOGGER.info("New stage successfully launched!");
            dialogStage.show();
            // user closed dialog
        }catch (IOException e){
            LOGGER.info("Error on opening new stage, reason: " + e.getMessage());
            e.printStackTrace();
        }
    }*/

    @FXML
    void bodyPhotosButtonClicked(ActionEvent event) {
        transitionLoader.openStage("fxml/BodyPhotos.fxml", (Stage)userImgView.getScene().getWindow(),"Fotos", 1000, 600, false);
    }

    @FXML
    void editUserDataButtonClicked(ActionEvent event) {
        // TODO: Either small dialog window for editing data or live editing into the table itself.
    }

    @FXML
    void statisicsButtonClicked(ActionEvent event) {
        // TODO: Michi's Statistiken
    }

    @FXML
    void viewCurrentWorkoutPlanClicked(ActionEvent event) {
        transitionLoader.openStage("fxml/Workoutplans.fxml",(Stage)userImgView.getScene().getWindow(),"Trainingspläne",1000,620, true);
    }

    @FXML
    void viewAllWorkoutPlansClicked(ActionEvent event) {
        transitionLoader.openStage("fxml/Workoutplans.fxml",(Stage)userImgView.getScene().getWindow(),"Trainingspläne",1000,620, true);
    }

    @FXML
    void exercisesButtonClicked(ActionEvent event) {
        transitionLoader.openStage("fxml/Exercises.fxml", (Stage) userImgView.getScene().getWindow(), "Übungen", 1100, 750, true);
    }

    @FXML
    void calendarClicked(ActionEvent event) {
        transitionLoader.openStage("fxml/Calendar.fxml", (Stage) userImgView.getScene().getWindow(), "Trainingskalender", 1000, 500, false);
    }

    @FXML
    void trainingClicked(ActionEvent event) {
        transitionLoader.openStage("fxml/Workoutstart.fxml", (Stage) userImgView.getScene().getWindow(), "Trainingsvorbereitung", 800, 600, false);
    }




}
