package Model;

/**
 * Represents a watch that is used to mark entries and exits into attraction zones
 */
public class Watch {

    /**
     * id of the wath
     */
    private int id;

    /**
     * status of the watch
     * 0 - damaged, won't be used
     * 1 - in use
     */
    private int status;

    /**
     * Constructor
     * @param status status of the new watch
     */
    public Watch(int status) {
        this.status = status;
    }

    /**
     * Getter for id
     * @return id of the watch
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id new id of the watch
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for status
     * @return status of the watch
     */
    public int getStatus() {
        return status;
    }

    /**
     * Setter for status
     * @param status new status of the watch
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Stringifies watch
     * @return an information about watch id and its status
     */
    @Override
    public String toString() {
        return "Watch: " + Integer.toString(id) + ", status: " + Integer.toString(status);
    }
}
