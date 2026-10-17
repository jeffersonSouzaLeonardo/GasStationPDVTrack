package com.br.manager.domain.integration.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/** Temporary view for screens that are not implemented yet. */
public final class PlaceholderView extends StackPane {

    public PlaceholderView(String title) {
        Label label = new Label(title);
        label.getStyleClass().add("page-title");
        getChildren().add(label);
    }
}
