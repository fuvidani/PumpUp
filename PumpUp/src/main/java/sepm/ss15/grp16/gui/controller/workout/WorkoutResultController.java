package sepm.ss15.grp16.gui.controller.workout;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sepm.ss15.grp16.entity.exercise.Exercise;
import sepm.ss15.grp16.entity.training.WorkoutResult;
import sepm.ss15.grp16.entity.training.helper.ExerciseSet;
import sepm.ss15.grp16.gui.controller.Controller;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Daniel Fuevesi on 08.05.15.
 */
public class WorkoutResultController extends Controller {


    @FXML
    private TableView<HashMap.Entry<ExerciseSet, WorkoutResult.ExecutionTimePair>> exercisesTable;

    @FXML
    private TableColumn<HashMap.Entry<ExerciseSet, WorkoutResult.ExecutionTimePair>, String> exercise;

    @FXML
    private TableColumn<HashMap.Entry<ExerciseSet, WorkoutResult.ExecutionTimePair>, String> repete;

    @FXML
    private TableColumn<HashMap.Entry<ExerciseSet, WorkoutResult.ExecutionTimePair>, String> duration;

    @FXML
    private LineChart<?, ?> workoutChart;

    @FXML
    private Label burnedCaloriesLabel;

    private ObservableList<HashMap.Entry<ExerciseSet, WorkoutResult.ExecutionTimePair>> masterData;

    @Override
    public void initController() {
        WorkoutController workoutController =(WorkoutController) getParentController();

        WorkoutResult workoutResult = workoutController.getWorkoutResult();

        double calorin = 0;
        for(ExerciseSet set : workoutResult.getAppointment().getSession().getExerciseSets())
        {
            calorin += set.getExercise().getCalories() * workoutResult.getList().get(set).getDuration();
        }
        burnedCaloriesLabel.setText(calorin + "");

        masterData = FXCollections.observableList(new LinkedList<>(workoutResult.getList().entrySet()));
        exercise.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey().getExercise().getName()));
        duration.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getDuration() + ""));
        repete.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getRepetion() + ""));
        exercisesTable.setItems(masterData);

    }

    @FXML
    void shareFacebookClicked(ActionEvent event) {
        WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load("https://www.facebook.com/dialog/feed?app_id=428485184010923&display=popup&name=PumpUp!&description=Share%20your%20workout%20results%20with%20PumpUp!&caption=Do%20you%20want%20to%20get%20in%20shape?&link=https%3A%2F%2Ffacebook.com%2FPumpUpTUVienna%2F&redirect_uri=https%3A%2F%2Ffacebook.com%2F");
        Stage stage = new Stage();
        stage.initOwner(this.stage);
        stage.setScene(new Scene(webView, 500, 300));
        stage.show();
    }

    @FXML
    void endWorkoutClicked(ActionEvent event) {
        mainFrame.navigateToParent();
    }


}
