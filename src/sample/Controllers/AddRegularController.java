package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import sample.Main;

public class AddRegularController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddTasks;

    @FXML
    private Button CanccelButton;

    @FXML
    private ImageView AllTasksLabel;

    @FXML
    private TextArea EndTime;

    @FXML
    private ImageView AddTasksLabel;

    @FXML
    private ImageView EditTasksLabel;

    @FXML
    private TextArea StartTime;

    @FXML
    private Button ChoseIrregular;

    @FXML
    private TextArea titleOfTask;

    @FXML
    private ImageView CalendarTasks;

    @FXML
    private TextArea Interval;

    @FXML
    void initialize() {

    CanccelButton.setOnMouseClicked(e ->{
            try {
                Main.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml"))));
            } catch (IOException ex){
                System.out.println(ex);
            }
        });

        AllTasksLabel.setOnMouseClicked(e ->{
            try {
                Main.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml"))));
            } catch (IOException ex){
                System.out.println(ex);
            }
        });

    }
}