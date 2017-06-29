package Model;

import java.util.Date;

public class Ticket {
    private int id;
    private Date stamp;
    private Date stampOut;
    private Watch watch;
    private Pass pass;

    public Ticket(Date stamp, Watch watch, Pass pass) {
        this.stamp = stamp;
        this.watch = watch;
        this.pass = pass;
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

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass pass) {
        this.pass = pass;
    }

    public Date getStampOut() {
        return stampOut;
    }

    public void setStampOut(Date stampOut) {
        this.stampOut = stampOut;
    }

    @Override
    public String toString() {
        return "Ticket: " + Integer.toString(id) + ", watch: " + Integer.toString(watch.getId());
    }
}
