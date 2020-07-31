package application.views;

import javafx.scene.Group;

public abstract class View {

    protected Group group;

    public View(Group group) {
        this.group = group;
    }

    public abstract void load();

}
