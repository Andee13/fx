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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.model.Model;
import sample.model.Task;
/**
 * Logic of execution user actions on this page.
 * */
public class AddIrregularController {
    /**
     * Field where user choose time.
     * */
    @FXML
    private JFXTimePicker TaskTime;
    /**
     * Field where user type date.
     * */
    @FXML
    private DatePicker TaskDate;
    /**
     * Button that change scene on addRegularTask.
     * */
    @FXML
    private Button REgularButton;
    /**
     * Image for all Tasks.
     * */
    @FXML
    private ImageView AllTasksLabel;
    /**
     * Image for all Edit Menu.
     * */
    @FXML
    private ImageView EditTasksLabel;
    /**
     * Button that if pressed add tasks in taskList.
     * */
    @FXML
    private Button AddTaskButton;
    /**
     * Text field for title of task.
     * */
    @FXML
    private TextArea TaskTitle;
    /**
     * Button for cancel entering task.
     * */
    @FXML
    private Button CancelButton;
    /**
     * Calendar view.
     * */
    @FXML
    private ImageView CalendarTasks;

    /**
     * Shows when user entered wrong data.
     * */
    @FXML
    private Text wrongText;

    /**
     * Method execute when scene is loaded.
     * It initialize field and add listeners for buttons.
     * */
    @FXML
    void initialize() {
        TaskDate.setValue(LocalDate.now());
        TaskTime.setValue(LocalTime.of(LocalTime.now().getHour() + 1, 0));
        wrongText.setVisible(false);
        /**
         * If user clicked Edit button, it loads new scene from Controllers.
         *
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
         * If user clicked AllTasksLabel button, it loads new scene from Controllers.
         *
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
         * If user clicked CalendarTasks button, it loads new scene from Controllers.
         *
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
         * Listener that forbid whitespace in the start of the string.
         */
        TaskTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("^\\s")) {
                    TaskTitle.setText(newValue.replaceAll("^\\s+", ""));
                }
            }
        });
        /**
         * If user clicked REgularButton button, it loads new scene from Controllers.
         */
        REgularButton.setOnMouseClicked(e ->{
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksRegular.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        /**
         * If user clicked CancelButton button, it loads new scene from Controllers.
         */
        CancelButton.setOnMouseClicked(e ->{
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        /**
         * If user clicked AddTaskButton button, it checks all field and add new task to the list.
         * In another case it shows the warring text.
         * If exception happens it print in console description of it.
         */
        AddTaskButton.setOnMouseClicked(e ->{
                String title = TaskTitle.getText().trim();
                if (title.length() != 0 && TaskTime.getValue() != null && TaskDate.getValue() != null){
                        StringBuffer bufferStart = new StringBuffer();
                        bufferStart.append(TaskDate.getValue()).append(" ").append(TaskTime.getValue());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        try{
                            Date Start;
                            Start = simpleDateFormat.parse(bufferStart.toString());
                            Task task = new Task(title, Start);
                            task.setActive(true);
                            Model.taskList.add(task);
                } catch (ParseException ex) {
                    System.out.println(ex);
            }
                try {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                    Model.primaryStage.setScene(scene);
                }catch (IOException  ex) {
                    System.out.println(ex);
                }
            } else {
                wrongText.setVisible(true);
            }
        });
    }
}
