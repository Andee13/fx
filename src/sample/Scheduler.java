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

import sample.model.Model;
import sample.model.Task;
import sample.model.Tasks;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
/**
 * Class implements interface runnable.
 * Start a thread and show notification*/
public class Scheduler implements Runnable {
    /**
     * Create SystemTryIcon and check List of Tasks.
     * If there are Tasks in this time it will inform user.
     * */
    public void run() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
        }

        SystemTray systemTray = SystemTray.getSystemTray();

        Image image = new ImageIcon(this.getClass().getResource("/sample/images/list.png")).getImage();
        PopupMenu trayPopupMenu = new PopupMenu();
        MenuItem action = new MenuItem("Action");
        /**
         * Event listener for action clicked on button action.
         * */
        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Action Clicked");
            }
        });
        trayPopupMenu.add(action);

        MenuItem close = new MenuItem("Close");
        trayPopupMenu.add(close);
        TrayIcon trayIcon = new TrayIcon(image, "SystemTray Demo", trayPopupMenu);
        Model.trayIcon = trayIcon;
        /**
         * Event listener for action clicked on button close.
         * Stops application when it clicked.
         * */
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                systemTray.remove(trayIcon);
                System.exit(0);
            }
        });

        trayIcon.setImageAutoSize(true);

        try {
            systemTray.add(trayIcon);
        } catch (AWTException awtException) {
            awtException.printStackTrace();
        }
        while (true) {
            Long temp = new Date().getTime();
            Date StartD = new Date(temp - temp % 60000);
            Date EndD = new Date(new Date().getTime() + 60000);
            SortedMap<Date, Set<Task>> sortedMap = Tasks.calendar(Model.taskList, StartD, EndD);
            Set<Date> setKey = sortedMap.keySet();
            for (Iterator<Date> dateIter = setKey.iterator(); dateIter.hasNext(); ) {
                Date DateKey = dateIter.next();
                Set<Task> taskSet = sortedMap.get(DateKey);
                for (Iterator<Task> iterator = taskSet.iterator(); iterator.hasNext(); ) {
                    Task task = iterator.next();
                    trayIcon.displayMessage("Опеващение о задаче\n",task.getTitle(), TrayIcon.MessageType.valueOf("INFO"));
                    System.out.println("Time to do this task -- " + task.getTitle());
                }
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}
