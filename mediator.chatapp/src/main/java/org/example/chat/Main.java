package org.example.chat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Platform.setImplicitExit(false);

        ChatMediator mediator = new ChatMediatorImpl();

        // Create at least 3 windows (add more names if you like)
        List.of("Alice", "Bob", "Carol").forEach(name -> {
            ChatClientController c = new ChatClientController(name, mediator);
            c.show();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
