package Model;

/**
 * A class representing single attraction in the aquapark
 */
public class Attraction {

    /**
     * Id of the attraction
     */
    private int id;

    /**
     * Name of the attraction
     */
    private String name;

    /**
     * Status of the attraction
     * true - active
     * false - not active
     */
    private boolean status;

    /**
     * Type of the attraction
     */
    private AttractionType attractionType;

    /**
     * Constructor
     *
     * @param name name of the new attraction
     * @param status status of the new attraction
     * @param attractionType type of the new attraction
     */
    public Attraction(String name, boolean status, AttractionType attractionType) {
        this.name = name;
        this.status = status;
        this.attractionType =attractionType;
    }

    /**
     * Getter for id
     *
     * @return id of the attraction
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     *
     * @param id new id of the attraction
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for status
     *
     * @return status of the attraction
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Setter for status
     *
     * @param status new status of the attraction
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Getter for attraction type
     *
     * @return attraction type of the attraction
     */
    public AttractionType getAttractionType() {
        return attractionType;
    }

    /**
     * Setter for the attraction type
     *
     * @param attractionType new attraction type of the attraction
     */
    public void setAttractionType(AttractionType attractionType) {
        this.attractionType = attractionType;
    }

    /**
     * Getter for name
     *
     * @return name of the attraction
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name new name of the attraction
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Stringifies the attraction
     *
     * @return name of the attraction
     */
    @Override
    public String toString() {
        return name;
    }
}
