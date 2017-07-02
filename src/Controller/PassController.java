package Controller;

import Database.Connector;
import Model.*;
import Utils.TimeUtilities;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Handles database related actions with Attraction class
 */
public class PassController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public PassController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new pass in the database
     * @param startDate name of the new pass
     * @param endDate status of the new pass
     * @param user user of the new pass
     * @param passType type of the pass of the new pass
     * @param discountGroup is discount group for creating pass
     */
    public void createPass(Date startDate, Date endDate, User user, PassType passType, DiscountGroup discountGroup) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO pass (start_date, end_date, aquapark_user_id, pass_type_id, disc_group_id) VALUES ('" +
                    startDateFormatted + "', '" + endDateFormatted + "', " + user.getId() + ", " +
                    passType.getId() + ", " + discountGroup.getId() + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all passes in the database
     * @return list of all passes in the database
     */
    public List<Pass> getAllPasses() {
        List<Pass> result = new ArrayList<>();
        UserController userController = new UserController();
        PassTypeController passTypeController = new PassTypeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Pass pass = new Pass(
                        rs.getTimestamp("start_date"),
                        rs.getTimestamp("end_date"),
                        userController.findById(rs.getInt("aquapark_user_id")),
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id"))
                );
                pass.setId(rs.getInt("id"));
                result.add(pass);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
        return result;
    }

    /**
     * Looks for the pass with given id
     * @param id id of the pass
     * @return desired Pass object or null if the pass couldn't be found
     */
    public Pass getPassById(int id) {
        this.connector.connect();
        WatchController watchController = new WatchController();
        UserController userController = new UserController();
        PassTypeController passTypeController = new PassTypeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Pass pass = new Pass(
                        rs.getTimestamp("start_date"),
                        rs.getTimestamp("end_date"),
                        userController.findById(rs.getInt("aquapark_user_id")),
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id"))
                );
                pass.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return pass;
            } else {
                this.connector.closeConnection(null);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }

    /**
     * Gets all passes for selected user
     * @param id is identifier of selected user
     * @return list of passes
     */
    public List<Pass> findPassesForUser(int id) {
        this.connector.connect();
        WatchController watchController = new WatchController();
        UserController userController = new UserController();
        PassTypeController passTypeController = new PassTypeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        ArrayList<Pass> passes = new ArrayList<Pass>();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass WHERE aquapark_user_id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (!rs.next()) {
                return null;
            }

            do {
                Pass pass = new Pass(
                        rs.getTimestamp("start_date"),
                        rs.getTimestamp("end_date"),
                        userController.findById(rs.getInt("aquapark_user_id")),
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id"))
                );
                pass.setId(rs.getInt("id"));
                passes.add(pass);
            } while (rs.next());

            this.connector.closeConnection(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passes;
    }

    /**
     * Updates pass in the database
     * @param id id of the pass to update
     * @param startDate new starting date of the pass
     * @param endDate new ending date of the pass
     * @param user new user of the pass
     * @param passType new pass type of the pass
     * @param discountGroup is new discount group of the pass
     */
    public void updatePass(int id, Date startDate, Date endDate, User user, PassType passType, DiscountGroup discountGroup) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE pass SET start_date='" +
                    startDateFormatted + "', end_date='" + endDateFormatted + "', aquapark_user_id=" + user.getId() +
                    ", pass_type_id=" + passType.getId() + ", disc_group_id=" + discountGroup.getId() + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes pass from the database
     * @param id id of the pass to be removed
     */
    public void deletePass(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM pass WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Calculates the price of the pass
     * @param passType pass type object that must have field name
     * @param startDate date of buying pass
     * @param endDate period of pass validity
     * @param group discount group of the client that is buying the pass
     * @param isLessDay if is true we don't adding 1 to numberOfeDays
     * @return price of the pass
     */
    public BigDecimal calculatePrice(PassType passType, Date startDate, Date endDate, DiscountGroup group, Boolean isLessDay) {
        PassPriceListPositionController passPriceListPositionController = new PassPriceListPositionController();
        PassPriceListController passPriceListController = new PassPriceListController();
        Date now = new Date();
        PassPriceList currentPriceList = passPriceListController.getPassPriceListForDay(now);
        long timeDiff = TimeUtilities.getDateDiff(startDate, endDate, TimeUnit.DAYS);
        long numberOfDays;

        if (!isLessDay) {
            numberOfDays = timeDiff + 1;
        } else {
            numberOfDays = timeDiff;
        }

        return passPriceListPositionController.getPrice(currentPriceList, group, passType).multiply(new BigDecimal(numberOfDays));
    }
}
