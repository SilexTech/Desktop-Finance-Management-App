package application.views.disbursements;

import application.models.ModelPerson;
import application.models.ModelTransaction;
import application.views.View;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.sql.Date;
import java.time.LocalDate;
import java.util.function.Predicate;

public class ViewCashlessDisbursements extends View {

    private TableView<ModelTransaction> tblIncome = new TableView<>();

    private DatePicker dtpDate = new DatePicker();
    private ComboBox<ModelPerson> cbxPerson = new ComboBox<>();
    private TextField txtAmount = new TextField();
    private TextField txtDescription = new TextField();
    private Button btnInsert = new Button("Unos");

    public ViewCashlessDisbursements(Group group) {
        super(group);
    }

    public TableView<ModelTransaction> getTblIncome() {
        return tblIncome;
    }

    public DatePicker getDtpDate() {
        return dtpDate;
    }

    public ComboBox<ModelPerson> getCbxPerson() {
        return cbxPerson;
    }

    public TextField getTxtAmount() {
        return txtAmount;
    }

    public TextField getTxtDescription() {
        return txtDescription;
    }

    public Button getBtnInsert() {
        return btnInsert;
    }

    @Override
    public void load() {

        BorderPane borderPane = new BorderPane();

        TableColumn<ModelTransaction, Date> tableColumnDate = new TableColumn<>("Datum");
        tableColumnDate.setMinWidth(100);
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnDate.setResizable(false);

        TableColumn<ModelTransaction, ModelPerson> tableColumnPerson = new TableColumn<>("Lice");
        tableColumnPerson.setMinWidth(200);
        tableColumnPerson.setCellValueFactory(new PropertyValueFactory<>("person"));
        tableColumnPerson.setResizable(false);

        TableColumn<ModelTransaction, Double> tableColumnAmount = new TableColumn<>("Iznos");
        tableColumnAmount.setMinWidth(100);
        tableColumnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableColumnAmount.setResizable(false);

        TableColumn<ModelTransaction, String> tableColumnDescription = new TableColumn<>("Opis");
        tableColumnDescription.setMinWidth(200);
        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<ModelTransaction, String>("description"));

        ObjectProperty<Predicate<ModelTransaction>> incomeFilter = new SimpleObjectProperty<>();
        incomeFilter.bind(Bindings.createObjectBinding(() ->
                transaction -> transaction.getType() == ModelTransaction.Type.CASHLESS_DISBURSEMENT));
        FilteredList<ModelTransaction> income = new FilteredList<>(ModelTransaction.getTransactions());
        tblIncome.setItems(income);
        income.predicateProperty().bind(Bindings.createObjectBinding(incomeFilter::get));

        ObjectProperty<Predicate<ModelPerson>> supplierFilter = new SimpleObjectProperty<>();
        supplierFilter.bind(Bindings.createObjectBinding(() ->
                person -> person.getType() == ModelPerson.Type.LEGAL_SUPPLIER));
        FilteredList<ModelPerson> suppliers = new FilteredList<>(ModelPerson.getPersons());
        suppliers.predicateProperty().bind(Bindings.createObjectBinding(supplierFilter::get));
        cbxPerson.setItems(suppliers);

        tblIncome.getColumns().addAll(tableColumnDate, tableColumnPerson, tableColumnAmount, tableColumnDescription);

        tblIncome.widthProperty().addListener((source, oldWidth, newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblIncome.lookup("TableHeaderRow");
            header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    header.setReordering(false);
                }
            });
        });

        borderPane.setCenter(tblIncome);

        HBox inputs = new HBox();

        dtpDate.setPrefWidth(100);
        dtpDate.setValue(LocalDate.now());
        cbxPerson.setPrefWidth(200);
        txtAmount.setPrefWidth(100);
        txtDescription.setPrefWidth(200);
        inputs.getChildren().addAll(dtpDate, cbxPerson, txtAmount, txtDescription, btnInsert);

        borderPane.setBottom(inputs);

        group.getChildren().add(borderPane);

    }

}
