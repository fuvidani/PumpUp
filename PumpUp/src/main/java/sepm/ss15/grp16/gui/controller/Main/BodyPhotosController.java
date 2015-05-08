package sepm.ss15.grp16.gui.controller.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Daniel Fuevesi on 07.05.15.
 * Controller of the little window with all photos of the user.
 * For further information see the Mockup.pdf file.
 */
public class BodyPhotosController  implements Initializable{

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Sets the stage of this controller.
     * @param stage the responsible stage
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    void shootPhotoClicked(ActionEvent event) {

    }

    @FXML
    void loadPhotoClicked(ActionEvent event) {

    }

    @FXML
    void getBackButtonClicked(ActionEvent event) {
        stage.close();
    }


}
