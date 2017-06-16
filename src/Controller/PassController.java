package Controller;

import Database.Connector;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PassController {
    private Connector connector;

    public PassController() {
        this.connector = new Connector();
    }

    public void createPass(Date startDate, Date endDate, Watch watch, User user, PassType passType) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO pass (start_date, end_date, watch_id, aquapark_user_id, pass_type_id) VALUES ('" +
                    startDateFormatted + "', '" + endDateFormatted + "', " + watch.getId() + ", " +
                    user.getId() + ", " + passType.getId() + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public List<Pass> getAllPasses() {
        List<Pass> result = new ArrayList<>();
        WatchController watchController = new WatchController();
        UserController userController = new UserController();
        PassTypeController passTypeController = new PassTypeController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Pass pass = new Pass(
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        watchController.getWatchById(rs.getInt("watch_id")),
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
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        watchController.getWatchById(rs.getInt("watch_id")),
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

    public void updatePass(int id, Date startDate, Date endDate, Watch watch, User user, PassType passType) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE pass SET start_date='" +
                    startDateFormatted + "', end_date='" + endDateFormatted + "', watch_id=" + watch.getId() +
                    ", user_id=" + user.getId() + ", pass_type_id=" + passType.getId() + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

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

    public Pass findByWatch(Watch watch) {
        this.connector.connect();
        WatchController watchController = new WatchController();
        UserController userController = new UserController();
        PassTypeController passTypeController = new PassTypeController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass WHERE watch_id=" + watch.getId();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Pass pass = new Pass(
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        watchController.getWatchById(rs.getInt("watch_id")),
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
}
