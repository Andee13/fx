/**
 * Package which has all controllers of application.
 * */
package sample.Controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.model.Model;
import sample.model.Task;
import sample.model.Tasks;
/**
 * Logic of execution user actions on calendar page.
 * */
public class CalendarController {
    /**
     * Image for all tasks menu.
     * */
    @FXML
    private ImageView AddTasksLabel;
    /**
     * Image for Edit menu.
     * */
    @FXML
    private ImageView EditTasksLabel;
    /**
     * Image for all Tasks menu.
     * */
    @FXML
    private ImageView AllTAsksLabel;
    /**
     * Table where tasks will be shown.
     * */
    @FXML
    private TableView<DateCalendar> MainTablewithTasks;
        /**
         * Table column for date.
         * */
    @FXML
    private TableColumn<DateCalendar, Date> dateColumn;
    /**
     * Table column for task description.
     * */
    @FXML
    private TableColumn<DateCalendar, String> taskDescription;
    /**
     * Table column for activeness of task.
     * */
    @FXML
    private TableColumn<DateCalendar, String> Active;
    /**
     * Field where user chooses start date of period.
     * */
    @FXML
    private DatePicker dataStart;
    /**
     * Field where user type end date of period.
     * */
    @FXML
    private DatePicker dataEnd;
    /**
     * Update table with tasks
     * */
    @FXML
    private Button showButton;
    /**
     * Field where user chooses start date of period.
     * */
    @FXML
    private JFXTimePicker StartTime;
    /**
     * Field where user type end date of period.
     * */
    @FXML
    private JFXTimePicker EndTime;
    /**
     * If field is null this text will be shown.
     * */
    @FXML
    private Text warningText;
    /**
     * List where all tasks are saved.
     * */
    private static ObservableList<DateCalendar> tasksCalendar = FXCollections.observableArrayList();
/**
 * Class to show user tasks in table.
 * */
   public class DateCalendar {
       /**
        * Date when task will occur.
        * */
        Date date;
        /**
         * Title of task.
         * */
        String title;
        /**
         * Task activeness
         * */
        boolean active;
        /**
         * @return date when task start.
         * */
        public Date getDate() {
            return date;
        }
        /**
         * @param date  set time when task start.
         * */
        public void setDate(Date date) {
            this.date = date;
        }
        /**
         * @return title of task.
         * */
        public String getTitle() {
            return title;
        }
        /**
        * @param title change title of task to title.
        * */
        public void setTitle(String title) {
            this.title = title;
        }
        /**
         * @return activeness of task.
         * */
        public boolean isActive() {
            return active;
        }
        /**
         * @param active set active task or not.
         * */
        public void setActive(boolean active) {
            this.active = active;
        }
    /**
     * Crete Task with following parameters
     * @param date - date when task happens
     * @param title - name of task
     * @param active  - activeness of task
     * */
        public DateCalendar(Date date, String title, boolean active) {
            this.date = date;
            this.title = title;
            this.active = active;
        }
    }
/**
 * Method initialize buttons and render scene before it is shown.
 * */
    @FXML
    void initialize() {

        warningText.setVisible(false);
        LocalTime localTime = LocalTime.now();
        StartTime.setValue(LocalTime.of(localTime.getHour() + 1, 0));
        EndTime.setValue(LocalTime.of(localTime.getHour() + 1, 0));
        LocalDate localDate = LocalDate.now();
        dataStart.setValue(LocalDate.now());
        dataEnd.setValue(LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth() + 1));
        /**
         * If user clicked AddTasksLabel button, it loads new scene from Controllers.
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
         * If user clicked AllTAsksLabel button, it loads new scene from Controllers.
         */
        AllTAsksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        /**
         * If user clicked EditTasksLabel button, it loads Main scene from Controllers.
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
         * If user clicked showButton button, it checks all field and update table.
         * In another case it shows the warring text.
         * If exception happens it print in console description of it.
         */
        showButton.setOnMouseClicked(e->{

            if(StartTime.getValue() != null && EndTime.getValue() != null && dataEnd.getValue() != null && dataStart.getValue() != null ){
                warningText.setVisible(false);
                StringBuffer bufferStart = new StringBuffer();
                StringBuffer bufferEnd = new StringBuffer();
                bufferStart.append(dataStart.getValue()).append(" ").append(StartTime.getValue());
                bufferEnd.append(dataEnd.getValue()).append(" ").append(EndTime.getValue());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    Date StartD;
                    Date EndD;
                    MainTablewithTasks.getItems().clear();
                    StartD = simpleDateFormat.parse(bufferStart.toString());
                    EndD = simpleDateFormat.parse(bufferEnd.toString());
                    SortedMap<Date, Set<Task>> sortedMap = Tasks.calendar(Model.taskList, StartD, EndD);
                    Set data = sortedMap.keySet();
                    for(Iterator<Date>  iterData = data.iterator(); iterData.hasNext();) {
                        Date date = iterData.next();
                        Set<Task> setTask = sortedMap.get(date);
                        for (Iterator<Task> iter = setTask.iterator(); iter.hasNext(); ) {
                            Task task = iter.next();

                            tasksCalendar.add(new DateCalendar(date, task.getTitle(), task.isActive()));
                        }
                    }
                    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
                    taskDescription.setCellValueFactory(new PropertyValueFactory<>("title"));
                    Active.setCellValueFactory(new PropertyValueFactory<>("active"));
                    MainTablewithTasks.setItems(tasksCalendar);
                } catch (ParseException ex){
                    System.out.println(ex);
                }
            } else {
                warningText.setVisible(true);
            }
        });
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        taskDescription.setCellValueFactory(new PropertyValueFactory<>("title"));
        Active.setCellValueFactory(new PropertyValueFactory<>("active"));
        MainTablewithTasks.setItems(tasksCalendar);
    }
}
