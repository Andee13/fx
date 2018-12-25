package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

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
import sample.Main;
import javafx.scene.image.ImageView;

public class MainController {


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
    private ImageView AllTasksLabel;
    @FXML
    private TableColumn<Task, Date> StartDate;

    @FXML
    private TableColumn<Task, String> Active;

    @FXML
    private TableColumn<Task, Integer> Interval;

    @FXML
    private TableColumn<Task, String> TitleColumn;

    @FXML
    private TableView<Task> MainTablewithTasks;

    @FXML
    private TableColumn<Task, Date> End;

    private ObservableList<Task>  tasksFx = FXCollections.observableArrayList();

    @FXML
    void initialize() {


        StartDate.setCellValueFactory(new PropertyValueFactory<Task, Date>("startTime"));
        End.setCellValueFactory(new PropertyValueFactory<Task, Date>("EndTime"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        Interval.setCellValueFactory(new PropertyValueFactory<>("RepeatInterval"));
        Active.setCellValueFactory(new PropertyValueFactory<Task, String>("active"));
        tasksFx.setAll(Model.taskList);
        //initData();

        MainTablewithTasks.setItems(tasksFx);

        AddTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksRegular.fxml")));
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
        CalendarTasks.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/CheckCalendar.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });

    }
    private void initData(){
        for(Task t: Model.taskList){
            tasksFx.add(t);
        }
    }
}
