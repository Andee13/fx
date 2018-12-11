package sample.Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sample.Main;

public class CalendarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView AllTAsksLabel;

    @FXML
    private ImageView AddTasksLabel;

    @FXML
    private ImageView EditTasksLabel;

    @FXML
    private TableColumn<?, ?> TitleColumn;

    @FXML
    private TableView<?> MainTablewithTasks;

    @FXML
    private ImageView CalendarTasks;

    @FXML
    private TableColumn<?, ?> StartDate;

    @FXML
    private TableColumn<?, ?> Active;

    @FXML
    private DatePicker dataEnd;

    @FXML
    private DatePicker dataStart;

    @FXML
    private Button showButton;

    @FXML
    private TableColumn<?, ?> End;

    @FXML
    private TextField startTime;

    @FXML
    private TextField endTime;

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
        EditTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });

        showButton.setOnMouseClicked(e->{
            System.out.println(startTime.getText());
            System.out.println(dataStart.getValue());
        });
    }
}
