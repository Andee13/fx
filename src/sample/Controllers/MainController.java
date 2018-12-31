/**
 * Package which has all controllers of application.
 * */
package sample.Controllers;

import java.io.IOException;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Model;
import sample.model.Task;
import javafx.scene.image.ImageView;
/**
 * Describe logic of main window application.
 * */
public class MainController {
    /**
     * Image for all Tasks scene.
     * */
    @FXML
    private ImageView AddTasksLabel;
    /**
     * Image for edit Tasks scene.
     * */
    @FXML
    private ImageView EditTasksLabel;
    /**
     * Image for calendar Tasks scene.
     * */
    @FXML
    private ImageView CalendarTasks;
    /**
     * Variable describe start date of task column that will be shown to user in the table.
     * */
    @FXML
    private TableColumn<Task, Date> StartDate;
    /**
     * Variable describe activeness of task column that will be shown to user in the table.
     * */
    @FXML
    private TableColumn<Task, String> Active;
    /**
     * Variable describe interval of repeated task column that will be shown to user in the table.
     * */
    @FXML
    private TableColumn<Task, Integer> Interval;
    /**
     * Variable describe title column that will be shown to user in the table.
     * */
    @FXML
    private TableColumn<Task, String> TitleColumn;
    /**
     * Variable describe table that will be shown to user in the table.
     * */
    @FXML
    private TableView<Task> MainTablewithTasks;
    /**
     * Variable describe end data column that will be shown to user in the table.
     * */
    @FXML
    private TableColumn<Task, Date> End;
    /**
     * Variable describe list that will be shown to user in the table.
     * */
    private ObservableList<Task>  tasksFx = FXCollections.observableArrayList();
    /**
     * Method initialize buttons and render scene before it is shown.
     * */
    @FXML
    void initialize() {
        StartDate.setCellValueFactory(new PropertyValueFactory<Task, Date>("startTime"));
        End.setCellValueFactory(new PropertyValueFactory<Task, Date>("EndTime"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        Interval.setCellValueFactory(new PropertyValueFactory<>("RepeatInterval"));
        Active.setCellValueFactory(new PropertyValueFactory<Task, String>("active"));
        tasksFx.setAll(Model.taskList);
        MainTablewithTasks.setItems(tasksFx);
        /**
         * If user clicked AllTAsksLabel button, it loads AllTasks scene from Controllers.
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
     * If user clicked EditTasksLabel button, it loads Edit scene from Controllers.
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
