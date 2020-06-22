package MainPackage.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scbisa2",
                "root","");

        return connection;
    }
}
