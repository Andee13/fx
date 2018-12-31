/**
 * Package describes model of application.
 * It is package where user data saved in appropriate data structure,
 * executed and gives an access to data for another classes of application.
 * * */
package sample.model;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class that describe structure of LinkedTaskList
 * */
public class LinkedTaskList extends TaskList implements Cloneable {
        private Node first;
        private Node last;
        private int count = 0;
        /**
         * Class that describe Node of list
         * */
        private class Node implements  Cloneable {
            private Task task;
            private Node next;
/**
 * Empty constructor
 * */
            public Node() {}
            /**
             * Constructor of Node
             * @param task - task that saves in node
             * */
            public Node( Task task ) {
                this.task = task;
            }
            /**
             * Method clone Node
             * @return Node - exact copy of this Node
             * */
            @Override
            public Node clone(){
            try{
                Node obj = (Node)super.clone(); 
                obj.task = task.clone();
                return obj;
            } catch(CloneNotSupportedException ex) {
            throw new InternalError();
            }
           }
        }
/**
 * Method add task to task list
 * @param task - task to add
 * */
        public void add(Task task) {
                if(task == null){
                        throw new IllegalArgumentException("Task = null");
                    }
                if(first == null){
                        first = last = new Node(task);
                        count++;
                        return;
                }
                if(first != null) {
                        Node temp = new Node(task);
                        last.next = temp;
                        last = temp;
                        count++;
                }

        }
    /**
     * Method add task to task list
     * @param task - task to remove
     * */
        public boolean remove(Task task){
                Node tempFather = first;
                Node tempSon = first.next;
                if(tempSon == null && first.task == task){
                        first = last = null;
                        count--;
                        return true;
                }

                if(tempFather.task == task) {
                        first = tempSon;
                        count--;
                        return true;
                }
                while(tempSon != null) {
                        if(tempSon.task == task){
                            if(tempSon == last){
                                last = tempFather;
                                count--;
                                return true;
                            }
                                tempFather.next = tempSon.next;
                                count--;
                                return true;
                        }
                        tempFather = tempSon;
                        tempSon = tempFather.next;
                }
                return false;
        }
    /**
     * Method returns size of list
     * */
        public int size() {
                return count;
        }
    /**
     * Method returns task by index
     * @param index - number in list of task.
     * */
        public Task getTask(int index) {
                Node temp = first;
                int counter = 0;
                while(temp != null) {
                        if(counter == index) {
                                return temp.task;
                        }
                        counter++;
                        temp = temp.next;
                }
                return null;
        }
  
    private class Iter implements Iterator<Task> {
        private Node node = new Node();
        private Node prev;
        boolean isNext = false;

        {
            node.next = first;
        }

        @Override
        public boolean hasNext() {
            return node.next != null;

        }

        @Override
        public Task next() {
            if(node == null){
                throw new NoSuchElementException("NoSuchElementException");
            }
            prev = node;
            node = node.next;
            isNext = true;
            return node.task;
        }

        @Override
        public void remove() {
            if(!isNext){
                throw new IllegalStateException("NoSuchElementException");
            }
            if(node == first){
              first = first.next;
              node.next = first;
              prev = null;
            }else{
                prev.next = node.next;
            }
            isNext = false;
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
    public LinkedTaskList clone() {
        LinkedTaskList obj = (LinkedTaskList)super.clone();           
            if(obj.first == null){
                return obj;
            }
            Node temp = obj.first.clone();
            while(last.next != null) {
                last.next = last.next.clone();
                last = last.next ;

            }
        return obj;
    }

}


