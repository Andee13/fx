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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.model.Model;
import sample.model.Task;
/**
 * Describes logic of editing regular task.
 * */
public class EditReg {
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
     * Field where user typing text.
     * */
    @FXML
    private TextArea titleOfTask;
    /**
     * Button to add new tasks to list.
     * */
    @FXML
    private Button AddTasksButton;
    /**
     * Field where user typing interval.
     * */
    @FXML
    private TextArea Interval;
    /**
     * When it clicked it close this window.
     * */
    @FXML
    private Button CanccelButton;
    /**
     * Give an opportunity to choose start time of task.
     * */
    @FXML
    private JFXTimePicker TimeStart;
    /**
     * Give an opportunity to choose start data.
     * */
    @FXML
    private DatePicker dataStart;
    /**
     * Give an opportunity to choose end time.
     * */
    @FXML
    private JFXTimePicker TimeEnd;
    /**
     * Give an opportunity to choose end data.
     * */
    @FXML
    private DatePicker DataEnd;
    /**
     * List for choicebox.
     * */
    ObservableList<String> timeInterval = FXCollections.observableArrayList("Days", "Hours", "Minutes");
    /**
     * Give an opportunity to choose task interval.
     * */
    @FXML
    private ChoiceBox<String> ChoiseBoxRegular;
    /**
     * Signaling about activeness of task.
     * */
    @FXML
    private CheckBox activeCheckBox;
/**
 * warning text will be shown when user input wrong date.
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
        Date dateS = task.getStartTime();
        LocalDate dateStart = dateS.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date dateE = task.getEndTime();
        LocalDate localDateEnd = dateE.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateStart = LocalDate.of(dateStart.getYear(),dateStart.getMonth(),dateStart.getDayOfMonth());
        dataStart.setValue(localDateStart);
        TimeStart.setValue(LocalTime.of(dateS.getHours(), dateS.getMinutes()));
        TimeEnd.setValue(LocalTime.of(dateE.getHours(), dateE.getMinutes()));
        DataEnd.setValue(LocalDate.of(localDateEnd.getYear(),localDateEnd.getMonth(), localDateEnd.getDayOfMonth()));
        if(task.isActive()){
            activeCheckBox.setSelected(true);
        } else {
            activeCheckBox.setSelected(false);
        }
        ChoiseBoxRegular.setItems(timeInterval);
        ChoiseBoxRegular.setValue("Minutes");
        Interval.setText(String.valueOf(task.getRepeatInterval() / 60));
        /**
         * If user clicked AddTasksButton button, it checks all field.
         * If every of fields has expected input it adds task to list and loads AllTasks scene.
         * If one field has wrong input application shows waring text.
         * @param e event provide by user.
         */
        AddTasksButton.setOnMouseClicked(e-> {
            String title = titleOfTask.getText().trim();
            if(title.length() != 0){
                try {
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
                    switch (intervalStr) {
                        case "Days":
                            Interval = Integer.parseInt(this.Interval.getText()) *86400;
                            break;
                        case "Hours":
                            Interval = Integer.parseInt(this.Interval.getText()) *3600;
                            break;
                        case "Minutes":
                            Interval = Integer.parseInt(this.Interval.getText()) * 60;
                            break;
                        default:
                            Interval = 0;
                            break;
                    }
                    Task task1 =  new Task(title, StartD, EndD, Interval);
                    task1.setActive(activeCheckBox.isSelected());
                    Model.taskList.remove(task);
                    Model.taskList.add(task1);
                    Model.writeTasks();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                    Model.primaryStage.setScene(scene);
                }catch (ParseException|IOException ex){
                    System.out.println(ex);
                }
            } else {
                warningText.setVisible(true);
            }
        });
        /**
         * If user clicked cancel button, it loads edit scene from Controllers.
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
         * If user clicked AllTAsksLabel button, it loads AllTasks scene from Controllers.
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
