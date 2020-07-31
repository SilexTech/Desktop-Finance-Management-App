package application.views.disbursements;

import application.controllers.disbursements.ControllerCashDisbursements;
import application.controllers.disbursements.ControllerCashlessDisbursements;
import application.views.View;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ViewTabDisbursements extends View {

    public ViewTabDisbursements(Group group) {
        super(group);
    }

    @Override
    public void load() {

        TabPane tabPane = new TabPane();

        Group groupCashDisbursements = new Group();
        Tab tabCashDisbursements = new Tab("Gotovinske isplate", groupCashDisbursements);
        new ControllerCashDisbursements(groupCashDisbursements);

        Group groupCashlessDisbursements = new Group();
        Tab tabCashlessDisbursements = new Tab("Bezgotovinske isplate", groupCashlessDisbursements);
        new ControllerCashlessDisbursements(groupCashlessDisbursements);

        tabPane.getTabs().addAll(tabCashDisbursements, tabCashlessDisbursements);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setSide(Side.LEFT);

        group.getChildren().add(tabPane);

    }
}
