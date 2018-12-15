package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class Main extends Application {
    //public static TaskList taskList = new ArrayTaskList();
    public  static List<Task> taskList = new LinkedList<Task>();
    public static Stage primaryStage;
    public static File in = new File("Data");
    @Override
    public void start(Stage primaryStage) throws Exception{




//        taskList.add(new Task("Programming", new Date(), new Date(new Date().getTime()+1000000), 1000));
//        taskList.add(new Task("Reading", new Date()));

        //Task task = new Task("Walking", new Date());
        //task.setActive(true);
        //taskList.add(task);
        //taskList.add(new Task("Playing games", new Date(), new Date(new Date().getTime()+1000000), 10000));

        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/MainMenu.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
        this.primaryStage = primaryStage;

    }

    @Override
    public void stop(){
        try{
            TaskIO.writeText(taskList, in);
        } catch(IOException ex){
            System.out.println("problem with file " + ex);
        }
    }

    @Override
    public void init(){
        if(in != null) {
            try {
                TaskIO.readText(taskList, in);
            } catch (IOException| ParseException e){
                System.out.println("File doesn't opened " + e);
            }
        } else {
            taskList = new ArrayTaskList();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
