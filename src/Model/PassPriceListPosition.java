package Model;

import java.math.BigDecimal;

/**
 * Is a single position in pass price list
 */
public class PassPriceListPosition {

    /**
     * id of the position
     */
    private int id;

    /**
     * price for the position
     */
    private BigDecimal price;

    /**
     * price list to which the position is bound
     */
    private PassPriceList passPriceList;

    /**
     * group that is related to this position
     */
    private DiscountGroup discountGroup;

    /**
     * type of the pass that is related to this position
     */
    private PassType passType;

    /**
     * Constructor
     * @param price price for the new position
     * @param passPriceList price list for the new position
     * @param discountGroup group for the new position
     * @param passType type of the pass for the new position
     */
    public PassPriceListPosition(BigDecimal price, PassPriceList passPriceList, DiscountGroup discountGroup, PassType passType) {
        this.price = price;
        this.passPriceList = passPriceList;
        this.discountGroup = discountGroup;
        this.passType = passType;
    }

    /**
     * Setter for id
     * @param id new id for the position
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for id
     * @return id of the position
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for price
     * @return price for the position
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Getter for group
     * @return group for the position
     */
    public DiscountGroup getDiscountGroup() {
        return discountGroup;
    }

    /**
     * Setter for price
     * @param price new price for the position
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Setter for group
     * @param discountGroup new group for the position
     */
    public void setDiscountGroup(DiscountGroup discountGroup) {
        this.discountGroup = discountGroup;
    }

    /**
     * Setter for pass type
     * @param passType new pass type for the position
     */
    public void setPassType(PassType passType) {
        this.passType = passType;
    }

    /**
     * Getter for pass type
     * @return pass type for the position
     */
    public PassType getPassType() {
        return passType;
    }

    /**
     * Getter for price list
     * @return price list for the position
     */
    public PassPriceList getPassPriceList() {
        return passPriceList;
    }

    /**
     * Setter for price list
     * @param passPriceList new price list for the position
     */
    public void setPassPriceList(PassPriceList passPriceList) {
        this.passPriceList = passPriceList;
    }

    /**
     * Stringifies position
     * @return an information about type of the pass, group and price that are related to the position
     */
    @Override public String toString() {
        return passType.toString() + " " + discountGroup.toString() + " " + price.toString();
    }
}
