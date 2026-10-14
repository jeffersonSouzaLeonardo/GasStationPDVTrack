package com.br.manager.controller;

import com.br.manager.config.SpringFxmlLoader;
import com.br.manager.navigation.Screen;
import com.br.manager.view.PlaceholderView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuController {

    private static final String ACTIVE_STYLE = "active";

    private final SpringFxmlLoader fxmlLoader;

    @FXML private StackPane contentArea;
    @FXML private Pane settingsSubmenu;
    @FXML private Button commandsButton;
    @FXML private Button fuelingsButton;
    @FXML private Button shiftButton;
    @FXML private Button settingsButton;
    @FXML private Button concentratorButton;
    @FXML private Button userButton;

    public MenuController(SpringFxmlLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    @FXML
    private void initialize() {
        settingsSubmenu.managedProperty().bind(settingsSubmenu.visibleProperty());
        settingsSubmenu.setVisible(false);
    }

    @FXML private void onCommands() { navigate(Screen.COMMANDS, commandsButton); }

    @FXML private void onFuelings() { navigate(Screen.FUELINGS, fuelingsButton); }

    @FXML private void onShift() { navigate(Screen.SHIFT_CASHIER, shiftButton); }

    @FXML private void onConcentrator() { navigate(Screen.CONCENTRATOR, concentratorButton); }

    @FXML private void onUser() { navigate(Screen.USER, userButton); }

    @FXML
    private void onSettings() {
        settingsSubmenu.setVisible(!settingsSubmenu.isVisible());
    }

    private void navigate(Screen screen, Button source) {
        Node view = screen.hasView() ? fxmlLoader.load(screen.getFxmlPath()) : new PlaceholderView(screen.getTitle());
        contentArea.getChildren().setAll(view);
        highlight(source);
    }

    private void highlight(Button active) {
        List.of(commandsButton, fuelingsButton, shiftButton, settingsButton, concentratorButton, userButton)
                .forEach(b -> b.getStyleClass().remove(ACTIVE_STYLE));
        active.getStyleClass().add(ACTIVE_STYLE);
    }
}
