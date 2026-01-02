import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Step 1: Database Information (Change these to match YOUR database)
    private static final String URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "00000000";

    // Step 2: Method to connect to database
    public static Connection getConnection() {
        Connection connection = null;

        try {
            // Step 3: Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 4: Connect to database
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Step 5: Success message
            System.out.println("✓ Connected to database!");

        } catch (ClassNotFoundException e) {
            // Error: MySQL driver file not found
            System.out.println("✗ Error: MySQL driver not found! Add mysql-connector-java.jar");

        } catch (SQLException e) {
            // Error: Can't connect to database (wrong password, database doesn't exist, etc.)
            System.out.println("✗ Error: Cannot connect to database!");
            System.out.println("   Check: 1) MySQL is running  2) Password is correct  3) Database exists");
        }

        return connection;
    }
}