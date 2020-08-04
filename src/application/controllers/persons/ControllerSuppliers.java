package application.controllers.persons;

import application.controllers.Controller;
import application.models.ModelPerson;
import application.models.ModelTransaction;
import application.views.income_expenses.ViewIncome;
import application.views.persons.ViewSuppliers;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControllerSuppliers extends Controller {
    public ControllerSuppliers(Group group) {
        super(new ViewSuppliers(group));
    }

    @Override
    public void init() {
        ViewSuppliers view = (ViewSuppliers) this.view;
        Button btnInsert = view.getBtnInsert();
        btnInsert.setOnAction(event -> {
            String name = view.getTxtName().getText();
            String type = view.getCbxType().getValue();
            ModelPerson person = null;
            for (ModelPerson p : ModelPerson.getPersons())
                if (p.getName().equals(name)) {
                    person = p;
                    break;
                }
            if (person != null) return;
            ModelPerson.Type SupplierType;
            switch (type) {
                case "pravno": SupplierType = ModelPerson.Type.LEGAL_SUPPLIER; break;
                case "fizičko": SupplierType = ModelPerson.Type.NATURAL_SUPPLIER; break;
                default: return;
            }
            try {
                ModelPerson mp = new ModelPerson(null, name, SupplierType);
                if(mp.insert()) {
                    ObservableList<ModelPerson> list = view.getTblSuppliers().getItems();
                    ModelPerson.getPersons().add(mp);
                    view.getTblSuppliers().getSelectionModel().select(list.indexOf(mp));
                    view.getTxtName().clear();
                    view.getTblSuppliers().refresh();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnInsert.setDefaultButton(true);
    }
}
