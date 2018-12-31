/**
 * Package describes model of application.
 * It is package where user data saved in appropriate data structure,
 * executed and gives an access to data for another classes of application.
 * * */
package sample.model;

import java.io.Serializable;
import java.util.Iterator;
/**
 * Describe structure of taskList and define methods list will work with.
 * */
public abstract class TaskList implements Iterable<Task>, Serializable{
    /**
     * Method that add task to taskList.
     * @param task -task to add to task list
     * */
    public abstract void add(Task task);
    /**
     * Method that remove task from taskList.
     * @param task - task to remove.
     * */
    public abstract boolean remove(Task task);
    /**
     * Method that give task be its index.
     * @param index - number of tasks
     * */
    public abstract Task getTask(int index);
    /**
     * Returns size of list.
     * */
    public abstract int size();
    /**
     * Method that check if the lists are equal.
     * */
    @Override
    public boolean equals(Object obj) {
        if( this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }

        
        TaskList temp = (TaskList)obj;

        if( size() != temp.size() ){
            return false;
        }

        Iterator<Task> iter = this.iterator();
        for(Iterator<Task> itertmp = temp.iterator(); iter.hasNext() && itertmp.hasNext();){
            if ( !iter.next().equals(itertmp.next()) ){
                return false;
            }
        }
        return true;
    }
    /**
     * Method that return hash code of list.
     * @return hash - of task list*/
    @Override
    public int hashCode() {
        int hash = 0;
        for(Task task : this){
            hash +=task.hashCode();
        }
        return hash;
    }

    /**
     * Method that clone task.
     * */
    protected TaskList clone() {
        try {
            return (TaskList)super.clone();
        } catch(CloneNotSupportedException ex) {
            ex.printStackTrace();
            throw new InternalError();
        }
    }
}
