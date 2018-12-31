/**
 * Package which has all controllers of application.
 * */
package sample.Controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import com.jfoenix.controls.JFXTimePicker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.model.Model;
import sample.model.Task;
/**
 * Logic of execution user actions on this page.
 * */
public class AddRegularController {
    /**
     * Checkbox data for interval
     * */
    ObservableList<String> timeInterval = FXCollections.observableArrayList("Days", "Hours", "Minutes");

    /**
     * Image for Edit menu.
     * */
    @FXML
    private ImageView EditTasksLabel;
    /**
     * Image for Calendar menu.
     * */
    @FXML
    private ImageView CalendarTasks;
    /**
     * Image for all Tasks.
     * */
    @FXML
    private ImageView AllTasksLabel;
    /**
     * Field where user type name of task
     * */
    @FXML
    private TextArea titleOfTask;
    /**
     * If user clicked ChoseIrregular button, it loads new scene from Controllers.
     */
    @FXML
    private Button ChoseIrregular;
    /**
     * If user clicked AddTasksButton button, it loads new scene from Controllers.
     */
    @FXML
    private Button AddTasksButton;
    /**
     * Field where user enter interval.
     */
    @FXML
    private TextArea Interval;
    /**
     * If user clicked CanccelButton button, it loads new scene from Controllers.
     */
    @FXML
    private Button CanccelButton;
    /**
     * User chooses time when task will start.
     */
    @FXML
    private JFXTimePicker TimeStart;
    /**
     * Field where user type start date of task.
     * */
    @FXML
    private DatePicker dataStart;

    @FXML
    private JFXTimePicker TimeEnd;
    /**
     * Field where user type end date of task.
     * */
    @FXML
    private DatePicker DataEnd;
    /**
     * Choice box for interval.
     * */
    @FXML
    private ChoiceBox<String> ChoiseBoxRegular;
    /**
     * Shows when user typed wrong data.
     * */
    @FXML
    private Text wrongInput;
    /**
     * Method initialize buttons and render scene before it is shown.
     * */
    @FXML
    void initialize() {
        wrongInput.setVisible(false);
        ChoiseBoxRegular.setItems(timeInterval);
        LocalTime localTime = LocalTime.now();
        TimeStart.setValue(LocalTime.of(localTime.getHour() + 1, 0));
        TimeEnd.setValue(LocalTime.of(localTime.getHour() + 1, 0));
        LocalDate localDate = LocalDate.now();
        dataStart.setValue(LocalDate.now());
        DataEnd.setValue(LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth() + 1));
    /**
         * Listener that forbid everything except digits in the Interval field of the string.
         */
        Interval.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    Interval.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        /**
         * If user clicked CancelButton button, it loads new scene from Controllers.
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
         * If user clicked EditTasksLabel button, it loads new scene from Controllers.
         */
        EditTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        /**
         * If user clicked CalendarTasks button, it loads new scene from Controllers.
         */
        CalendarTasks.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/CheckCalendar.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        /**
         * Listener that forbid to enter whitespace in the start of the field
         * */
        titleOfTask.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("^\\s")) {
                    titleOfTask.setText(newValue.replaceAll("^\\s+", ""));
                }
            }
        });
        /**
         * If user clicked CancelButton button, it loads Main scene from Controllers.
         */
        CanccelButton.setOnMouseClicked(e ->{
            try {
                Model.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml"))));
            } catch (IOException ex){
                System.out.println(ex);
            }
        });
        /**
         * If user clicked AddTaskButton button, it checks all field and add new task to the list,
         * then show new scene to user.
         * In another case it shows the warring text.
         * If exception happens it print in console description of it.
         */
        AddTasksButton.setOnMouseClicked(e ->{
            try {
                String title = titleOfTask.getText().trim();
                if(TimeStart.getValue() != null && TimeEnd.getValue() != null && DataEnd.getValue() != null && dataStart.getValue() != null
                        && titleOfTask.getText() != null  && !"".equals(title) && ChoiseBoxRegular.getValue() != null) {
                    StringBuffer bufferStart = new StringBuffer();
                    StringBuffer bufferEnd = new StringBuffer();
                    bufferStart.append(dataStart.getValue()).append(" ").append(TimeStart.getValue());
                    bufferEnd.append(DataEnd.getValue()).append(" ").append(TimeEnd.getValue());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date StartD;
                    Date EndD;
                    System.out.println(bufferStart);
                    System.out.println(bufferEnd);
                    StartD = simpleDateFormat.parse(bufferStart.toString());
                    EndD = simpleDateFormat.parse(bufferEnd.toString());
                    String intervalStr = ChoiseBoxRegular.getValue();
                    int Interval;
                    switch(intervalStr){
                        case "Days":
                            Interval = Integer.parseInt(this.Interval.getText()) *86400;
                            break;
                        case "Hours":
                            Interval = Integer.parseInt(this.Interval.getText()) *3600;
                            break;
                        case "Minutes":
                            Interval = Integer.parseInt(this.Interval.getText()) * 60;
                            break;
                            default: Interval = 0;
                            break;
                    }
                    Task task = new Task(title, StartD,EndD, Interval);
                    task.setActive(true);
                    Model.taskList.add(task);
                    Model.writeTasks();
                    Model.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml"))));
                } else {
                    wrongInput.setVisible(true);

                }

            } catch (IOException |ParseException ex ) {
                System.out.println(ex);
            }
        });
        /**
         * Switch on adding irregular task
         * */
        ChoseIrregular.setOnMouseClicked(e ->{
            try {
                Model.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksIrregular.fxml"))));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println(ex);
            }
        });

    }
}