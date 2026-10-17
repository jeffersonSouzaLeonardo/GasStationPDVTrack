package com.br.manager.domain.integration.controller;

import com.br.manager.domain.integration.model.ConnectionStatus;
import com.br.manager.domain.integration.service.ConcentratorService;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ConcentratorController {

    private final ConcentratorService concentratorService;

    @FXML private StackPane root;
    @FXML private Button connectButton;
    @FXML private Label statusLabel;
    @FXML private Label lastFuelingLabel;

    public ConcentratorController(ConcentratorService concentratorService) {
        this.concentratorService = concentratorService;
    }

    @FXML
    private void initialize() {
        showStatus(concentratorService.getStatus());
    }

    @FXML
    private void onConnect() {
        showStatus(ConnectionStatus.CONNECTING);
        connectButton.setDisable(true);

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                concentratorService.connect();
                return null;
            }
        };
        task.setOnSucceeded(e -> finish());
        task.setOnFailed(e -> finish());
        Thread thread = new Thread(task, "concentrator-connect");
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void onClose() {
        if (root.getParent() instanceof Pane parent) {
            parent.getChildren().remove(root);
        }
    }

    private void finish() {
        Platform.runLater(() -> {
            showStatus(concentratorService.getStatus());
            connectButton.setDisable(false);
        });
    }

    private void showStatus(ConnectionStatus status) {
        statusLabel.setText(status.getLabel());
        statusLabel.getStyleClass().removeAll("status-connected", "status-connecting", "status-disconnected");
        statusLabel.getStyleClass().add("status-" + status.name().toLowerCase());
    }
}
