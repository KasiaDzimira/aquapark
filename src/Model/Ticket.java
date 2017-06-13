package Model;

import java.util.Date;

public class Ticket {
    private int id;
    private Date stamp;
    private Watch watch;

    public Ticket(Date stamp, Watch watch) {
        this.stamp = stamp;
        this.watch = watch;
    }

    public Ticket(Watch watch) {
        this.watch = watch;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getStamp() {
        return stamp;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    @Override
    public String toString() {
        return "Ticket: " + Integer.toString(id) + ", watch: " + Integer.toString(watch.getId());
    }
}
