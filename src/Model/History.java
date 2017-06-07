package Model;

import java.util.Date;

public class History {
    private int id;
    private Date entryTime;
    private Date exitTime;
    private Attraction attraction;
    private Watch watch;

    public History(Date entryTime, Date exitTime, Attraction attraction, Watch watch) {
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.attraction = attraction;
        this.watch = watch;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    @Override
    public String toString() {
        return "History: " + id + ", entry time: " + entryTime + ", exit time: " + exitTime + ", attraction: " +
                attraction + ", watch: " + watch;
    }
}
