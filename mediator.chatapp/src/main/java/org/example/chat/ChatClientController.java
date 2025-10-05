package org.example.chat;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class ChatClientController implements ChatClient {

    private final String username;
    private final ChatMediator mediator;

    private final Stage stage = new Stage();
    private final TextArea chatArea = new TextArea();
    private final TextField messageField = new TextField();
    private final ChoiceBox<String> recipientBox = new ChoiceBox<>();
    private final Button sendBtn = new Button("Send");

    public ChatClientController(String username, ChatMediator mediator) {
        this.username = username;
        this.mediator = mediator;

        buildUI();
        mediator.register(this);

        stage.setOnCloseRequest(e -> {
            mediator.unregister(username);
            stage.hide();
            if (Stage.getWindows().stream().noneMatch(w -> w.isShowing())) {
                Platform.exit();
            }
        });
    }

    public void show() { stage.show(); }

    @Override
    public String getUsername() { return username; }

    @Override
    public void deliverMessage(String from, String message) {
        Platform.runLater(() ->
                chatArea.appendText(String.format("[%s → %s] %s%n", from, username, message))
        );
    }

    @Override
    public void reflectSent(String to, String message) {
        Platform.runLater(() ->
                chatArea.appendText(String.format("[%s → %s] %s%n", username, to, message))
        );
    }

    @Override
    public void updateUserList(List<String> usernames) {
        Platform.runLater(() -> {
            List<String> others = usernames.stream()
                    .filter(u -> !u.equals(username))
                    .collect(Collectors.toList());

            String current = recipientBox.getValue();
            recipientBox.getItems().setAll(others);

            if (current != null && others.contains(current)) {
                recipientBox.setValue(current);
            } else if (!others.isEmpty()) {
                recipientBox.setValue(others.get(0));
            } else {
                recipientBox.setValue(null);
            }

            updateSendDisabledState();
        });
    }

    private void buildUI() {
        stage.setTitle("Chat - " + username);

        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        messageField.setPromptText("Type your message…");
        recipientBox.setPrefWidth(160);

        sendBtn.setDefaultButton(true);
        sendBtn.setOnAction(e -> trySend());
        messageField.setOnAction(e -> trySend());

        messageField.textProperty().addListener((o, a, b) -> updateSendDisabledState());
        recipientBox.valueProperty().addListener((o, a, b) -> updateSendDisabledState());

        HBox bottom = new HBox(8, new Label("To:"), recipientBox, messageField, sendBtn);
        bottom.setPadding(new Insets(8));
        HBox.setHgrow(messageField, Priority.ALWAYS);

        VBox root = new VBox(8, chatArea, bottom);
        root.setPadding(new Insets(8));
        VBox.setVgrow(chatArea, Priority.ALWAYS);

        stage.setScene(new Scene(root, 560, 360));
    }

    private void trySend() {
        String to = recipientBox.getValue();
        String msg = messageField.getText() == null ? "" : messageField.getText().trim();

        if (to == null || to.isBlank()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Choose a recipient.", ButtonType.OK);
            a.initOwner(stage);
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }
        if (msg.isBlank()) return;

        mediator.sendMessage(username, to, msg);
        messageField.clear();
    }

    private void updateSendDisabledState() {
        boolean disabled = recipientBox.getValue() == null
                || messageField.getText() == null
                || messageField.getText().isBlank();
        sendBtn.setDisable(disabled);
    }
}
