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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.Main;
import sample.model.Task;

public class EditController {



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
    private Button editTask;

    @FXML
    private Button deleteTask;

    @FXML
    private Text warnText;

    private ObservableList<Task> tasksFx = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        warnText.setVisible(false);
        StartDate.setCellValueFactory(new PropertyValueFactory<Task, Date>("startTime"));
        End.setCellValueFactory(new PropertyValueFactory<Task, Date>("EndTime"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        Interval.setCellValueFactory(new PropertyValueFactory<>("RepeatInterval"));
        Active.setCellValueFactory(new PropertyValueFactory<Task, String>("active"));



        tasksFx.addAll(Main.taskList);
        MainTablewithTasks.setItems(tasksFx);
        editTask.setOnMouseClicked(e ->{
            if(MainTablewithTasks.getSelectionModel().getSelectedItem() != null){
                if (MainTablewithTasks.getSelectionModel().getSelectedItem().getRepeatInterval() != 0) {
                    Task task = new Task(MainTablewithTasks.getSelectionModel().getSelectedItem().getTitle(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getStartTime(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getEndTime(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getRepeatInterval());
                    task.setActive(MainTablewithTasks.getSelectionModel().getSelectedItem().isActive());
                    Main.taskTemp = task;
                    try {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditReg.fxml")));
                        Main.primaryStage.setScene(scene);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }


                } else {
                    Task task = new Task(MainTablewithTasks.getSelectionModel().getSelectedItem().getTitle(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getStartTime());
                    task.setActive(MainTablewithTasks.getSelectionModel().getSelectedItem().isActive());
                    Main.taskTemp = task;
                    try {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditIrreg.fxml")));
                        Main.primaryStage.setScene(scene);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }

            } else {
                warnText.setVisible(false);
            }
        });
        deleteTask.setOnMouseClicked(e->{
            if(MainTablewithTasks.getSelectionModel().getSelectedItem() != null) {
                if (MainTablewithTasks.getSelectionModel().getSelectedItem().getRepeatInterval() != 0) {
                    Task task = new Task(MainTablewithTasks.getSelectionModel().getSelectedItem().getTitle(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getStartTime(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getEndTime(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getRepeatInterval());
                    task.setActive(MainTablewithTasks.getSelectionModel().getSelectedItem().isActive());
                    MainTablewithTasks.getItems().remove(MainTablewithTasks.getSelectionModel().getSelectedItem());
                    //System.out.println(task.isActive());
                    warnText.setVisible(false);
                    Main.taskList.remove(task);
                } else {
                    Task task = new Task(MainTablewithTasks.getSelectionModel().getSelectedItem().getTitle(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getStartTime());
                    task.setActive(MainTablewithTasks.getSelectionModel().getSelectedItem().isActive());
                    Main.taskList.remove(task);
                    warnText.setVisible(false);
                    MainTablewithTasks.getItems().remove(MainTablewithTasks.getSelectionModel().getSelectedItem());
                }
            } else {
                warnText.setVisible(true);
            }
        });

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

}
