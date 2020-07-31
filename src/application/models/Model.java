package application.models;

import java.sql.*;

public abstract class Model {

    protected static Connection connection;
    protected static Connection localConnection;

    public abstract boolean insert() throws SQLException;
    public abstract void update();
    public abstract void delete();

    public static void init() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://itradionica.com:3306/stefan?serverTimezone=UTC", "stefan", "VjEQ3mpOOZmJyTwm");
        try {
            localConnection = DriverManager.getConnection("jdbc:mysql://localhost:3308/marija2007xx", "root", "");
        }
        catch (SQLException e) {
            localConnection = null;
        }
    }

}
