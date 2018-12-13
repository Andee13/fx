package sample.model;
import java.util.Date;
import java.util.Iterator;
import java.util.*;
public class Tasks {
    public static  Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end){
        if(start == null || end == null){
            throw new IllegalArgumentException("null argumet");
        }
        if(start.after(end)) {
            throw new IllegalArgumentException("Unappropriate time");
        }
       
       Iterator<Task> iterator =  tasks.iterator();
        TaskList tasks_in = new LinkedTaskList();
        while(iterator.hasNext()){
            Task task = iterator.next();
            Date temp = task.nextTimeAfter(start);
            if( temp != null && temp.after(start) ){
                if ( temp.before(end) || temp.equals(end)) {
                    tasks_in.add(task);
                }
            }
            
        }
        return tasks_in;
    }
 
    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end){
        if(start == null || end == null){
            throw new IllegalArgumentException("null argumet");
        }
        if(start.after(end)) {
            throw new IllegalArgumentException("Unpropriate time");
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