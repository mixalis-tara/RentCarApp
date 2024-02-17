package rentCarApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

	String database = "jdbc:mysql://localhost/dbrentcarapp";
	String username = "root";
	String password = "";

	// Open the connection
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(database, username, password);
	}

    // Close the connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	public static void closeResultSet(ResultSet resultSet) {
		if(resultSet != null ) {
			try{
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void closeStatement(PreparedStatement statement) {
		if(statement != null ) {
			try{
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}

