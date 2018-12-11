package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import sample.Main;

public class EditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> StartDate;

    @FXML
    private ImageView AllTAsksLabel;

    @FXML
    private TableColumn<?, ?> Active;

    @FXML
    private ImageView AddTasksLabel;

    @FXML
    private ImageView EditTasksLabel;

    @FXML
    private TableColumn<?, ?> TitleColumn;

    @FXML
    private TableColumn<?, ?> End;

    @FXML
    private TableView<?> MainTablewithTasks;

    @FXML
    private ImageView CalendarTasks;

    @FXML
    private TableColumn<?, ?> Interval;

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
