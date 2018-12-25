package sample.Controllers;


import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import sample.model.Model;
import sample.model.Task;
import sample.model.Tasks;

import static sample.model.Tasks.calendar;

public class CalendarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView AddTasksLabel;

    @FXML
    private ImageView EditTasksLabel;

    @FXML
    private ImageView CalendarTasks;

    @FXML
    private ImageView AllTAsksLabel;

    @FXML
    private TableView<DateCalendar> MainTablewithTasks;

    @FXML
    private TableColumn<DateCalendar, Date> dateColumn;

    @FXML
    private TableColumn<DateCalendar, String> taskDescription;

    @FXML
    private TableColumn<DateCalendar, String> Active;


    @FXML
    private DatePicker dataStart;

    @FXML
    private DatePicker dataEnd;

    @FXML
    private Button showButton;

    @FXML
    private JFXTimePicker StartTime;

    @FXML
    private JFXTimePicker EndTime;

    @FXML
    private Text warningText;

    private ObservableList<DateCalendar> tasksCalendar = FXCollections.observableArrayList();

   public class DateCalendar {
        Date date;
        String title;
        boolean active;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public DateCalendar(Date date, String title, boolean active) {
            this.date = date;
            this.title = title;
            this.active = active;
        }
    }

    @FXML
    void initialize() {
        warningText.setVisible(false);
        AddTasksLabel.setOnMouseClicked(e -> {
            try {

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksRegular.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        AllTAsksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        EditTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });

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


    }
}
