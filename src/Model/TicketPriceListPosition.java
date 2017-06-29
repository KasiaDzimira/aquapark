package Model;

import java.math.BigDecimal;

/**
 * Is a single position in ticket price list
 */
public class TicketPriceListPosition {

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
    private TicketPriceList ticketPriceList;

    /**
     * day of the week that is related to this position
     */
    private Day day;

    /**
     * group that is related to this position
     */
    private DiscountGroup discountGroup;

    /**
     * daytime (for example evening) that is related to this position
     */
    private Daytime daytime;

    /**
     * type of the attraciton that is related to this position
     */
    private AttractionType attractionType;

    /**
     * Constructor
     * @param price price for the new position
     * @param ticketPriceList price list for the new position
     * @param day day for the new position
     * @param discountGroup group for the new position
     * @param daytime daytime for the new position
     * @param attractionType type of the attraction for the new position
     */
    public TicketPriceListPosition(BigDecimal price, TicketPriceList ticketPriceList, Day day, DiscountGroup discountGroup, Daytime daytime, AttractionType attractionType) {
        this.price = price;
        this.ticketPriceList = ticketPriceList;
        this.day = day;
        this.discountGroup = discountGroup;
        this.daytime = daytime;
        this.attractionType = attractionType;
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
     * Getter for attraction type
     * @return attraction type for the position
     */
    public AttractionType getAttractionType() {
        return attractionType;
    }

    /**
     * Getter for price
     * @return price for the position
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Getter for day
     * @return day for the position
     */
    public Day getDay() {
        return day;
    }

    /**
     * Getter for group
     * @return group for the position
     */
    public DiscountGroup getDiscountGroup() {
        return discountGroup;
    }

    /**
     * Setter for attraction type
     * @param attractionType new attraction type for the position
     */
    public void setAttractionType(AttractionType attractionType) {
        this.attractionType = attractionType;
    }

    /**
     * Getter for price list
     * @return price list for the position
     */
    public TicketPriceList getTicketPriceList() {
        return ticketPriceList;
    }

    /**
     * Getter for daytime
     * @return daytime for the position
     */
    public Daytime getDaytime() {
        return daytime;
    }

    /**
     * Setter for day
     * @param day new day for the position
     */
    public void setDay(Day day) {
        this.day = day;
    }

    /**
     * Setter for daytime
     * @param daytime new daytime for the position
     */
    public void setDaytime(Daytime daytime) {
        this.daytime = daytime;
    }

    /**
     * Setter for price
     * @param price new price for the position
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Setter for price list
     * @param ticketPriceList new price list for the position
     */
    public void setTicketPriceList(TicketPriceList ticketPriceList) {
        this.ticketPriceList = ticketPriceList;
    }

    /**
     * Setter for group
     * @param discountGroup new group for the position
     */
    public void setDiscountGroup(DiscountGroup discountGroup) {
        this.discountGroup = discountGroup;
    }

    /**
     * Stringifies position
     * @return an information about the day, group, daytime, type of the attraction and price that are related to the position
     */
    @Override public String toString() {
        return day.toString() + " " + discountGroup.toString() + " " + daytime.toString() + " " + attractionType.toString() + " " + price.toString();
    }
}
