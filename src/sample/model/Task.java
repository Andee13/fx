package sample.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Task implements Cloneable, Serializable {
        private String title;
        private Date start;
        private Date end;
        private int interval;
        private boolean active;

        public Task(String title, Date time) {
            setTitle(title);
            setTime(time);
        }
        public Task(String title, Date start, Date end, int interval) {
            setTitle(title);
            setTime(start, end, interval);
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public boolean isActive() {
            return active;
        }
        public void setActive(boolean active) {
            this.active = active;
        }
        public Date getTime() {
            return start;
        }
        public void setTime(Date time) {
            this.start = this.end = time;
            this.interval = 0;
        }
        public Date getStartTime() {
            return start;
        }
        public Date getEndTime() {
            return end;
        }
        public int getRepeatInterval() {
            return interval;
        }
        public void setTime(Date start, Date end, int interval) {
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
        public boolean isRepeated() {
            return interval == 0 ? false : true;
        }
        
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

    @Override
    public int hashCode() { 
        int temp = 37;
        temp += (start.hashCode() + end.hashCode() + interval + title.hashCode());
        return temp;
    }
    
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
// Additional code

//    public Date getStart() {
//        return start;
//    }
//
//    public void setStart(Date start) {
//        this.start = start;
//    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
