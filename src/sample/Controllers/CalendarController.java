package sample.Controllers;


import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.Set;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Main;
import sample.model.Task;
import sample.model.Tasks;

import static sample.model.Tasks.calendar;

public class CalendarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView AllTAsksLabel;

    @FXML
    private ImageView AddTasksLabel;

    @FXML
    private ImageView EditTasksLabel;

    @FXML
    private TableColumn<?, ?> TitleColumn;

    @FXML
    private TableView<?> MainTablewithTasks;

    @FXML
    private ImageView CalendarTasks;

    @FXML
    private TableColumn<?, ?> StartDate;

    @FXML
    private TableColumn<?, ?> Active;

    @FXML
    private DatePicker dataEnd;

    @FXML
    private DatePicker dataStart;

    @FXML
    private Button showButton;

    @FXML
    private TableColumn<?, ?> End;

    @FXML
    private TableColumn<?, ?> Interval;

    @FXML
    private JFXTimePicker StartTime;

    @FXML
    private JFXTimePicker EndTime;

    @FXML
    void initialize() {
        AddTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksRegular.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        AllTAsksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        EditTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });

        showButton.setOnMouseClicked(e->{
            if(StartTime.getValue() != null && EndTime.getValue() != null && dataEnd.getValue() != null && dataStart.getValue() != null ){
                //update table
            }
            StringBuffer bufferStart = new StringBuffer();
            StringBuffer bufferEnd = new StringBuffer();
            bufferStart.append(dataStart.getValue()).append(" ").append(StartTime.getValue());
            bufferEnd.append(dataEnd.getValue()).append(" ").append(EndTime.getValue());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                Date StartD;
                Date EndD;
                System.out.println(bufferStart);
                System.out.println(bufferEnd);
                StartD = simpleDateFormat.parse(bufferStart.toString());
                EndD = simpleDateFormat.parse(bufferEnd.toString());
                SortedMap<Date, Set<Task>> sortedMap = Tasks.calendar( Main.taskList, StartD, EndD);



                System.out.println(StartD);
                System.out.println(EndD);
            } catch (ParseException ex){
                System.out.println(ex);
            }



        });


    }
}
