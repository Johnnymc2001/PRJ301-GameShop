/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.shoppa;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import johnny.registeration.RegisterationDTO;
import johnny.ultils.DBHelpers;

/**
 *
 * @author JohnnyMC
 */
public class ShoppaDAO implements Serializable {

    public Map<String, ShoppaDTO> gamesList;

    public Map<String, ShoppaDTO> getGamesList() {
        return gamesList;
    }

    public ShoppaDTO getGameInfoByName(String gameName) throws SQLException, NamingException {
        ShoppaDTO game = null;
        fetchAllGames();

        game = gamesList.get(gameName);
        return game;
    }

    public ArrayList<ShoppaDTO> getAvailableGames() throws NamingException, SQLException {
        ArrayList<ShoppaDTO> avail = new ArrayList<>();
        fetchAllGames();
        getGamesList().forEach((k, v) -> {
            if (v.getQuantity() > 0) {
                avail.add(new ShoppaDTO(v.getId(), v.getName(), v.getQuantity(), v.getPrice()));
            }
        });

        return avail;
    }

    public boolean removeNCopies(String gameName, int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE GamesList "
                        + "SET quantity = quantity - ? "
                        + "Where name = ? and quantity >= ? ";

                stm = con.prepareStatement(sql);

                stm.setInt(1, quantity);
                stm.setString(2, gameName);
                stm.setInt(3, quantity);

                int effectiveRow = stm.executeUpdate();

                if (effectiveRow > 0) {
                    return true;
                }
            } // End connection
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void fetchAllGames() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "Select id, name, quantity, price "
                        + "From GamesList ";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    double money = rs.getFloat("price");

                    ShoppaDTO dto = new ShoppaDTO(id, name, quantity, money);
                    if (this.gamesList == null) {
                        this.gamesList = new HashMap<String, ShoppaDTO>();
                    };
                    // Add account
                    this.gamesList.put(name, new ShoppaDTO(id, name, quantity, money));
                }
            } // End connection
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean checkoutGame(ShoppaDTO item) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "INSERT INTO GamesCheckout (name, quantity, price, total) "
                        + "VALUES (?, ?, ?, ?) ";

                stm = con.prepareStatement(sql);
                stm.setString(1, item.getName());
                stm.setInt(2, item.getQuantity());
                stm.setDouble(3, item.getPrice());
                stm.setDouble(4, item.getQuantity() * item.getPrice());

                int effectiveRow = stm.executeUpdate();

                if (effectiveRow > 0) {
                    return true;
                }
            } // End connection
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
