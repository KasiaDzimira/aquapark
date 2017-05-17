package Controller;

import Database.Connector;
import Model.Day;
import Model.TicketPriceList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketPriceListController {
    private Connector connector;

    public TicketPriceListController() {
        this.connector = new Connector();
    }

    public void createTicketPriceList(Date startDate, Date endDate) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO tckt_prc_lst (start_date, end_date) VALUES ('" +
                    startDateFormatted + "', '" + endDateFormatted + "')";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public List<TicketPriceList> getAllTicketPriceLists() {
        List<TicketPriceList> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM tckt_prc_lst";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                TicketPriceList ticketPriceList = new TicketPriceList(
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                ticketPriceList.setId(rs.getInt("id"));
                result.add(ticketPriceList);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return result;
    }

    public TicketPriceList getTicketPriceListById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM tckt_prc_lst WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                TicketPriceList ticketPriceList = new TicketPriceList(
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                ticketPriceList.setId(rs.getInt("id"));
                return ticketPriceList;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }

    public void updateTicketPriceList(int id, Date startDate, Date endDate) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE tckt_prc_lst SET start_date='" + startDateFormatted + "', end_date='" + endDateFormatted + "' WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public void deleteDay(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM tckt_prc_lst WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
