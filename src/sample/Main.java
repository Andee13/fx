package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static sample.model.Model.*;


public class Main extends Application {
    /*public  static List<Task> taskLis = new LinkedList<Task>();
    public  static volatile List<Task> taskList = Collections.synchronizedList(taskLis);
    public static Stage primaryStage;
    public static File in = new File("Data");
    public static Task taskTemp;*/


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/MainMenu.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
        Model.primaryStage = primaryStage;

    }

    @Override
    public void stop(){
        try{
            TaskIO.writeText(Model.taskList, in);
        } catch(IOException ex){
            System.out.println("problem with file " + ex);
        }
        System.exit(0);
    }

    @Override
    public void init(){

        if(in != null) {
            try {
                TaskIO.readText(Model.taskList, in);
            } catch (IOException| ParseException e){
                System.out.println("File doesn't opened " + e);
            }
            Runnable r = new Scheduler();
            Thread thread = new Thread(r);
            thread.start();
        } else {
            System.out.println("File doesn't find ");
        }

    }

    public static void main(String[] args) {

        launch(args);


    }
}
