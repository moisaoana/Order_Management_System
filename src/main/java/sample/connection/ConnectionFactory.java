package sample.connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class assures the connection to the database
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class ConnectionFactory {
    private static final Logger LOGGER =Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://localhost:3306/ordermanagement";
    private static final String USER="root";
    private static final String PASS="rootoana";
    private static ConnectionFactory singleInstance= new ConnectionFactory();
    private ConnectionFactory(){
    try{
        Class.forName(DRIVER);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    }
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error connecting to the database");
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * Method for getting an active connection
     * @return an object of type Connection
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }
    /**
     * This method closes the connection to the database
     * @param connection parameter of type Connection (an active connection)
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error trying to close the connection");
            }
        }
    }

    /**
     * This method closes an existing statement
     * @param statement parameter of type Statement (an existing statement)
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error trying to close the statement");
            }
        }
    }

    /**
     * This method closes the result set
     * @param resultSet an existing result set
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error trying to close the ResultSet");
            }
        }
    }
}



