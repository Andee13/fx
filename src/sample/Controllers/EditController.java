/**
 * Package which has all controllers of application.
 * */
package sample.Controllers;

import java.io.IOException;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Task;
import sample.model.Model;
/**
 * Controller that observe every action of user on this page.
 * */
public class EditController {
    /**
     * Variable that describe table column with date.
     * */
    @FXML
    private TableColumn<Task, Date> StartDate;
    /**
     * ImageView that give an opportunity for user to see scene with all Tasks.
     * */
    @FXML
    private ImageView AllTAsksLabel;
    /**
     * Variable that describe table column with activeness of task.
     * */
    @FXML
    private TableColumn<Task, String> Active;
    /**
     *ImageView that gives an opportunity for user to open scene with functional of adding tasks.
     *  */
    @FXML
    private ImageView AddTasksLabel;
    /**
     * Variable that describe table column with title of task.
     * */
    @FXML
    private TableColumn<Task, String> TitleColumn;
    /**
     * Variable that describe table column with end  time of task.
     * */
    @FXML
    private TableColumn<Task, Date> End;
    /**
     * Variable that describe whole table.
     * */
    @FXML
    private TableView<Task> MainTablewithTasks;
    /**
     *ImageView that gives an opportunity for user to see tasks for a certain period of time.
     *  */
    @FXML
    private ImageView CalendarTasks;
    /**
     * Variable that describe table column with repeated time of the repeated task.
     * */
    @FXML
    private TableColumn<Task, Integer> Interval;
    /**
     * Gives user an ability to redact task  that is chosen.
     * */
    @FXML
    private Button editTask;
    /**
     * Button that allow user to delete the task.
     * */
    @FXML
    private Button deleteTask;
    /**
     * Shows when user enter wrong data in the fields.
     * */
    @FXML
    private Text warnText;
    /**
     * List where all tasks will be shown for user.
     * */
    private ObservableList<Task> tasksFx = FXCollections.observableArrayList();
/**
 * Method initialize buttons and render scene before it is shown.
 * */
    @FXML
    void initialize() {
        warnText.setVisible(false);
        StartDate.setCellValueFactory(new PropertyValueFactory<Task, Date>("startTime"));
        End.setCellValueFactory(new PropertyValueFactory<Task, Date>("EndTime"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        Interval.setCellValueFactory(new PropertyValueFactory<>("RepeatInterval"));
        Active.setCellValueFactory(new PropertyValueFactory<Task, String>("active"));
        tasksFx.addAll(Model.taskList);
        MainTablewithTasks.setItems(tasksFx);
        /**
         * If button pressed it shows new window where you can redact the chosen task.
         * If user doesn't choose any task warning text will be shown.
         * @param e event when button is clicked.
         * */
        editTask.setOnMouseClicked(e ->{
            if(MainTablewithTasks.getSelectionModel().getSelectedItem() != null){
                if (MainTablewithTasks.getSelectionModel().getSelectedItem().getRepeatInterval() != 0) {
                    Task task = new Task(MainTablewithTasks.getSelectionModel().getSelectedItem().getTitle(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getStartTime(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getEndTime(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getRepeatInterval());
                    task.setActive(MainTablewithTasks.getSelectionModel().getSelectedItem().isActive());
                    Model.taskTemp = task;
                    try {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditReg.fxml")));
                        Model.primaryStage.setScene(scene);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                } else {
                    Task task = new Task(MainTablewithTasks.getSelectionModel().getSelectedItem().getTitle(),
                            MainTablewithTasks.getSelectionModel().getSelectedItem().getStartTime());
                    task.setActive(MainTablewithTasks.getSelectionModel().getSelectedItem().isActive());
                    Model.taskTemp = task;
                    try {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditIrreg.fxml")));
                        Model.primaryStage.setScene(scene);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                    Model.writeTasks();
                }
            } else {
                warnText.setVisible(true);
            }
        });
        /**
         * If button pressed it shows new window where user agree or disagree to redact the task.
         * If user doesn't choose any task warning text will be shown.
         * @param e event when button is clicked.
         * */

        deleteTask.setOnMouseClicked(e->{
            /**
             * New window will be shown for user.
             * @param event event that will be give for making a modal window.
             * */
            java.util.function.Consumer<Event> times = (event) -> {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(ModalWindow.class.getResource("/sample/fxmlFiles/ModalWindow.fxml"));
                    stage.setScene(new Scene(root));
                    stage.setTitle("My modal window");
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(
                            ((Node)event.getSource()).getScene().getWindow() );
                    stage.showAndWait();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            };

            if(MainTablewithTasks.getSelectionModel().getSelectedItem() != null) {
                times.accept(e);
                if(ModalWindow.isDeleted) {
                    if (MainTablewithTasks.getSelectionModel().getSelectedItem().getRepeatInterval() != 0) {
                        Task task = new Task(MainTablewithTasks.getSelectionModel().getSelectedItem().getTitle(),
                                MainTablewithTasks.getSelectionModel().getSelectedItem().getStartTime(),
                                MainTablewithTasks.getSelectionModel().getSelectedItem().getEndTime(),
                                MainTablewithTasks.getSelectionModel().getSelectedItem().getRepeatInterval());
                        task.setActive(MainTablewithTasks.getSelectionModel().getSelectedItem().isActive());
                        MainTablewithTasks.getItems().remove(MainTablewithTasks.getSelectionModel().getSelectedItem());
                        warnText.setVisible(false);
                        Model.taskList.remove(task);
                        Model.writeTasks();
                    } else {
                        Task task = new Task(MainTablewithTasks.getSelectionModel().getSelectedItem().getTitle(),
                                MainTablewithTasks.getSelectionModel().getSelectedItem().getStartTime());
                        task.setActive(MainTablewithTasks.getSelectionModel().getSelectedItem().isActive());
                        Model.taskList.remove(task);
                        warnText.setVisible(false);
                        MainTablewithTasks.getItems().remove(MainTablewithTasks.getSelectionModel().getSelectedItem());
                        Model.writeTasks();
                    }
                    ModalWindow.isDeleted = false;
                }
            } else {
                warnText.setVisible(true);
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
        AllTAsksLabel.setOnMouseClicked(e -> {
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
