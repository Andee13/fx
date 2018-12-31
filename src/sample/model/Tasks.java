/**
 * Package describes model of application.
 * It is package where user data saved in appropriate data structure,
 * executed and gives an access to data for another classes of application.
 * * */
package sample.model;

import java.util.Date;
import java.util.Iterator;
import java.util.*;
/**
 * Describe logic of processing taskList and extraction needed data.
 * */
public class Tasks {
/**
 * Method extract tasks that will happened in period of time
 * @param tasks - task List that contains all tasks
 * @param start - data when period of extraction task starts
 * @param end - data when period of extraction task ends
 * @return task_in - task List
 * */
    public static  Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end){
        if(start == null || end == null){
            throw new IllegalArgumentException("null argumet");
        }
        if(start.after(end)) {
            throw new IllegalArgumentException("Unappropriate time");
        }
        TaskList tasks_in = new LinkedTaskList();
       synchronized (tasks) {
           Iterator<Task> iterator = tasks.iterator();

           while (iterator.hasNext()) {
               Task task = iterator.next();
               Date temp = task.nextTimeAfter(start);
               if (temp != null && temp.after(start)) {
                   if (temp.before(end) || temp.equals(end)) {
                       tasks_in.add(task);
                   }
               }
           }
       }
        return tasks_in;
    }
    /**
     * Method extract tasks which will happened in period of time and adds to map time and what task will occur.
     * @param tasks - task List that contains all tasks
     * @param start - data when period of extraction task starts
     * @param end - data when period of extraction task ends
     * @return SortedMap<Date, Set<Task>> - task List
     * */

    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end){
        if(start == null || end == null){
            throw new IllegalArgumentException("null argumete");
        }
        if(start.after(end)) {
            throw new IllegalArgumentException("Unappropriate time");
        }
        SortedMap<Date, Set<Task>> sortedMap =  new TreeMap<Date, Set<Task>>();
        Iterable<Task> tasks_in = incoming(tasks, start, end);
        for(Task temp : tasks_in) {
            for(Date time = temp.nextTimeAfter(start); time != null && time.after(start) && (time.equals(end)|| time.before(end)); time = temp.nextTimeAfter(time)){
                if(sortedMap.containsKey(time)){
                    Set<Task> t = sortedMap.get(time);
                    t.add(temp);
                } else {
                    Set<Task> set = new LinkedHashSet<Task>();
                    set.add(temp);
                    sortedMap.put(time, set);
                }
            }
        }
        return sortedMap ;
    }
}