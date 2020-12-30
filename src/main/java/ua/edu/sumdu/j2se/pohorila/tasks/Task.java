package ua.edu.sumdu.j2se.pohorila.tasks;

import java.time.LocalDateTime;
import static java.util.Objects.hash;

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
    private LocalDateTime time;
    /** Time of start. */
    private LocalDateTime start;
    /** Time of end task. */
    private LocalDateTime end;
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
    public Task(String title, LocalDateTime time){
        if(time == null){
            throw new IllegalArgumentException("Invalid argument");
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
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval){
        if(start == null || end == null || interval < 0){
            throw new IllegalArgumentException("Invalid argument");
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
    public LocalDateTime getTime(){
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
    public void setTime(LocalDateTime time){
        if(repeated){
            this.repeated = false;
        }
        if(time.isAfter(this.end)){
            this.interval = 0;
        }
        this.time = time;
    }

    /**
     * Getter for start time.
     * @return start time of task
     */
    public LocalDateTime getStartTime(){
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
    public LocalDateTime getEndTime(){
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
    public void setTime(LocalDateTime start, LocalDateTime end, int interval){
        if(this.time.isAfter(end)){
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
    public LocalDateTime nextTimeAfter(LocalDateTime current){
        if (isRepeated() && isActive()) {
            LocalDateTime next = start;
            if (current.isBefore(start)) {
                return next;
            } else if (current.isAfter(end)) {
                return null;
            } else {
                while (next.isBefore(end) || next.isEqual(end)) {
                    if (next.isAfter(current)) {
                        return next;
                    }
                    next = next.plusSeconds(interval);
                }
            }
        }
        else if (!isRepeated() && isActive()) {
            if (current.isBefore(time)) {
                return time;
            }
            return null;
        }
        return null;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return
            // time == task.time &&
           // start == task.start &&
           // end == task.end &&
            interval == task.interval &&
            active == task.active &&
            repeated == task.repeated &&
            title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return hash(interval, title);
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
