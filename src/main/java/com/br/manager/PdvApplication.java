package com.br.manager;

import com.br.manager.config.HorustechProperties;
import com.br.manager.config.SpringFxmlLoader;
import com.br.manager.navigation.Screen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableConfigurationProperties(HorustechProperties.class)
public class PdvApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = new SpringApplication(PdvApplication.class).run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) {
        Parent root = context.getBean(SpringFxmlLoader.class).load(Screen.MENU.getFxmlPath());
        Rectangle2D bounds = javafx.stage.Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        scene.getStylesheets().add(getClass().getResource("/css/dark-theme.css").toExternalForm());

        stage.setTitle("Gas Station PDV");
        stage.setScene(scene);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}
