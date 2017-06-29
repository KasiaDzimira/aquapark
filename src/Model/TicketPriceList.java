package Model;

import java.util.Date;

/**
 * Represents price list for tickets for a certain period with all its positions
 */
public class TicketPriceList {

    /**
     * id of the price list
     */
    private int id;

    /**
     * a date when the price list starts to be valid
     */
    private Date startDate;

    /**
     * a last day when price list is valid
     */
    private Date endDate;

    /**
     * Constructor
     * @param startDate start date of the new price list
     * @param endDate end date of the new price list
     */
    public TicketPriceList(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Getter for id
     * @return id of the price list
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id new id for the price list
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for end date
     * @return end date of the price list
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Getter for start date
     * @return end start of the price list
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Setter for end date
     * @param endDate new end date of the price list
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Setter for start date
     * @param startDate new start date of the price list
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
