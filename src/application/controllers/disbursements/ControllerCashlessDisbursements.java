package application.controllers.disbursements;

import application.controllers.Controller;
import application.models.ModelPerson;
import application.models.ModelTransaction;
import application.views.disbursements.ViewCashlessDisbursements;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControllerCashlessDisbursements extends Controller {
    public ControllerCashlessDisbursements(Group group) {
        super(new ViewCashlessDisbursements(group));
    }

    @Override
    public void init() {
        ViewCashlessDisbursements view = (ViewCashlessDisbursements) this.view;
        Button btnInsert = view.getBtnInsert();
        btnInsert.setOnAction(event -> {
            Date date = Date.valueOf(view.getDtpDate().getValue());
            String name = view.getTxtName().getText();
            Double amount = null;
            try {
                amount = Double.valueOf(view.getTxtAmount().getText());
            }
            catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                return;
            }
            String description = view.getTxtDescription().getText();
            if (name == null) return;
            try {
                ModelTransaction mt = new ModelTransaction(null, ModelPerson.getByName(name), amount, date, description, ModelTransaction.Type.CASHLESS_DISBURSEMENT, false, null);
                if(mt.insert()) {
                    ObservableList<ModelTransaction> list = view.getTblIncome().getItems();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) == mt) {
                            view.getTblIncome().getSelectionModel().select(i);
                            break;
                        }
                    }
                    view.getDtpDate().setValue(LocalDate.now());
                    view.getTxtName().clear();
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
