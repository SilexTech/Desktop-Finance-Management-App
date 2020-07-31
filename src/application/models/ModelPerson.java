package application.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelPerson extends Model {

    public enum Type { NATURAL, LEGAL, NATURAL_SUPPLIER, NATURAL_CLIENT, LEGAL_SUPPLIER, LEGAL_CLIENT };

    private static ObservableList<ModelPerson> persons = FXCollections.observableArrayList();

    private final int personID;
    private String name;
    private ModelPerson.Type type;

    public ModelPerson(int personID, String name, Type type) {
        this.personID = personID;
        this.name = name;
        this.type = type;
        //persons.add(this);
    }

    public static ObservableList<ModelPerson> getPersons() {
        return persons;
    }

    public int getPersonID() {
        return personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private static ModelPerson parseResultSet(ResultSet resultSet) throws SQLException {
        int personID = resultSet.getInt(1);
        String name = resultSet.getString(2);
        Type type = ModelPerson.Type.valueOf(resultSet.getString(3));
        return new ModelPerson(personID, name, type);
    }

    public static void init() throws SQLException {
        Model.init(); // !important
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM persons");
        while (resultSet.next()) {
            persons.add(parseResultSet(resultSet));
        }
    }

    public static ModelPerson getByID(int ID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persons WHERE personID = ?");
        preparedStatement.setInt(1, ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        ModelPerson person = null;
        if (resultSet.next()) {
            person = parseResultSet(resultSet);
        }
        return person;
    }

    public static ModelPerson getByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persons WHERE name = ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        ModelPerson person = null;
        if (resultSet.next()) {
            person = parseResultSet(resultSet);
        }
        return person;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean insert() {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

}
