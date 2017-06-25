package Model;

/**
 * A group of people that will get a certain discount
 */
public class DiscountGroup {

    /**
     * Id of the group
     */
    private int id;

    /**
     * Name of the group
     */
    private String name;

    /**
     * Discount for the group
     * Has rather informative function, isn't taken into account during price list filling
     */
    private float discount;

    /**
     * Constructor
     *
     * @param name new name of the group
     * @param discount new discount for the group
     */
    public DiscountGroup(String name, float discount) {
        this.name = name;
        this.discount = discount;
    }

    /**
     * Setter for id
     *
     * @param id new id of the group
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter for name
     *
     * @param name new name of the group
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for name
     *
     * @return name of the group
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for id
     *
     * @return id of the group
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for discount
     *
     * @return discount for the group
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * Setter for discount
     *
     * @param discount new discount for the group
     */
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    /**
     * Stringifies group
     *
     * @return name of the group
     */
    @Override
    public String toString() {
        return name;
    }
}
