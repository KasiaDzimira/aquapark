package Model;

import java.util.Date;

/**
 * Represent a pass in the aquapark
 */
public class Pass {

    /**
     * id of the pass
     */
    private int id;

    /**
     * a date when the pass starts to be valid
     */
    private Date startDate;

    /**
     * a last day when pass is valid
     */
    private Date endDate;

    /**
     * user that is an owner of the pass
     */
    private User user;

    /**
     * type of the pass
     */
    private PassType passType;

    /**
     * Constructor
     * @param startDate start date of the new pass
     * @param endDate end date of the new pass
     * @param user owner of the new pass
     * @param passType type of the new pass
     */
    public Pass(Date startDate, Date endDate, User user, PassType passType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.passType = passType;
    }

    /**
     * Setter for id
     * @param id new id for the pass
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for id
     * @return id of the pass
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for start date
     * @param startDate new start date of the pass
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Setter for end date
     * @param endDate new end date of the pass
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for start date
     * @return start date of the pass
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Getter for user
     * @return user that is the owner of the pass
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for user
     * @param user new user for the pass
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter for end date
     * @return end date of the pass
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Getter for pass type
     * @return type of the pass
     */
    public PassType getPassType() {
        return passType;
    }

    /**
     * Setter for pass type
     * @param passType new type of the pass
     */
    public void setPassType(PassType passType) {
        this.passType = passType;
    }

    /**
     * Stringifies pass
     * @return an information about the pass: its id, user and type
     */
    @Override
    public String toString() {
        return "Pass: " + Integer.toString(id) + ", user: " + user + ", type: " + passType;
    }
}
