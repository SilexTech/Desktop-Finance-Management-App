package application.controllers.income_expenses;

import application.controllers.Controller;
import application.views.income_expenses.ViewTabIncomeExpenses;
import javafx.scene.Group;


public class ControllerTabIncomeExpenses extends Controller {
    public ControllerTabIncomeExpenses(Group group) {
        super(new ViewTabIncomeExpenses(group));
    }

    @Override
    public void init() {

    }
}
