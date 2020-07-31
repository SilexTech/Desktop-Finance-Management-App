package application.controllers.income_expenses;

import application.controllers.Controller;
import application.models.ModelPerson;
import application.models.ModelTransaction;
import application.views.income_expenses.ViewIncome;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControllerIncome extends Controller {
    public ControllerIncome(Group group) {
        super(new ViewIncome(group));
    }

    @Override
    public void init() {
        ViewIncome view = (ViewIncome) this.view;
        Button btnInsert = view.getBtnInsert();
        btnInsert.setOnAction(event -> {
            Date date = Date.valueOf(view.getDtpDate().getValue());
            ModelPerson person = view.getCbxPerson().getValue();
            Double amount = null;
            try {
                amount = Double.valueOf(view.getTxtAmount().getText());
            }
            catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                return;
            }
            String description = view.getTxtDescription().getText();
            if (person == null) return;
            try {
                ModelTransaction mt = new ModelTransaction(null, person, amount, date, description, ModelTransaction.Type.CLAIM, false, null);
                if(mt.insert()) {
                    ObservableList<ModelTransaction> list = view.getTblIncome().getItems();
                    ModelTransaction.getTransactions().add(mt);
                    view.getTblIncome().getSelectionModel().select(list.indexOf(mt));
                    view.getDtpDate().setValue(LocalDate.now());
                    view.getTxtAmount().clear();
                    view.getTxtDescription().clear();
                    view.getTblIncome().refresh();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnInsert.setDefaultButton(true);
    }
}
