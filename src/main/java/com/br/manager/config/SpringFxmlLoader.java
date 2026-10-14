package com.br.manager.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;

/** Loads FXML files delegating controller creation to Spring. */
@Component
public class SpringFxmlLoader {

    private final ApplicationContext context;

    public SpringFxmlLoader(ApplicationContext context) {
        this.context = context;
    }

    public Parent load(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(context::getBean);
        try {
            return loader.load();
        } catch (IOException e) {
            throw new UncheckedIOException("Could not load view " + fxmlPath, e);
        }
    }
}
