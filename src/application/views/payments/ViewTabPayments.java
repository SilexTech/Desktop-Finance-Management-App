package application.views.payments;

import application.controllers.payments.ControllerCashPayments;
import application.controllers.payments.ControllerCashlessPayments;
import application.views.View;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ViewTabPayments extends View {

    public ViewTabPayments(Group group) {
        super(group);
    }

    @Override
    public void load() {

        TabPane tabPane = new TabPane();

        Group groupCashPayments = new Group();
        Tab tabCashPayments = new Tab("Gotovinske uplate", groupCashPayments);
        new ControllerCashPayments(groupCashPayments);

        Group groupCashlessPayments = new Group();
        Tab tabCashlessPayments = new Tab("Bezgotovinske uplate", groupCashlessPayments);
        new ControllerCashlessPayments(groupCashlessPayments);

        tabPane.getTabs().addAll(tabCashPayments, tabCashlessPayments);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setSide(Side.LEFT);

        group.getChildren().add(tabPane);

    }
}
