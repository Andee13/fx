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
                StringBuffer bufferStart = new StringBuffer();
                StringBuffer bufferEnd = new StringBuffer();
                bufferStart.append(dataStart.getValue()).append(" ").append(StartTime.getValue());
                bufferEnd.append(dataEnd.getValue()).append(" ").append(EndTime.getValue());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    Date StartD;
                    Date EndD;
//                    System.out.println(bufferStart);
//                    System.out.println(bufferEnd);
                    MainTablewithTasks.getItems().clear();
                    StartD = simpleDateFormat.parse(bufferStart.toString());
                    EndD = simpleDateFormat.parse(bufferEnd.toString());
                    SortedMap<Date, Set<Task>> sortedMap = Tasks.calendar(Main.taskList, StartD, EndD);
                    Set data = sortedMap.keySet();
                    for(Iterator<Date>  iterData = data.iterator(); iterData.hasNext();){
                        Date date = iterData.next();
                        Set<Task> setTask = sortedMap.get(date);
                        for(Iterator<Task> iter = setTask.iterator();iter.hasNext();){
                            Task task = iter.next();
                            tasksCalendar.add(new DateCalendar(date, task.getTitle(), task.isActive()));
                        }


                        //Date date = iterData.next();
                        //Set<Task> taskSet = sortedMap.get(date);


                        ////tasksCalendar.addAll(sortedMap.get(iterData.next()));


                        //for(Iterator<Task> taskIterator = taskSet.iterator();taskIterator.hasNext();){
                        //}
                    }

//                    DateColumn = new TableColumn<>("Date");
//                    DateColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
//
//                    TaskDescription = new TableColumn<>("Title");
//                    TaskDescription.setCellValueFactory(new PropertyValueFactory<>("title"));
//
//                    Active = new TableColumn<>("Active");
//                    Active.setCellValueFactory(new PropertyValueFactory<>("active"));

                   // MainTablewithTasks.getColumns().setAll(DateColumn,TaskDescription,Active);
                    // MainTablewithTasks.

                    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

                    taskDescription.setCellValueFactory(new PropertyValueFactory<>("title"));

                    Active.setCellValueFactory(new PropertyValueFactory<>("active"));
                    MainTablewithTasks.setItems(tasksCalendar);


//                    System.out.println(StartD);
//                    System.out.println(EndD);
                } catch (ParseException ex){
                    System.out.println(ex);
                }

            }

        });


    }
}
