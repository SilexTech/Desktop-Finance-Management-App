package application.views.income_expenses;

import application.controllers.income_expenses.ControllerExpenses;
import application.controllers.income_expenses.ControllerIncome;
import application.views.View;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ViewTabIncomeExpenses extends View {

    public ViewTabIncomeExpenses(Group group) {
        super(group);
    }

    @Override
    public void load() {

        TabPane tabPane = new TabPane();

        Group groupIncome = new Group();
        Tab tabIncome = new Tab("Prihodi", groupIncome);
        new ControllerIncome(groupIncome);

        Group groupExpenses = new Group();
        Tab tabExpenses = new Tab("Rashodi", groupExpenses);
        new ControllerExpenses(groupExpenses);

        tabPane.getTabs().addAll(tabIncome, tabExpenses);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setSide(Side.LEFT);

        group.getChildren().add(tabPane);

    }

}
