package Controller;

import Database.Connector;
import Model.PassPriceList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Handles database related actions with PassPriceList class
 */
public class PassPriceListController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public PassPriceListController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new price list in the database
     * @param startDate starting date of the new price list
     * @param endDate ending date of the new price list
     */
    public void createPassPriceList(Date startDate, Date endDate) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO pass_prc_lst (start_date, end_date) VALUES ('" +
                    startDateFormatted + "', '" + endDateFormatted + "')";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all price lists in the database
     * @return list of all price lists in the database
     */
    public List<PassPriceList> getAllPassPriceLists() {
        List<PassPriceList> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass_prc_lst";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                PassPriceList passPriceList = new PassPriceList(
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                passPriceList.setId(rs.getInt("id"));
                result.add(passPriceList);
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
     * Looks for the price list with given id
     * @param id id of the price list
     * @return desired PassPriceList object or null if the price list couldn't be found
     */
    public PassPriceList getPassPriceListById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass_prc_lst WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                PassPriceList passPriceList = new PassPriceList(
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                passPriceList.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return passPriceList;
            } else {
                this.connector.closeConnection(rs);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
        return null;
    }

    /**
     * Looks for the price list with given day
     * @param day day for which the price list is valid
     * @return desired PassPriceList object or null if the price list couldn't be found
     */
    public PassPriceList getPassPriceListForDay(Date day) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String dayFormatted = new SimpleDateFormat("yyyy-MM-dd").format(day);
            String sql = "SELECT * FROM pass_prc_lst WHERE start_date <='" + dayFormatted + "'::date AND end_date >='" +
                    dayFormatted + "'::date";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                PassPriceList passPriceList = new PassPriceList(
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                passPriceList.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return passPriceList;
            } else {
                this.connector.closeConnection(rs);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
        return null;
    }

    /**
     * Updates price list in the database
     * @param id id of the price list to update
     * @param startDate new starting date of the price list
     * @param endDate new ending date of the price list
     */
    public void updatePassPriceList(int id, Date startDate, Date endDate) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE pass_prc_lst SET start_date='" + startDateFormatted + "', end_date='" + endDateFormatted + "' WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes price list from the database
     * @param id id of the price list to be removed
     */
    public void deletePassPriceList(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM pass_prc_lst WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
