package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.LinkedTaskList;
import sample.model.Task;
import sample.model.TaskList;

import java.util.Date;


public class Main extends Application {
    public static TaskList taskList = new LinkedTaskList();
    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        taskList.add(new Task("Programming", new Date(), new Date(new Date().getTime()+1000000), 1000));
        taskList.add(new Task("Reading", new Date()));

        Task task = new Task("Walking", new Date());
        task.setActive(true);
        taskList.add(task);
        taskList.add(new Task("Playing games", new Date(), new Date(new Date().getTime()+1000000), 10000));

        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/MainMenu.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
        this.primaryStage = primaryStage;

    }

    @Override
    public void stop(){
        System.out.println("Stop");
    }

    @Override
    public void init(){
        System.out.println("Init");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
