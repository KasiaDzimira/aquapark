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
     */
    public void createPass(Date startDate, Date endDate, User user, PassType passType) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO pass (start_date, end_date, aquapark_user_id, pass_type_id) VALUES ('" +
                    startDateFormatted + "', '" + endDateFormatted + "', " + user.getId() + ", " +
                    passType.getId() + ")";
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
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id"))
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
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Pass pass = new Pass(
                        rs.getTimestamp("start_date"),
                        rs.getTimestamp("end_date"),
                        userController.findById(rs.getInt("aquapark_user_id")),
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id"))
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
     * Updates pass in the database
     * @param id id of the pass to update
     * @param startDate new starting date of the pass
     * @param endDate new ending date of the pass
     * @param user new user of the pass
     * @param passType new pass type of the pass
     */
    public void updatePass(int id, Date startDate, Date endDate, User user, PassType passType) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE pass SET start_date='" +
                    startDateFormatted + "', end_date='" + endDateFormatted + "', user_id=" + user.getId() +
                    ", pass_type_id=" + passType.getId() + " WHERE id=" + id;
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
     * @param pass pass object that must have fields startDate, endDate, passType correctly filled
     * @param group discount group of the client that is buying the pass
     * @return price of the pass
     */
    public BigDecimal calculatePrice(Pass pass, DiscountGroup group) {
        PassPriceListPositionController passPriceListPositionController = new PassPriceListPositionController();
        PassPriceListController passPriceListController = new PassPriceListController();
        Date now = new Date();
        PassPriceList currentPriceList = passPriceListController.getPassPriceListForDay(now);
        long timeDiff = TimeUtilities.getDateDiff(pass.getStartDate(), pass.getEndDate(), TimeUnit.HOURS);
        long numberOfDays = (timeDiff % 24) + 1;
        return passPriceListPositionController.getPrice(currentPriceList, group, pass.getPassType()).multiply(new BigDecimal(numberOfDays));
    }
}
