package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.text.Text;

import sample.model.Model;
import sample.model.Task;
import sample.model.TaskIO;
import sample.model.Tasks;

import static sample.model.Model.in;

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
    private Text wrongInput;

    @FXML
    void initialize() {
        wrongInput.setVisible(false);
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






        titleOfTask.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                /*System.out.println("oldValue = " + oldValue);
                 */
                System.out.println("newValue = " + newValue);

                if (newValue.matches("^\\s")) {
                    titleOfTask.setText(newValue.replaceAll("^\\s+", ""));
                }
            }
        });




        CanccelButton.setOnMouseClicked(e ->{
            try {
                Model.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml"))));
            } catch (IOException ex){
                System.out.println(ex);
            }
        });
        AddTasksButton.setOnMouseClicked(e ->{

            try {
                String title = titleOfTask.getText().trim();
                if(TimeStart.getValue() != null && TimeEnd.getValue() != null && DataEnd.getValue() != null && dataStart.getValue() != null
                        && titleOfTask.getText() != null  && !"".equals(title)) {
                    StringBuffer bufferStart = new StringBuffer();
                    StringBuffer bufferEnd = new StringBuffer();
                    bufferStart.append(dataStart.getValue()).append(" ").append(TimeStart.getValue());
                    bufferEnd.append(DataEnd.getValue()).append(" ").append(TimeEnd.getValue());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date StartD;
                    Date EndD;
                    System.out.println(bufferStart);
                    System.out.println(bufferEnd);
                    StartD = simpleDateFormat.parse(bufferStart.toString());
                    EndD = simpleDateFormat.parse(bufferEnd.toString());
                    String intervalStr = ChoiseBoxRegular.getValue();
                    int Interval;
                    switch(intervalStr){
                        case "Days":
                            Interval = Integer.parseInt(this.Interval.getText()) *86400;
                            break;
                        case "Hours":
                            Interval = Integer.parseInt(this.Interval.getText()) *3600;
                            break;
                        case "Minutes":
                            Interval = Integer.parseInt(this.Interval.getText()) * 60;
                            break;
                            default: Interval = 0;
                            break;
                    }
                    Task task = new Task(title, StartD,EndD, Interval);
                    task.setActive(true);
                    Model.taskList.add(task);
                    Model.writeTasks();
                    Model.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml"))));
                } else {
                    wrongInput.setVisible(true);

                }

            } catch (IOException |ParseException ex ) {
                System.out.println(ex);
            }
        });
        ChoseIrregular.setOnMouseClicked(e ->{
            try {
                Model.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksIrregular.fxml"))));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println(ex);
            }
        });

    }
}