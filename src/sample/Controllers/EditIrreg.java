/**
 * Package which has all controllers of application.
 * */
package sample.Controllers;

import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.model.Model;
import sample.model.Task;
/**
 * Describes logic of editing irregular task.
 * */
public class EditIrreg {
    /**
     *ImageView that gives an opportunity for user to open scene with functional of adding tasks.
     *  */
    @FXML
    private ImageView AddTasksLabel;
    /**
     *ImageView that gives an opportunity for user to see tasks for a certain period of time.
     *  */
    @FXML
    private ImageView CalendarTasks;
    /**
     * ImageView that give an opportunity for user to see scene with all Tasks.
     * */
    @FXML
    private ImageView AllTasksLabel;
    /**
    * Variable that adds tasks to list.
    */
    @FXML
    private TextArea titleOfTask;
    /**
     *  Variable that adds tasks to list.
     * */
    @FXML
    private Button AddTasksButton;
    /**
     *  Describe logic on cancel button.
     * */
    @FXML
    private Button CanccelButton;
    /**
     *  Describe start time from user.
     * */
    @FXML
    private JFXTimePicker TimeStart;
    /**
     *  Describe start date from user.
     * */
    @FXML
    private DatePicker dataStart;
    /**
     *  Checkbox of Activeness task.
     * */
    @FXML
    private CheckBox ChoiseBox;
    /**
     *  warning text that will be shown if user type wong date in field.
     * */
    @FXML
    private Text warningText;
    /**
     * Method initialize buttons and render scene before it is shown.
     * */
    @FXML
    void initialize() {
        warningText.setVisible(false);
        Task task = Model.taskTemp;
        Model.taskTemp = null;
        titleOfTask.setText(task.getTitle());
        LocalDate localDate = task.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime localTime = task.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        dataStart.setValue(localDate);
        TimeStart.setValue(localTime);
        ChoiseBox.setSelected(task.isActive());
        /**
         * Checks fields of task and add it to list.
         * If fields wrong user will be shown waring text.
         * @param e event that user gives to the application by clicking the AddTAsksButton.
         *  */
        AddTasksButton.setOnMouseClicked(e ->{
            String title = titleOfTask.getText().trim();
            if(title.length() != 0) {
            try {
                    StringBuffer bufferStart = new StringBuffer();
                    bufferStart.append(dataStart.getValue()).append(" ").append(TimeStart.getValue());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date Start = simpleDateFormat.parse(bufferStart.toString());
                    Task task1 = new Task(title, Start);
                    task1.setActive(ChoiseBox.isSelected());
                    Model.taskList.remove(task);
                    Model.taskList.add(task1);
                    Model.writeTasks();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                    Model.primaryStage.setScene(scene);
                } catch (IOException | ParseException ex){
                    System.out.println(ex);
                }
            } else {
                warningText.setVisible(true);
            }
        });
        /**
         * If user clicked CanccelButton button, it loads Edit scene from Controllers.
         */
        CanccelButton.setOnMouseClicked(event -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        /**
         * If user clicked AddTasksLabel button, it loads Add scene from Controllers.
         */
        AddTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksRegular.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        /**
         * If user clicked AllTasksLabel button, it loads new scene with add tasks from Controllers.
         */
        AllTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        /**
         * If user clicked Calendar button, it loads Calendar scene from Controllers.
         */
        CalendarTasks.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/CheckCalendar.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
    }
}
