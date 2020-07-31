package application.controllers;

import application.views.ViewTabs;
import javafx.scene.Group;

public class ControllerTabs extends Controller {

    public ControllerTabs(Group group) {
        super(new ViewTabs(group));
    }

    @Override
    public void init() {

    }
}
