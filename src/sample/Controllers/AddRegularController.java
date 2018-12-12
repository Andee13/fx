package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTimePicker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import sample.Main;

public class AddRegularController {
    ObservableList<String> timeInterval = FXCollections.observableArrayList("Days", "Hours", "Minutes");
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
    private TextArea titleOfTask;

    @FXML
    private Button ChoseIrregular;

    @FXML
    private Button AddTasksButton;

    @FXML
    private TextArea Interval;

    @FXML
    private Button CanccelButton;

    @FXML
    private JFXTimePicker TimeStart;

    @FXML
    private DatePicker dataStart;

    @FXML
    private JFXTimePicker TimeEnd;

    @FXML
    private DatePicker DataEnd;

    @FXML
    private ChoiceBox<String> ChoiseBoxRegular;

    @FXML
    void initialize() {
        ChoiseBoxRegular.setItems(timeInterval);
        Interval.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    Interval.setText(newValue.replaceAll("[^\\d]", ""));
                }
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
        EditTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
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
        CanccelButton.setOnMouseClicked(e ->{
            try {
                Main.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml"))));
            } catch (IOException ex){
                System.out.println(ex);
            }
        });


        AddTasksButton.setOnMouseClicked(e ->{
            try {
            //Write code for tasks adding

                Main.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml"))));
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        ChoseIrregular.setOnMouseClicked(e ->{
            try {


                Main.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksIrregular.fxml"))));
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });

    }
}