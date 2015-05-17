package sepm.ss15.grp16.gui.controller.Calendar;

import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.grp16.gui.controller.Controller;
import sepm.ss15.grp16.gui.controller.StageTransitionLoader;
import sepm.ss15.grp16.service.CalendarService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Daniel Fuevesi on 08.05.15.
 *
 */
public class CalendarController extends Controller implements Initializable{

    private  final Logger LOGGER = LogManager.getLogger(CalendarController.class);
    private CalendarService calendarService;
    private StageTransitionLoader transitionLoader;

    @FXML private WebView webView;
    @FXML private WebEngine engine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.transitionLoader = new StageTransitionLoader(this);


        engine = webView.getEngine();
        String path = System.getProperty("user.dir");
        path.replace("\\\\", "/");
        path += "/src/main/java/sepm/ss15/grp16/gui/controller/Calendar/html/selectable.html";
        engine.load("file:///" + path);

        engine.getLoadWorker().stateProperty().addListener((ov,oldState, newState)->{
            if(newState== Worker.State.SUCCEEDED){

                // JS to Java
                JSObject script = (JSObject) engine.executeScript("window");
                script.setMember("drag", calendarService);

                // Java to JS, function to create single event
                engine.executeScript("function addEvent(title, start, end) {\n" +
                        "var eventData = {\n" +
                        "   title: title,\n" +
                        "   start: start,\n" +
                        "};\n" +
                        "$('#calendar').fullCalendar('renderEvent', eventData, true);\n" +
                        "}");
            }

            // Java to JS, send JSON list
            engine.executeScript("function addListEvents(result) {\n" +
                    "for(var i=0; i<result.data.length; i++){\n" +
                    "   addEvent(result.data[i].eventName, result.data[i].startTime, result.data[i].endTime);\n" +
                    "};\n" +
                    "}");

        });
    }

    public void setCalendarService(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @FXML
    void exportToGoogleClicked(ActionEvent event) {

    }
}
