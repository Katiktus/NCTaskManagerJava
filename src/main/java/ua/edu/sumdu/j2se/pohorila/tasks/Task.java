package ua.edu.sumdu.j2se.pohorila.tasks;

import java.util.Objects;

/**
 * Class Task.
 * @author Pohorila
 * @version 1.0
 *
 */

public class Task implements Cloneable{
    /** Name of task. */
    private String title;
    /** Time of task. */
    private int time;
    /** Time of start. */
    private int start;
    /** Time of end task. */
    private int end;
    /** Interval of execution. */
    private int interval;
    /** Active state. */
    private boolean active;
    /** Repeated state. */
    private boolean repeated;

    /**
     * Constructor for non repeated task.
     * @param title name of task
     * @param time time of execution
     */
    public Task(String title, int time){
        if(time < 0){
            throw new IllegalArgumentException("Time is negative");
        }
        else {
            this.time = time;

        }
        this.title = title;
        this.active = false;
        this.repeated = false;
    }

    /**
     * Constructor for repeated task.
     * @param title name of task
     * @param start time of start
     * @param end time of end
     * @param interval task interval
     */
     public Task(String title, int start, int end, int interval){
         if(start < 0 || end < 0 || interval < 0){
             throw new IllegalArgumentException("They negative");
         }else{
             this.start = start;
             this.end = end;
             this.interval = interval;
         }
         this.title = title;
         this.active = false;
         this.repeated = true;
     }

    /**
     * Getter for title.
     * @return name of task
     */
    public String getTitle(){
        return title;
    }

    /**
     * Setter for title.
     * @param title name of task
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Return active state.
     * @return active state
     */
    public boolean isActive(){
        return active;
    }

    /**
     * Setter for active state.
     * @param active set active state
     */
    public void setActive(boolean active){
        this.active = active;
    }

    /**
     * Getter for time.
     * @return time of task
     */
    public int getTime(){
        if(repeated){
            return start;
        }
        else{
            return time;
        }
    }

    /**
     * Setter for time.
     * @param time set time of task
     */
    public void setTime(int time){
        if(repeated){
            this.repeated = false;
        }
        if(time > this.end){
            this.interval = 0;
        }
        this.time = time;
    }

    /**
     * Getter for start time.
     * @return start time of task
     */
    public int getStartTime(){
        if(!repeated){
            return time;
        }
        else{
            return start;
        }
    }

    /**
     * Getter for end time.
     * @return end time of task
     */
    public int getEndTime(){
        if(!repeated){
            return time;
        }
        else{
            return end;
        }
    }

    /**
     * Getter for repeated interval.
     * @return repeated interval
     */
    public int getRepeatInterval(){
        return  interval;
    }

    /**
     * Setter fro time.
     * @param start time of start task
     * @param end time of end start
     * @param interval task interval
     */
    public void setTime(int start, int end, int interval){
        if(this.time > end){
            this.interval = 0;
        }
        this.interval = interval;
        this.end = end;
        this.start = start;
        this.repeated = true;
    }

    /**
     * Return repeated state.
     * @return repeated state
     */

    public boolean isRepeated(){
        return repeated;
    }

    /**
     * Return next time of execution after current.
     * @param current current time
     * @return next execution time after current
     */
    public int nextTimeAfter(int current){
        if(isRepeated() & isActive()){
            if(current < start){
                return start;
            }
            else if(current == start){
                return start + interval;
            }
            else if(current + interval <= end){
                return ((current - start)/interval)*(2*interval) + start;
            }
            else{
                return -1;
            }
        }
        else if(!isRepeated() && isActive()){
            if(current < time){
                return time;
            }
            else{
                return -1;
            }
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time == task.time &&
            start == task.start &&
            end == task.end &&
            interval == task.interval &&
            active == task.active &&
            repeated == task.repeated &&
            title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return time*start^end+interval;
    }

    @Override
    public String toString() {
        return "Task{" +
            "title='" + title + '\'' +
            ", time=" + time +
            ", start=" + start +
            ", end=" + end +
            ", interval=" + interval +
            ", active=" + active +
            ", repeated=" + repeated +
            '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
