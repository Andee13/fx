/**
 * Package describes model of application.
 * It is package where user data saved in appropriate data structure,
 * executed and gives an access to data for another classes of application.
 * * */
package sample.model;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayTaskList extends TaskList implements Cloneable {
        private  int Size = 20;
        private Task[] tasks = new Task[Size];
        private int count = 0;
/**
 * Method that adds task in list
 * */
        public void add(Task task) {
            if(task == null){
                    throw new IllegalArgumentException("Task = null");
            }
            if(count == Size){
                Task[] temp = new Task[(int)(tasks.length * 1.2)];
                System.arraycopy(task, 0, temp, 0 , tasks.length);
                for(int i = 0; i < tasks.length; i++){
                    temp[i] = tasks[i];
                }
                tasks = temp;
            }
            tasks[count] = task;
            count++;
        }
    /**
     * Method that removes task from the list
     * @param task - task to remove
     * */
        public boolean remove(Task task) {
            for(int i = 0; i < tasks.length; i++ ){
                if(tasks[i] == task){
                    tasks[i] = tasks[count - 1];
                    tasks[count - 1] = null;
                    count--;
                    return true;
                }
            }
            return false;
        }
    /**
     * Method return size of list
     * */
        public int size() {
            return count;
        }
    /**
     * Method that get task by index
     * @param index - number of task in list
     * */
        public Task getTask(int index) {
            return tasks[index];
        }
/**
 * Class implements  methods of iterator
 * */
       private class Iter implements Iterator<Task> {
        int currentIndex = -1;
        boolean isNext = false;
        /**
         * Method returns true if in the list exist next element
         * */
        @Override
        public boolean hasNext() {
            return currentIndex + 1 < count;
        }
    /**
     * Method return next element of list
     * */
        @Override
        public Task next() {
            if(!hasNext()){
                throw new NoSuchElementException("NoSuchElementException43343454");
            }
            isNext = true;
            return tasks[++currentIndex];
        }
    /**
     * Method that removes task from list
     * */
        @Override
        public void remove() {
            if(!isNext){
                throw new IllegalStateException("IllegalStateException");
            }
            if(count == 1){
                tasks[currentIndex] = null;
            } else {
               for(int i = currentIndex; i < count - 1 ; i++){
                    tasks[i] = tasks[i + 1];
               }
            }
            currentIndex--;
            isNext = false;
            count--;

        }
    }

/**
 * Method returns iterator
 * */
    @Override
    public Iterator<Task> iterator() {
        return new Iter();
    }
    /**
     * Method clone ArrayList
     * @return ArrayTaskList - exact copy of this task list
     * */
    @Override
    public ArrayTaskList clone() {
        ArrayTaskList obj = (ArrayTaskList)super.clone();
        obj.tasks = tasks.clone();
        return obj;
    }
}
