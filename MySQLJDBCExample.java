import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLJDBCExample {
    // Update these values to match your MySQL configuration.
    private static final String JDBC_URL =
            "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        String sqlQuery = "SELECT id, username, email FROM users";

        // Try-with-resources automatically closes the connection,
        // statement, and result set.
        try (Connection connection =
                     DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");

                System.out.printf(
                        "User ID: %d, Username: %s, Email: %s%n",
                        id, username, email
                );
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
