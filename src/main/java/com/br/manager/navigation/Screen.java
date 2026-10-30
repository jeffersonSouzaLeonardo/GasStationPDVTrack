package com.br.manager.navigation;

public enum Screen {
    MENU("/fxml/menu-view.fxml"),
    COMMANDS(null, "Comandas"),
    FUELINGS(null, "Abastecimentos"),
    SHIFT_CASHIER("/fxml/cash-session-list-view.fxml", "Turno/Caixa"),
    CONCENTRATOR("/fxml/concentrator-view.fxml", "Concentrador"),
    USER(null, "Usuário");

    private final String fxmlPath;
    private final String title;

    Screen(String fxmlPath) {
        this(fxmlPath, "");
    }

    Screen(String fxmlPath, String title) {
        this.fxmlPath = fxmlPath;
        this.title = title;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public boolean hasView() {
        return fxmlPath != null;
    }

    public String getTitle() {
        return title;
    }
}
