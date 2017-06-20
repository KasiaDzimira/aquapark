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

public class PassPriceListController {
    private Connector connector;

    public PassPriceListController() {
        this.connector = new Connector();
    }

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
