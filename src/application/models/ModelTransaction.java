package application.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ModelTransaction extends Model {

    public enum Type { DEBT, CLAIM, CASH_DISBURSEMENT, CASHLESS_DISBURSEMENT, CASH_PAYMENT, CASHLESS_PAYMENT };

    private static ObservableList<ModelTransaction> transactions = FXCollections.observableArrayList();

    private Integer transactionID;
    private ModelPerson person;
    private Double amount;
    private Date date;
    private String description;
    private ModelTransaction.Type type;
    private boolean VAT;
    private String image;

    public ModelTransaction(Integer transactionID, ModelPerson person, Double amount, Date date, String description, Type type, boolean VAT, String image) {
        this.transactionID = transactionID;
        this.person = person;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
        this.VAT = VAT;
        this.image = image;
        //transactions.add(this);
    }

    public static ObservableList<ModelTransaction> getTransactions() {
        return transactions;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public ModelPerson getPerson() {
        return person;
    }

    public void setPerson(ModelPerson person) {
        this.person = person;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isVAT() {
        return VAT;
    }

    public void setVAT(boolean VAT) {
        this.VAT = VAT;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private static ModelTransaction parseResultSet(ResultSet resultSet) throws SQLException {
        int transactionID = resultSet.getInt(1);
        ModelPerson person = ModelPerson.getByID(resultSet.getInt(2));
        Double amount = resultSet.getDouble(3);
        Date date = resultSet.getDate(4);
        String description = resultSet.getString(5);
        if (description == null)
            description = "";
        Type type = Type.valueOf(resultSet.getString(6));
        boolean VAT = resultSet.getBoolean(7);
        String image = resultSet.getString(8);
        return new ModelTransaction(transactionID, person,  amount, date, description, type, false, image);
    }

    public static void init() throws SQLException {
        Model.init(); // !important
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions");
        while (resultSet.next()) {
            transactions.add(parseResultSet(resultSet));
        }
    }

    @Override
    public boolean insert() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO transactions(Person, Amount, Date, Description, Type, VAT) VALUES (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, person.getPersonID());
        preparedStatement.setDouble(2, amount);
        preparedStatement.setDate(3, date);
        preparedStatement.setString(4, description);
        preparedStatement.setString(5, type.toString());
        preparedStatement.setBoolean(6, false);
        int i = preparedStatement.executeUpdate();
        if (i == 1) {
            preparedStatement.setInt(1, person.getPersonID());
            preparedStatement.setDouble(2, amount);
            preparedStatement.setDate(3, date);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, type.toString());
            preparedStatement.setBoolean(6, false);
            i = preparedStatement.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions ORDER BY TransactionID LIMIT 1");
            if (resultSet.next()) {
                this.transactionID = parseResultSet(resultSet).transactionID;
            }
            return true;
        }
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

}
