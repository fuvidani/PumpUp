package sepm.ss15.grp16.gui.controller.WorkoutPlans;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sepm.ss15.grp16.entity.Exercise;
import sepm.ss15.grp16.entity.training.TrainingsSession;
import sepm.ss15.grp16.entity.training.helper.ExerciseSet;
import sepm.ss15.grp16.gui.controller.Controller;
import sepm.ss15.grp16.service.exception.ServiceException;
import sepm.ss15.grp16.service.impl.ExerciseServiceImpl;
import sepm.ss15.grp16.service.impl.UserServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Author: Lukas
 * Date: 15.05.2015
 */
public class SetController extends Controller implements Initializable {
	private UserServiceImpl userService;
	private ExerciseServiceImpl exerciseService;

	private Exercise selection;

	public static TrainingsSession session_interClassCommunication;

	@FXML
	private Button btnShow;

	@FXML
	private RadioButton rdRepeats;

	@FXML
	private TextField txtRepeat;

	@FXML
	private ToggleGroup tglGrType;

	@FXML
	private RadioButton rdMinutes;

	@FXML
	private TableView<Exercise> tblvExercises;

	@FXML
	private TableColumn<Exercise, String> tblcName;

	@FXML
	private TableColumn<Exercise, Integer> tblcCalo;

	@FXML
	private TableColumn<Exercise, String> tblcCat;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			tblcName.setCellValueFactory(new PropertyValueFactory<>("name"));
			tblcCalo.setCellValueFactory(new PropertyValueFactory<>("calories"));

			ObservableList<Exercise> data = FXCollections.observableArrayList(
					exerciseService.findAll()
			);

			tblvExercises.setItems(data);

			tblvExercises.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
				if (newValue != null) {
					selection = newValue;
					btnShow.setDisable(false);
				}
			});

		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onClickShow(ActionEvent event) {

	}

	@FXML
	void onClickSave(ActionEvent event) {
		ExerciseSet set = createValidSet();

		if (set != null) {
			SessionController.set_interClassCommunication = set;
			this.stage.close();
		}
	}

	private ExerciseSet createValidSet() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Fahler");
		alert.setHeaderText("Falsche Daten!");
		boolean error = false;
		String errormessage = "";

		String repeat = txtRepeat.getText();
		Integer repeat_int = null;

		String repeat_type = ((RadioButton) tglGrType.getSelectedToggle()).getText();

		ExerciseSet.SetType setType;

		if (repeat_type.equals("Wiederholung")) {
			setType = new ExerciseSet.SetType(ExerciseSet.SetType.REPEAT);
		} else {
			setType = new ExerciseSet.SetType(ExerciseSet.SetType.TIME);
		}

		if (repeat == null || repeat.equals("")) {
			error = true;
			errormessage = "Keine Wiederholungen oder Zeit angegeben!";
		} else {
			try {
				repeat_int = Integer.parseInt(repeat);
			} catch (NumberFormatException e) {
				error = true;
				errormessage = repeat_type + " muss eine ganzzahlige Zahl sein!";
			}
		}

		tglGrType.getSelectedToggle();

		if (error) {
			alert.setContentText(errormessage);
			alert.showAndWait();
			return null;
		} else {
			Integer id = null;
			Integer order_nr = null;
			if (session_interClassCommunication != null && session_interClassCommunication.getId() != null) {
				id = session_interClassCommunication.getId();
			}

			if (session_interClassCommunication != null) {
				List<ExerciseSet> sets = session_interClassCommunication.getExerciseSets();
				if (sets != null) {
					order_nr = session_interClassCommunication.getExerciseSets().size() + 1;
				}
			} else {
				order_nr = 1;
			}

			return new ExerciseSet(id, selection, userService.getLoggedInUser(),
					repeat_int, setType, order_nr, false);
		}
	}

	@FXML
	void onClickCancle(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("�nderungen verwerfen");
		alert.setHeaderText("Wollen Sie wirklich abbrechen?");
		alert.setContentText("Alle �nderungen w�rden verlorgen gehen!");
		ButtonType yes = new ButtonType("Ja");
		ButtonType cancel = new ButtonType("Abbrechen", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(yes, cancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == yes) {
			this.stage.close();
		}
		session_interClassCommunication = null;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public void setExerciseService(ExerciseServiceImpl exerciseService) {
		this.exerciseService = exerciseService;
	}
}
