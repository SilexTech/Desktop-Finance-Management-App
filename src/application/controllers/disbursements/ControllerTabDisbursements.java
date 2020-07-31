package application.controllers.disbursements;

import application.controllers.Controller;
import application.views.disbursements.ViewTabDisbursements;
import application.views.payments.ViewTabPayments;
import javafx.scene.Group;

public class ControllerTabDisbursements extends Controller {
    public ControllerTabDisbursements(Group group) {
        super(new ViewTabDisbursements(group));
    }

    @Override
    public void init() {

    }
}
