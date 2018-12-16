package sample;
import sample.Main;
import sample.model.LinkedTaskList;
import sample.model.Task;
import sample.model.TaskList;
import sample.model.Tasks;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class Scheduler /*implements  Runnable*/{
    public void run(){
        Long temp  = new Date().getTime();
        Date StartD = new Date(temp - temp % 60000);
        Date EndD = new Date(new Date().getTime() + 60000);
        SortedMap<Date, Set<Task>> sortedMap =  Tasks.calendar(Main.taskList, StartD, EndD);
        Set<Date> setKey = sortedMap.keySet();
       for(Iterator<Date> dateIter = setKey.iterator(); dateIter.hasNext();){
           Date DateKey = dateIter.next();
           Set<Task> taskSet = sortedMap.get(DateKey);
           for(Iterator iterator = taskSet.iterator();iterator.hasNext();){

               System.out.println("Time to do this task");
           }

       }
       try {
           Thread.sleep(60000);
       }catch (InterruptedException ex){
           System.out.println(ex);
       }
    }
}
