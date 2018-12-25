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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.model.Model;
import sample.model.Task;
import sample.model.TaskIO;

import static sample.model.Model.in;
import static sample.model.Model.taskList;

public class AddIrregularController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button irregularTasks;

    @FXML
    private JFXTimePicker TaskTime;

    @FXML
    private DatePicker TaskDate;

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
    private Text wrongText;


    @FXML
    void initialize() {

        wrongText.setVisible(false);

        EditTasksLabel.setOnMouseClicked(e -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EditTasks.fxml")));
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

        TaskTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                /*System.out.println("oldValue = " + oldValue);
                 */
                System.out.println("newValue = " + newValue);

                if (newValue.matches("^\\s")) {
                    TaskTitle.setText(newValue.replaceAll("^\\s+", ""));
                }
            }
        });

        REgularButton.setOnMouseClicked(e ->{
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AddTasksRegular.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        CancelButton.setOnMouseClicked(e ->{
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                Model.primaryStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        AddTaskButton.setOnMouseClicked(e ->{


                String title = TaskTitle.getText().trim();
                if (title.length() != 0 && TaskTime.getValue() != null && TaskDate.getValue() != null){



                        StringBuffer bufferStart = new StringBuffer();

                        bufferStart.append(TaskDate.getValue()).append(" ").append(TaskTime.getValue());

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


                        System.out.println(bufferStart);
                        try{
                            Date Start;
                            Start = simpleDateFormat.parse(bufferStart.toString());
                            Task task = new Task(title, Start);
                            task.setActive(true);
                            Model.taskList.add(task);
                } catch (ParseException ex) {
                System.out.println(ex);
            }


//                    try{
//                        TaskIO.writeText(taskList, in);
//                    } catch(IOException ex){
//                        System.out.println("problem with file " + ex);
//                    }
                    try{
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/MainMenu.fxml")));
                        Model.primaryStage.setScene(scene);
                    }catch (IOException  ex){
                        System.out.println(ex);
                    }
                } else {
                    wrongText.setVisible(true);
                }


        });
    }
}
