package application;

import application.controllers.Controller;
import application.controllers.ControllerTabs;
import application.models.ModelPerson;
import application.models.ModelTransaction;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group);
        primaryStage.setTitle("Transakcije");

        ModelPerson.init();
        ModelTransaction.init();

        Controller c = new ControllerTabs(group);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
