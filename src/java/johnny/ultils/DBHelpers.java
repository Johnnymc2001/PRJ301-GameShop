/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.ultils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBHelpers {

    public static Connection makeConnection() throws SQLException, NamingException {
        // 1. Get current Context
        Context context = new InitialContext();
        // 2. Get container Context
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        // 3. Get data source
        DataSource ds = (DataSource) tomcatContext.lookup("DSGood");
        // 4. Get connection
        Connection con = ds.getConnection();
        
        // 1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // 2. Create connection string
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Registeration";
        // 3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "123456789");

        return con;
    }
}
