package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import sample.Main;
import sample.model.Task;

public class AddIrregularController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button irregularTasks;

    @FXML
    private JFXTimePicker TaskTime;

    @FXML
    private DatePicker TaskDate;

    @FXML
    private Button REgularButton;

    @FXML
    private ImageView AllTasksLabel;

    @FXML
    private ImageView EditTasksLabel;

    @FXML
    private Button AddTaskButton;

    @FXML
    private TextArea TaskTitle;

    @FXML
    private Button CancelButton;

    @FXML
    private ImageView AddTasksLabel;

    @FXML
    private ImageView CalendarTasks;

    @FXML
    void initialize() {
        EditTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        AllTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        CalendarTasks.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/CheckCalendar.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });



        REgularButton.setOnMouseClicked(e ->{
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksRegular.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        CancelButton.setOnMouseClicked(e ->{
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        AddTaskButton.setOnMouseClicked(e ->{
            try {
                String title = TaskTitle.getText().trim();
                if (title.length() != 0 && TaskTime.getValue() != null && TaskDate.getValue() != null){

                    StringBuffer bufferStart = new StringBuffer();

                    bufferStart.append(TaskDate.getValue()).append(" ").append(TaskTime.getValue());

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date Start;

                    System.out.println(bufferStart);

                    Start = simpleDateFormat.parse(bufferStart.toString());
                    Task task = new Task(title, Start);
                    task.setActive(true);
                    Main.taskList.add(task);
                }

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException | ParseException ex) {
                System.out.println(ex);
            }
        });
    }
}
