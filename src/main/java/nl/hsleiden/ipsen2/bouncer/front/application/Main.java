package nl.hsleiden.ipsen2.bouncer.front.application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);

        primaryStage.setTitle("Bouncer");
        primaryStage.show();
        primaryStage.setWidth(1600);
        primaryStage.setHeight(900);

        ViewManager.setWindow(primaryStage);
        ViewManager.addScene("LoginView", "Login");
        ViewManager.setShowingScene("Login");

        RequestManager.setHost("http://127.0.0.1:8080");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
