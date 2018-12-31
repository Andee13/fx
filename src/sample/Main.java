/**
 * Package containes Main class that start application.
 * Also it containes Thread Schedular what shows notificatons.
 * It have model where are files that are code of application.
 * Package image where images was saved.
 * Package fxml consist of plain fmxl files.
 * Package Controllers have classes which monitors user actions
 * and react on every of them.
 * Package res has one library - jfoenix-8.0.8 that improve interface.
 * */
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.TaskIO;
import sample.model.Model;
import java.awt.SystemTray;
import java.io.IOException;
import java.text.ParseException;
import static sample.model.Model.trayIcon;
import static sample.model.Model.in;
import static sample.model.Model.taskList;
/**
 * This class is class with main method. It start application
 * It extends Application.
 * */
public class Main extends Application {
    /**
     * Method start an application*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/MainMenu.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
        Model.primaryStage = primaryStage;

    }
    /**
     * Method stops will be run when application stops.
     * It writes List that contains Tasks into file.
     * Then it removes icon in the systemTry.
     * */
    @Override
    public void stop() {
        try {
            TaskIO.writeText(taskList, in);
        } catch (IOException ex) {
            System.out.println("problem with file " + ex);
        }
        SystemTray.getSystemTray().remove(trayIcon);
    }

    /**
     * Method start execution before application start
     * and it reads Tasks from file.
     * If file doesn't exist it print in console "File doesn't find".
     * If exception happens it will print "File doesn't opened "
     * and description of exception.
     *
     * */
    @Override
    public void init() {
        if (in != null) {
            try {
                TaskIO.readText(Model.taskList, in);
            } catch (IOException | ParseException e) {
                System.out.println("File doesn't opened " + e);
            }
        } else {
            System.out.println("File doesn't find ");
        }
    }
/**
 * Main method is point of execution whole application.
 * It start new Thread that will inform user about Tasks to do.
 * Runs JavaFX methods such as start and init.
 * @param args - parameters given throw console.
 * */
    public static void main(String[] args) {
        Runnable r = new Scheduler();
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        thread.start();
        launch(args);

    }
}
