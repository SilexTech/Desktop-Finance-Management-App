package application.views;

import application.controllers.income_expenses.ControllerTabIncomeExpenses;
import application.views.disbursements.ViewTabDisbursements;
import application.views.payments.ViewTabPayments;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ViewTabs extends View {

    public ViewTabs(Group group) {
        super(group);
    }

    @Override
    public void load() {

        TabPane tabPane = new TabPane();

        Group groupIncomeExpenses = new Group();
        Tab tabIncomeExpenses = new Tab("Prihodi/Rashodi", groupIncomeExpenses);
        new ControllerTabIncomeExpenses(groupIncomeExpenses);

        Group groupPayments = new Group();
        Tab tabPayments = new Tab("Uplate", groupPayments);
        new ViewTabPayments(groupPayments).load();

        Group groupDisbursements = new Group();
        Tab tabDisbursements = new Tab("Isplate", groupDisbursements);
        new ViewTabDisbursements(groupDisbursements).load();

        Group groupPersons = new Group();
        Tab tabPersons = new Tab("Lica", groupPersons);
        new ViewTabPersons(groupPersons).load();

        Group groupReports = new Group();
        Tab tabReports = new Tab("Izvještaji", groupReports);
        new ViewTabReports(groupReports).load();

        tabPane.getTabs().addAll(tabIncomeExpenses, tabPayments, tabDisbursements, tabPersons, tabReports);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        group.getChildren().add(tabPane);

    }
}
