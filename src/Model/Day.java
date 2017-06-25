package Model;

/**
 * A day in the week
 */
public class Day {

    /**
     * Id of the day
     */
    private int id;

    /**
     * Name of the day
     */
    private String name;

    /**
     * Constructor
     *
     * @param name new name of the day
     */
    public Day(String name) {
        this.name = name;
    }

    /**
     * Setter for id
     *
     * @param id new id of the day
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter for name
     *
     * @param name new name of the day
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for name
     *
     * @return name of the day
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for id
     *
     * @return id of the day
     */
    public int getId() {
        return id;
    }

    /**
     * Stringifies the day
     *
     * @return name of the day
     */
    @Override
    public String toString() {
        return name;
    }
}
