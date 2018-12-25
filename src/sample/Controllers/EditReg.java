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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import sample.model.Model;
import sample.model.Model;
import sample.model.Task;

public class EditReg {

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
    ObservableList<String> timeInterval = FXCollections.observableArrayList("Days", "Hours", "Minutes");
    @FXML
    private ChoiceBox<String> ChoiseBoxRegular;

    @FXML
    private CheckBox activeCheckBox;



    @FXML
    void initialize() {
        Task task = Model.taskTemp;
        Model.taskTemp = null;
        titleOfTask.setText(task.getTitle());
        Date dateS = task.getStartTime();
        LocalDate dateStart = dateS.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date dateE = task.getEndTime();
        LocalDate localDateEnd = dateE.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateStart = LocalDate.of(dateStart.getYear(),dateStart.getMonth(),dateStart.getDayOfMonth());
        dataStart.setValue(localDateStart);
        TimeStart.setValue(LocalTime.of(dateS.getHours(), dateS.getMinutes()));
        TimeEnd.setValue(LocalTime.of(dateE.getHours(), dateE.getMinutes()));
        DataEnd.setValue(LocalDate.of(localDateEnd.getYear(),localDateEnd.getMonth(), localDateEnd.getDayOfMonth()));
        if(task.isActive()){
            activeCheckBox.setSelected(true);
        } else {
            activeCheckBox.setSelected(false);
        }
        ChoiseBoxRegular.setItems(timeInterval);
        ChoiseBoxRegular.setValue("Minutes");
        Interval.setText(String.valueOf(task.getRepeatInterval() / 60));
        AddTasksButton.setOnMouseClicked(e-> {
            String title = titleOfTask.getText().trim();
            if(title.length() != 0){
                try {

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
                    switch (intervalStr) {
                        case "Days":
                            Interval = Integer.parseInt(this.Interval.getText()) *86400;
                            break;
                        case "Hours":
                            Interval = Integer.parseInt(this.Interval.getText()) *3600;
                            break;
                        case "Minutes":
                            Interval = Integer.parseInt(this.Interval.getText()) * 60;
                            break;
                        default:
                            Interval = 0;
                            break;
                    }
                    Task task1 =  new Task(title, StartD, EndD, Interval);
                    task1.setActive(activeCheckBox.isSelected());
                    Model.taskList.remove(task);
                    Model.taskList.add(task1);

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
                    Model.primaryStage.setScene(scene);



                }catch (ParseException|IOException ex){
                    System.out.println(ex);
                }


            } else {


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
