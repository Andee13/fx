package sample;

import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.ArrayTaskList;
import sample.model.Task;
import sample.model.TaskList;

public class Controller {
    private ObservableList<Task>  tasksFx = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Task, Date> StartDate;

    @FXML
    private TableColumn<Task, String> Active;

    @FXML
    private TableColumn<Task, Integer> IntervalBetween;

    @FXML
    private TableColumn<Task, String> TitleColumn;

    @FXML
    private TableView<Task> MainTablewithTasks;

    @FXML
    private TableColumn<Task, String> EndDate;

    @FXML
    void initialize() {
        initData();
        ObservableList<Task> data = MainTablewithTasks.getItems();
        StartDate.setCellValueFactory(new PropertyValueFactory<Task, Date>("start"));
        EndDate.setCellValueFactory(new PropertyValueFactory<Task, String>("EndDate"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        IntervalBetween.setCellValueFactory(new PropertyValueFactory<Task, Integer>("Interval"));
        Active.setCellValueFactory(new PropertyValueFactory<Task, String>("active"));



        MainTablewithTasks.setItems(tasksFx);

    }
    private void initData(){
        TaskList taskList = new ArrayTaskList();

        taskList.add(new Task("Reading", new Date()));
        taskList.add(new Task("Programming", new Date(), new Date(new Date().getTime()+1000000), 1000));
        Task task = new Task("Walking", new Date());
        task.setActive(true);
        taskList.add(task);
        taskList.add(new Task("Playing games", new Date(), new Date(new Date().getTime()+1000000), 10000));
        for(Task t: taskList) {
            System.out.println(t.getEndTime());
            tasksFx.add(t);
        }

    }
}
