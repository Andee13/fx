package sample.model;

import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Model {

    public  static List<Task> taskLis = new LinkedList<Task>();
    public  static volatile List<Task> taskList = Collections.synchronizedList(taskLis);
    public static Stage primaryStage;
    public static File in = new File("Data");
    public static Task taskTemp;
    /*static {
        in = new File("Da");
    }*/


    public static void writeTasks(){System.out.println("ok++++");
        try{
            TaskIO.writeText(taskList, in);

        } catch(IOException ex){
            System.out.println("problem with file " + ex);
        }
    }

}
