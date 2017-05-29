package Controller;

import Database.Connector;
import Model.Day;
import Model.Daytime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaytimeController {
    private Connector connector;

    public DaytimeController() {
        this.connector = new Connector();
    }

    public void createDaytime(String name, Date startHour, Date endHour) {
        this.connector.connect();
        try {
            String startHourFormatted = new SimpleDateFormat("HH:mm:ss").format(startHour);
            String endHourFormatted = new SimpleDateFormat("HH:mm:ss").format(endHour);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO daytime (name, start_hour, end_hour) VALUES ('" +
                    name + "', '" + startHourFormatted + "', '" + endHourFormatted + "')";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public List<Daytime> getAllDaytimes() {
        List<Daytime> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM daytime";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Daytime daytime = new Daytime(
                        rs.getString("name"),
                        new Date(rs.getTime("start_hour").getTime()),
                        new Date(rs.getTime("end_hour").getTime())
                );
                daytime.setId(rs.getInt("id"));
                result.add(daytime);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return result;
    }

    public Daytime getDaytimeById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM daytime WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Daytime daytime = new Daytime(
                        rs.getString("name"),
                        rs.getTime("start_hour"),
                        rs.getTime("end_hour")
                );
                daytime.setId(rs.getInt("id"));
                return daytime;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }

    public void updateDaytime(int id, String name, Date startHour, Date endHour) {
        this.connector.connect();
        try {
            String startHourFormatted = new SimpleDateFormat("HH:mm:ss").format(startHour);
            String endHourFormatted = new SimpleDateFormat("HH:mm:ss").format(endHour);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE daytime SET name='" + name + "', start_hour='" + startHourFormatted + "', end_hour='" + endHourFormatted + "' WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public void deleteDaytime(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM daytime WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
