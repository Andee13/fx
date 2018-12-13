package sample;
import sample.Main;
import sample.model.LinkedTaskList;
import sample.model.Task;
import sample.model.TaskList;
import sample.model.Tasks;

import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

public class Scheduler implements  Runnable{
    public void run(){
        SortedMap<Date, Set<Task>> sortedMap =  Tasks.calendar(Main.taskList, new Date(), new Date( new Date().getTime() + 60000));

        if (){


        }


    }
}
