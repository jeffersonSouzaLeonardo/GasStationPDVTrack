package com.br.manager.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Consumer;

/** Loads FXML files delegating controller creation to Spring. */
@Component
public class SpringFxmlLoader {

    private final ApplicationContext context;

    public SpringFxmlLoader(ApplicationContext context) {
        this.context = context;
    }

    public Parent load(String fxmlPath) {
        FXMLLoader loader = newLoader(fxmlPath);
        try {
            return loader.load();
        } catch (IOException e) {
            throw new UncheckedIOException("Could not load view " + fxmlPath, e);
        }
    }

    /** Loads a view and hands its controller to the given consumer for initialization. */
    public <C> Parent load(String fxmlPath, Consumer<C> controllerInitializer) {
        FXMLLoader loader = newLoader(fxmlPath);
        try {
            Parent root = loader.load();
            @SuppressWarnings("unchecked")
            C controller = (C) loader.getController();
            controllerInitializer.accept(controller);
            return root;
        } catch (IOException e) {
            throw new UncheckedIOException("Could not load view " + fxmlPath, e);
        }
    }

    private FXMLLoader newLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(context::getBean);
        return loader;
    }
}
