package sample.Controllers;

import com.jfoenix.controls.JFXTimePicker;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.model.Model;
import sample.model.Task;

public class EditIrreg {

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
    private Button AddTasksButton;

    @FXML
    private Button CanccelButton;

    @FXML
    private JFXTimePicker TimeStart;

    @FXML
    private DatePicker dataStart;

    @FXML
    private CheckBox ChoiseBox;

    @FXML
    private Text warningText;

    @FXML
    void initialize() {
        warningText.setVisible(false);
        Task task = Model.taskTemp;
        Model.taskTemp = null;
        titleOfTask.setText(task.getTitle());
        LocalDate localDate = task.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalTime localTime = task.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        dataStart.setValue(localDate);
        TimeStart.setValue(localTime);
        ChoiseBox.setSelected(task.isActive());
        AddTasksButton.setOnMouseClicked(e ->{

            String title = titleOfTask.getText().trim();
            if(title.length() != 0) {
            try {
                StringBuffer bufferStart = new StringBuffer();
                bufferStart.append(dataStart.getValue()).append(" ").append(TimeStart.getValue());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                Date Start = simpleDateFormat.parse(bufferStart.toString());
                Task task1 = new Task(title, Start);
                task1.setActive(ChoiseBox.isSelected());

                Model.taskList.remove(task);
                Model.taskList.add(task1);

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException | ParseException ex){
                System.out.println(ex);
            }

            } else {
                warningText.setVisible(true);
            }
        });




        CanccelButton.setOnMouseClicked(event -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        AddTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksRegular.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
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
