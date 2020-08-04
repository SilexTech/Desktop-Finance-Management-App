package application.controllers.persons;

import application.controllers.Controller;
import application.models.ModelPerson;
import application.models.ModelTransaction;
import application.views.income_expenses.ViewIncome;
import application.views.persons.ViewClients;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControllerClients extends Controller {
    public ControllerClients(Group group) {
        super(new ViewClients(group));
    }

    @Override
    public void init() {
        ViewClients view = (ViewClients) this.view;
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
            ModelPerson.Type clientType;
            switch (type) {
                case "pravno": clientType = ModelPerson.Type.LEGAL_CLIENT; break;
                case "fizičko": clientType = ModelPerson.Type.NATURAL_CLIENT; break;
                default: return;
            }
            try {
                ModelPerson mp = new ModelPerson(null, name, clientType);
                if(mp.insert()) {
                    ObservableList<ModelPerson> list = view.getTblClients().getItems();
                    ModelPerson.getPersons().add(mp);
                    view.getTblClients().getSelectionModel().select(list.indexOf(mp));
                    view.getTxtName().clear();
                    view.getTblClients().refresh();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnInsert.setDefaultButton(true);
    }
}
