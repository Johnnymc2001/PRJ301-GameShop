package johnny.registeration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;
import johnny.ultils.DBHelpers;

public class RegisterationDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "Select username "
                        + "From Accounts "
                        + "Where username = ? And password = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                // 4. Execute Query
                rs = stm.executeQuery();
                // 5. Process Result (Nhieu dong tra ve while, it dong tra ve while)
                if (rs.next()) {
                    return true;

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
        return false;
    }

    private List<RegisterationDTO> accountList;

    public List<RegisterationDTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "Select username, password, name, isAdmin "
                        + "From Accounts "
                        + "Where name Like ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                // 4. Execute Query
                rs = stm.executeQuery();
                // 5. Process Result (Nhieu dong tra ve while, it dong tra ve while)
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String name = rs.getString("name");
                    boolean isAdmin = rs.getBoolean("isAdmin");

                    RegisterationDTO dto = new RegisterationDTO(username, password, name, isAdmin);
                    // Check if accountList is null, if null create object
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    };
                    // Add account
                    this.accountList.add(dto);
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

    public String getNameUsingUsername(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "Select username, name "
                        + "From Accounts "
                        + "Where username Like ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                // 4. Execute Query
                rs = stm.executeQuery();
                // 5. Process Result (Nhieu dong tra ve while, it dong tra ve while)
                while (rs.next()) {
                    String username = rs.getString("username");
                    name = rs.getString("name");

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
            return name;
        }
    }

    public boolean deleteAccount(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "DELETE FROM Accounts "
                        + "WHERE username = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                // 4. Execute Query
                int effectiveRow = stm.executeUpdate();
                // 5. Process Result (Nhieu dong tra ve while, it dong tra ve while)
                if (effectiveRow > 0) {
                    return true;
                }
            } // End connection
        } finally {

        }
        return false;
    }

    public boolean updateAccount(String username, String password, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Accounts "
                        + "SET username = ?, password = ?, isAdmin = ? "
                        + "WHERE username = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setBoolean(3, isAdmin);

                stm.setString(4, username);
                // 4. Execute Query
                int effectiveRow = stm.executeUpdate();
                // 5. Process Result (Nhieu dong tra ve while, it dong tra ve while)
                if (effectiveRow > 0) {
                    return true;
                }
            } // End connection
        } finally {

        }
        return false;
    }

    public boolean createNewAccount(String username, String password,
            String name, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "INSERT INTO Accounts (username, password, name, isAdmin) "
                        + "VALUES (?, ?, ?, ?)";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, name);
                stm.setBoolean(4, isAdmin);
                // 4. Execute Query
                int effectiveRow = stm.executeUpdate();
                // 5. Process Result (Nhieu dong tra ve while, it dong tra ve while)
                if (effectiveRow > 0) {
                    return true;
                }
            } // End connection
        } finally {

        }
        return false;
    }
}
