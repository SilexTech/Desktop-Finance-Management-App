package application.controllers.persons;

import application.controllers.Controller;
import application.views.persons.ViewTabPersons;
import javafx.scene.Group;


public class ControllerTabPersons extends Controller {
    public ControllerTabPersons(Group group) {
        super(new ViewTabPersons(group));
    }

    @Override
    public void init() {

    }
}
