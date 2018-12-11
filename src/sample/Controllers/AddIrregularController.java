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

public class AddIrregularController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button irregularTasks;

    @FXML
    private Button REgularButton;

    @FXML
    private ImageView AllTasksLabel;

    @FXML
    private ImageView EditTasksLabel;

    @FXML
    private Button AddTaskButton;

    @FXML
    private TextArea TaskTitle;

    @FXML
    private Button CancelButton;

    @FXML
    private ImageView AddTasksLabel;

    @FXML
    private ImageView CalendarTasks;

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
        AllTasksLabel.setOnMouseClicked(e -> {
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


        CancelButton.setOnMouseClicked(e ->{
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        AddTaskButton.setOnMouseClicked(e ->{
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Main.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
    }
}
