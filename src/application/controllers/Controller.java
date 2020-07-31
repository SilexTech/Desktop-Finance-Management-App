package application.controllers;

import application.views.View;

public abstract class Controller {

    protected final View view;

    public Controller(View view) {
        this.view = view;
        this.view.load();
        init();
    }

    public abstract void init();

}
