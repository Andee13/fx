package sample;
import sample.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class Scheduler implements Runnable{
    public void run() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
        }

        SystemTray systemTray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().getImage("sample/images/list.png");
        PopupMenu trayPopupMenu = new PopupMenu();
        MenuItem action = new MenuItem("Action");
        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Action Clicked");
            }
        });
        trayPopupMenu.add(action);

        MenuItem close = new MenuItem("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        trayPopupMenu.add(close);
        TrayIcon trayIcon = new TrayIcon(image, "SystemTray Demo", trayPopupMenu);
        //adjust to default size as per system recommendation
        trayIcon.setImageAutoSize(true);

        try{
            systemTray.add(trayIcon);
        }catch(AWTException awtException){
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
