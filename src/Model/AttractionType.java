package Model;

/**
 * A class representing attraction type in the aquapark
 */
public class AttractionType {

    /**
     * Id of the attraction type
     */
    private int id;

    /**
     * Name of the attraction type
     */
    private String name;

    /**
     * Constructor
     *
     * @param name name of the new attraction type
     */
    public AttractionType(String name) {
        this.name = name;
    }

    /**
     * Getter for id
     *
     * @return id of the attraction type
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     *
     * @param id new id of the attraction type
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for name
     *
     * @return name of the attraction type
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name new name of the attraction type
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Stringifies the attraction type
     *
     * @return name of the attraction type
     */
    @Override
    public String toString() {
        return name;
    }
}
