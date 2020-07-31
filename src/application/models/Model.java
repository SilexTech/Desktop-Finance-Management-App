package application.models;

import java.sql.*;

public abstract class Model {

    protected static Connection connection = null;

    public abstract boolean insert() throws SQLException;
    public abstract void update();
    public abstract void delete();

    public static void init() throws SQLException {
        if (connection == null)
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/marija2007xx", "root", "");
    }

}
