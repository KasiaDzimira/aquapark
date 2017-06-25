package Model;

import java.util.Date;

/**
 * Represents daytime (for example morning, evening)
 */
public class Daytime {

    /**
     * Id of the daytime
     */
    private int id;

    /**
     * Name of the daytime
     */
    private String name;

    /**
     * An hour that is a starting hour of the daytime
     */
    private Date startHour;

    /**
     * An hour that is an ending hour of the daytime
     */
    private Date endHour;

    /**
     * Constructor
     *
     * @param name new name of the daytime
     * @param startHour new starting hour of the daytime
     * @param endHour new ending hour of the daytime
     */
    public Daytime(String name, Date startHour, Date endHour) {
        this.name = name;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    /**
     * Getter for id
     *
     * @return id of the daytime
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for name
     *
     * @return name of the daytime
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name new name of the daytime
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for id
     *
     * @param id new id of the daytime
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for ending hour
     *
     * @return ending hour of the daytime
     */
    public Date getEndHour() {
        return endHour;
    }

    /**
     * Getter for starting hour
     *
     * @return starting hour of the daytime
     */
    public Date getStartHour() {
        return startHour;
    }

    /**
     * Setter for ending hour
     *
     * @param endHour new ending hour of the daytime
     */
    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }

    /**
     * Setter for starting hour
     *
     * @param startHour new starting hour of the daytime
     */
    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }

    /**
     * Stringifies daytime
     *
     * @return name of the daytime
     */
    @Override
    public String toString() {
        return name;
    }
}
