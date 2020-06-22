package MainPackage.Helper;

import java.sql.*;

public class DBLogin {
    public static Connection GetDatabaseConnection() {
        Connection connection = null;
        String dbUrl = "jdbc:mysql://localhost:3306/scbisa2";
        String user = "root";
        String pass = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, user, pass);
        } catch (ClassNotFoundException e) {
            e.getLocalizedMessage();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getLocalizedMessage();
        }

        return connection;
    }

    public static boolean CheckLoginUser(String uname, String pass) {
        Connection connection = GetDatabaseConnection();
        String checkQuery = "select *from guru where id = ? and password = ? ";

        PreparedStatement preparedStatement = null;
        boolean status = false; //initially false

        try {
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
            preparedStatement.close();
            return status;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
