package Controller;

import Database.Connector;
import Model.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassPriceListPositionController {
    private Connector connector;

    public PassPriceListPositionController() {
        this.connector = new Connector();
    }

    public void createPassPriceListPosition(BigDecimal price, int passPriceListId, int discountGroupId, int passTypeId, int attractionTypeId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO pass_prc_lst_pos (price, pass_prc_lst_id, disc_group_id, pass_type_id, attraction_type_id) VALUES (" +
                    price + "," + passPriceListId + "," + discountGroupId + "," + passTypeId + "," + attractionTypeId + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public List<PassPriceListPosition> getAllPassPriceListPositions() {
        List<PassPriceListPosition> result = new ArrayList<>();
        AttractionTypeController attractionTypeController = new AttractionTypeController();
        PassTypeController passTypeController = new PassTypeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        PassPriceListController passPriceListController = new PassPriceListController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass_prc_lst_pos";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                PassPriceListPosition position = new PassPriceListPosition(
                        new BigDecimal(rs.getString("price")),
                        passPriceListController.getPassPriceListById(rs.getInt("pass_prc_lst_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id")),
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id")),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
                );
                position.setId(rs.getInt("id"));
                result.add(position);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
        return result;
    }

    public List<PassPriceListPosition> getAllPassPriceListPositionsByPassPriceList(int passPriceListId) {
        List<PassPriceListPosition> result = new ArrayList<>();
        AttractionTypeController attractionTypeController = new AttractionTypeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        PassTypeController passTypeController = new PassTypeController();
        PassPriceListController passPriceListController = new PassPriceListController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass_prc_lst_pos WHERE pass_prc_lst_id=" + passPriceListId;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                PassPriceListPosition position = new PassPriceListPosition(
                        new BigDecimal(rs.getString("price")),
                        passPriceListController.getPassPriceListById(rs.getInt("pass_prc_lst_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id")),
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id")),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
                );
                position.setId(rs.getInt("id"));
                result.add(position);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
        return result;
    }

    public PassPriceListPosition getPassPriceListPositionById(int id) {
        this.connector.connect();
        AttractionTypeController attractionTypeController = new AttractionTypeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        PassTypeController passTypeController = new PassTypeController();
        PassPriceListController passPriceListController = new PassPriceListController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM tckt_prc_lst_pos WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                PassPriceListPosition position = new PassPriceListPosition(
                        new BigDecimal(rs.getString("price")),
                        passPriceListController.getPassPriceListById(rs.getInt("pass_prc_lst_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id")),
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id")),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
                );
                position.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return position;
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

    public BigDecimal getPrice(PassPriceList priceList, DiscountGroup group, PassType passType, AttractionType attractionType) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT price FROM pass_prc_lst_pos WHERE pass_prc_lst_id=" + priceList.getId() +
                    " AND disc_group_id=" + group.getId() + " AND pass_type_id=" +
                    passType.getId() + " AND attraction_type_id="
                    + attractionType.getId();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                BigDecimal price = new BigDecimal(rs.getString("price"));
                this.connector.closeConnection(rs);
                return price;
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

    public void updatePassPriceListPosition(int id, BigDecimal price) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE pass_prc_lst_pos SET price=" +
                    price + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public void updatePassPriceListPosition(int id, int passPriceListId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE pass_prc_lst_pos SET pass_prc_lst_id=" +
                    passPriceListId + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public void deletePassPriceListPosition(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM pass_prc_lst_pos WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
