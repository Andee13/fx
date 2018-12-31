/**
 * Package describes model of application.
 * It is package where user data saved in appropriate data structure,
 * executed and gives an access to data for another classes of application.
 * * */
package sample.model;

import javafx.stage.Stage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
/**
 * Class where all model data are saved and where it is accessible for another classes of application
 * */
public class Model {
/**
 * List where tasks retains when program executes.
 * */
    public  static List<Task> taskLis = new LinkedList<Task>();
    /**
     * Synchronized list where tasks retains when program executes.
     * */
    public  static volatile List<Task> taskList = Collections.synchronizedList(taskLis);
    /**
     * Stage where actions of java fx application happens.
     * */
    public static Stage primaryStage;
    /**
     * File where tasks saves.
     * */
    public static File in = new File("Data");
    /**
     * Task that we use as a buffer to edit Controller.
     * */
    public static Task taskTemp;
    /**
     * Icon that arise in the corner of screen.
     * */
    public static TrayIcon trayIcon;

/**
 * Method that writes tasks into file
 * */
    public static void writeTasks(){
        try{
            TaskIO.writeText(taskList, in);

        } catch(IOException ex){
            System.out.println("problem with file " + ex);
        }
    }

}
