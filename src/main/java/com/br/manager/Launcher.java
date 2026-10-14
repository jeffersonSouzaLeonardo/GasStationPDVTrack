package com.br.manager;

import javafx.application.Application;

/** Plain entry point; avoids the JavaFX "runtime components missing" error on the classpath. */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(PdvApplication.class, args);
    }
}
