package Model;

import java.util.Date;

/**
 * Represents a ticket in the aquapark
 */
public class Ticket {

    /**
     * id of the ticket
     */
    private int id;

    /**
     * timestamp of entry
     */
    private Date stamp;

    /**
     * timestamp of exit
     */
    private Date stampOut;

    /**
     * watch used for the ticket
     */
    private Watch watch;

    /**
     * optional, pass that was used for the ticket
     */
    private Pass pass;

    /**
     * Constructor
     * @param stamp timestamp of entry of the new ticket
     * @param watch watch of the new ticket
     * @param pass pass of the new ticket
     */
    public Ticket(Date stamp, Watch watch, Pass pass) {
        this.stamp = stamp;
        this.watch = watch;
        this.pass = pass;
        this.stampOut = null;
    }

    /**
     * Constructor
     * @param stamp timestamp of entry of the new ticket
     * @param stampOut timestamp of exit of the new ticket
     * @param watch watch of the new ticket
     * @param pass pass of the new ticket
     */
    public Ticket(Date stamp, Date stampOut, Watch watch, Pass pass) {
        this.stamp = stamp;
        this.watch = watch;
        this.pass = pass;
        this.stampOut = stampOut;
    }

    /**
     * Constructor
     * @param watch watch of the new ticket
     */
    public Ticket(Watch watch) {
        this.watch = watch;
    }

    /**
     * Setter for id
     * @param id new id of the ticket
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for id
     * @return id of the ticket
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for entry stamp
     * @return entry stamp of the ticket
     */
    public Date getStamp() {
        return stamp;
    }

    /**
     * Getter for watch
     * @return watch of the ticket
     */
    public Watch getWatch() {
        return watch;
    }

    /**
     * Setter for watch
     * @param watch new watch of the ticket
     */
    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    /**
     * Setter for entry stamp
     * @param stamp new entry stamp of the ticket
     */
    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    /**
     * Getter for pass
     * @return pass of the ticket
     */
    public Pass getPass() {
        return pass;
    }

    /**
     * Setter for pass
     * @param pass new pass of the ticket
     */
    public void setPass(Pass pass) {
        this.pass = pass;
    }

    /**
     * Getter for exit stamp
     * @return exit stamp of the ticket
     */
    public Date getStampOut() {
        return stampOut;
    }

    /**
     * Setter for exit stamp
     * @param stampOut new exit stamp of the ticket
     */
    public void setStampOut(Date stampOut) {
        this.stampOut = stampOut;
    }

    /**
     * Stingifies ticket
     * @return an information about about the ticket: its id and watch id
     */
    @Override
    public String toString() {
        return "Ticket: " + Integer.toString(id) + ", watch: " + Integer.toString(watch.getId());
    }
}
