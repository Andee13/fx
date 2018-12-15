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
import javafx.scene.image.ImageView;
import sample.Main;
import sample.model.Task;

public class EditController {

    private ObservableList<Task> tasksFx = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Task, Date> StartDate;

    @FXML
    private ImageView AllTAsksLabel;

    @FXML
    private TableColumn<Task, String> Active;

    @FXML
    private ImageView AddTasksLabel;

    @FXML
    private ImageView EditTasksLabel;

    @FXML
    private TableColumn<Task, String> TitleColumn;

    @FXML
    private TableColumn<Task, Date> End;

    @FXML
    private TableView<Task> MainTablewithTasks;

    @FXML
    private ImageView CalendarTasks;

    @FXML
    private TableColumn<Task, Integer> Interval;

    @FXML
    void initialize() {

        StartDate.setCellValueFactory(new PropertyValueFactory<Task, Date>("start"));
        End.setCellValueFactory(new PropertyValueFactory<Task, Date>("End"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        Interval.setCellValueFactory(new PropertyValueFactory<>("Interval"));
        Active.setCellValueFactory(new PropertyValueFactory<Task, String>("active"));
       // initData();

        MainTablewithTasks.setItems(tasksFx);

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
        CalendarTasks.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/CheckCalendar.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });


    }
//    private void initData(){
//        for(Task t: Main.taskList){
//            tasksFx.add(t);
//        }
//    }
}
