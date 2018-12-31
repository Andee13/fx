/**
 * Package describes model of application.
 * It is package where user data saved in appropriate data structure,
 * executed and gives an access to data for another classes of application.
 * * */
package sample.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
/**
 * Class describe inner structure of tsk and methods to work with.
 * */
public class Task implements Cloneable, Serializable {
    /**
     * Task name
     * */
        private String title;
    /**
     * Date when task starts
     * */
        private Date start;
    /**
     * Date when task ends.
     * */
        private Date end;
    /**
     * Interval of task.
     * */
        private int interval;
    /**
     * Activeness of task.
     * */
        private boolean active;
    /**
     * Constructor that creates unrepeated task
     * @param time - time when it's occur
     * @param title - name of task
     * */
        public Task(String title, Date time) {
            setTitle(title);
            setTime(time);
        }
    /**
     * Constructor that creates repeated task
     * @param title - name of task
     * @param start - time when it starts
     * @param end - when task ends
     * @param interval - time between task happening
     * */
        public Task(String title, Date start, Date end, int interval) {
            setTitle(title);
            setTime(start, end, interval);
        }
        /**
         * Getter for task name
         * @return title
         * */
        public String getTitle() {
            return title;
        }
    /**
     * Setter for name of task.
     * @param title- set title equals to this
     * */
        public void setTitle(String title) {
            this.title = title;
        }
        /**
     * Getter for activeness name
     * @return Active
     * */
        public boolean isActive() {
            return active;
        }
    /**
     * Setter for activeness of task.
     * @param active - set task equals to this
     * */
        public void setActive(boolean active) {
            this.active = active;
        }
    /**
     * Getter for  unrepeated task time
     * @return start
     * */
        public Date getTime() {
            return start;
        }
    /**
     * Setter for unrepeated task time
     * @param time - time when task happen
     * */
        public void setTime(Date time) {
            this.start = this.end = time;
            this.interval = 0;
        }
    /**
     * Getter for  repeated task time
     * @return title
     * */
        public Date getStartTime() {
            return start;
        }
        /**
     * Getter for  end task time
     * @return end
     * */
        public Date getEndTime() {
            return end;
        }
    /**
     * Getter for task interval
     * @return interval
     * */
        public int getRepeatInterval() {
            return interval;
        }
    /**
     * Setter for repeated task time
     * @param start - date when task starts
     * @param end  - date when task ends
     * @param  interval - interval with which task repeats.
     * */
        public void setTime(Date start, Date end, int interval) {
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
    /**
     * Return true if task repeated and false in other case
     * @return true or false
     * */
        public boolean isRepeated() {
            return interval != 0;
        }
    /**
     * Method returns next date of occurrence or null if task doesn't happens
     * @param current - date after what method seek date of task execution
     * @return Date - date when task will occur
     * */
        public Date nextTimeAfter(Date current) {
        if (isActive()) {
            if ( isRepeated() ){
                Date temp = new Date(start.getTime());
                while(temp.before(end) || temp.equals(end)){
                    if(current.before(temp)) {
                        return temp;
                    }
                    temp.setTime(temp.getTime() + (long)interval * 1000);
                }
            } else {
                if(current.before(start)) {
                    return start;
                }
            }
        }
        return null;
    }
/**
 * Checks if two tasks are equals
 * @return true if task equals another task ad false in other case.
 * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return interval == task.interval &&
                active == task.active &&
                Objects.equals(title, task.title) &&
                Objects.equals(start, task.start) &&
                Objects.equals(end, task.end);
    }
/**
 * Method returns hash of task
 * @return temp - task hash
 * */
    @Override
    public int hashCode() { 
        int temp = 37;
        temp += (start.hashCode() + end.hashCode() + interval + title.hashCode());
        return temp;
    }
    /**
     * Method clone task
     * @return Task - exact copy first task
     @exception InternalError - if task isn't cloneable
     * */
    public Task clone() {
        try{
            Task obj = (Task)super.clone();
            obj.start = (Date)start.clone();
            obj.end = (Date)end.clone();
            return obj;
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }

}
