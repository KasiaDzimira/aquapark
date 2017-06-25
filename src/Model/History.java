package Model;

import java.util.Date;

/**
 * Represents an entry into attraction zone
 */
public class History {

    /**
     * Id of the history
     */
    private int id;

    /**
     * A time when a user enters the attraction zone
     */
    private Date entryTime;

    /**
     * A time when a user leaves the attraction zone
     */
    private Date exitTime;

    /**
     * An attraction that was in use for that entry
     */
    private Attraction attraction;

    /**
     * A watch that user had
     */
    private Watch watch;

    /**
     * Constructor
     *
     * @param entryTime entry time of the new history
     * @param exitTime exit time of the new history
     * @param attraction attraction of the new history
     * @param watch watch of the new history
     */
    public History(Date entryTime, Date exitTime, Attraction attraction, Watch watch) {
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.attraction = attraction;
        this.watch = watch;
    }

    /**
     * Getter for watch
     *
     * @return watch of the history
     */
    public Watch getWatch() {
        return watch;
    }

    /**
     * Setter for watch
     *
     * @param watch new watch of the history
     */
    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    /**
     * Getter for id
     *
     * @return id of the history
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     *
     * @param id new id of the history
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for exit time
     *
     * @return exit time of the history
     */
    public Date getExitTime() {
        return exitTime;
    }

    /**
     * Getter for entry time
     *
     * @return entry time of the history
     */
    public Date getEntryTime() {
        return entryTime;
    }

    /**
     * Setter for attraction
     *
     * @param attraction new attraction of the history
     */
    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    /**
     * Getter for attraction
     *
     * @return attraction of the history
     */
    public Attraction getAttraction() {
        return attraction;
    }

    /**
     * Setter for entry time
     *
     * @param entryTime new entry time of the history
     */
    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    /**
     * Setter for exit time
     *
     * @param exitTime new exit time of the history
     */
    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    /**
     * Stringifies history
     *
     * @return an information about history: its id, entry time, exit time, attraction and watch
     */
    @Override
    public String toString() {
        return "History: " + id + ", entry time: " + entryTime + ", exit time: " + exitTime + ", attraction: " +
                attraction + ", watch: " + watch;
    }
}
