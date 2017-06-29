package Model;

/**
 * Is a type of the pass
 */
public class PassType {

    /**
     * id of the type
     */
    private int id;

    /**
     * name of the type
     */
    private String name;

    /**
     * Constructor
     * @param name name of the new type
     */
    public PassType(String name) {
        this.name = name;
    }

    /**
     * Getter for id
     * @return id of the new type
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id new id for the type
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter for name
     * @param name new name of the type
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for name
     * @return name of the type
     */
    public String getName() {
        return name;
    }

    /**
     * Stringifies type
     * @return name of the type
     */
    @Override
    public String toString() {
        return name;
    }
}
