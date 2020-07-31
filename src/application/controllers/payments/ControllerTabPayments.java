package application.controllers.payments;

import application.controllers.Controller;
import application.views.payments.ViewTabPayments;
import javafx.scene.Group;

public class ControllerTabPayments extends Controller {
    public ControllerTabPayments(Group group) {
        super(new ViewTabPayments(group));
    }

    @Override
    public void init() {

    }
}
