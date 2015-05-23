package sepm.ss15.grp16.gui.controller.workout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import sepm.ss15.grp16.gui.StageTransitionLoader;
import sepm.ss15.grp16.gui.controller.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Daniel Fuevesi on 08.05.15.
 * This controller controls the lower section of the training's stage.
 */
public class WorkoutMusicPlayerController extends Controller implements Initializable {


    private StageTransitionLoader transitionLoader;
    @FXML
    private Label songTotalLengthLabel;

    @FXML
    private MediaView musicPlayerSlide;

    @FXML
    private Label artistAndSongLabel;

    @FXML
    private Label songSecondsCounterLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.transitionLoader = new StageTransitionLoader(this);
    }

    @FXML
    void rewindButtonClicked(ActionEvent event) {

    }

    @FXML
    void playButtonClicked(ActionEvent event) {

    }

    @FXML
    void forwardButtonClicked(ActionEvent event) {

    }

    @FXML
    void playlistButtonClicked(ActionEvent event) {
        transitionLoader.openStage("fxml/workout/Playlist.fxml", (Stage) songSecondsCounterLabel.getScene().getWindow(), "Musik - Playlist", 700, 300, false);
    }

}
