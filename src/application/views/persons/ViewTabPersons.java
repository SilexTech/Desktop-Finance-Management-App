package application.views.persons;

import application.controllers.income_expenses.ControllerExpenses;
import application.controllers.income_expenses.ControllerIncome;
import application.controllers.persons.ControllerClients;
import application.controllers.persons.ControllerSuppliers;
import application.views.View;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ViewTabPersons extends View {

    public ViewTabPersons(Group group) {
        super(group);
    }

    @Override
    public void load() {

        TabPane tabPane = new TabPane();

        Group groupClients = new Group();
        Tab tabClients = new Tab("Klijenti", groupClients);
        new ControllerClients(groupClients);

        Group groupSuppliers = new Group();
        Tab tabSuppliers = new Tab("Dobavljači", groupSuppliers);
        new ControllerSuppliers(groupSuppliers);

        tabPane.getTabs().addAll(tabClients, tabSuppliers);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setSide(Side.LEFT);

        group.getChildren().add(tabPane);

    }

}
