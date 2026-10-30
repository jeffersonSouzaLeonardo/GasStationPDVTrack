package com.br.manager.domain.shift.controller;

import com.br.manager.config.SpringFxmlLoader;
import com.br.manager.domain.shift.entity.CashSession;
import com.br.manager.domain.shift.service.CashSessionService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@Scope("prototype")
public class CashSessionListController {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final CashSessionService cashSessionService;
    private final SpringFxmlLoader fxmlLoader;

    @FXML private StackPane root;
    @FXML private TableView<CashSession> sessionTable;
    @FXML private TableColumn<CashSession, Long> numberColumn;
    @FXML private TableColumn<CashSession, String> shiftColumn;
    @FXML private TableColumn<CashSession, String> openedByColumn;
    @FXML private TableColumn<CashSession, String> closedByColumn;
    @FXML private TableColumn<CashSession, String> openedAtColumn;
    @FXML private TableColumn<CashSession, String> closedAtColumn;
    @FXML private TableColumn<CashSession, String> statusColumn;
    @FXML private TableColumn<CashSession, Void> actionColumn;

    public CashSessionListController(CashSessionService cashSessionService, SpringFxmlLoader fxmlLoader) {
        this.cashSessionService = cashSessionService;
        this.fxmlLoader = fxmlLoader;
    }

    @FXML
    private void initialize() {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("sessionNumber"));
        shiftColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getShift() != null ? cell.getValue().getShift().getName() : "-"));
        openedByColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getOpenedBy() != null ? cell.getValue().getOpenedBy().getName() : "-"));
        closedByColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getClosedBy() != null ? cell.getValue().getClosedBy().getName() : "-"));
        openedAtColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getOpenedAt() != null
                        ? DATE_TIME_FORMAT.format(cell.getValue().getOpenedAt()) : "-"));
        closedAtColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getClosedAt() != null
                        ? DATE_TIME_FORMAT.format(cell.getValue().getClosedAt()) : "-"));
        statusColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getStatus().getLabel()));

        actionColumn.setCellFactory(column -> new TableCell<>() {
            private final Button openButton = new Button("Abrir");
            {
                openButton.getStyleClass().add("table-action-button");
                openButton.setOnAction(event -> openDetail(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : openButton);
            }
        });

        sessionTable.setPlaceholder(new Label("Nenhuma sessão de caixa encontrada."));
        reload();
    }

    @FXML
    private void onRefresh() {
        reload();
    }

    @FXML
    private void onNewCashier() {
        openDetail(null);
    }

    private void reload() {
        sessionTable.setItems(FXCollections.observableArrayList(cashSessionService.findAll()));
    }

    private void openDetail(CashSession session) {
        Parent detail = fxmlLoader.load("/fxml/shift-cashier-view.fxml",
                (ShiftCashierController controller) -> controller.setCashSession(session));
        if (root.getParent() instanceof Pane parent) {
            parent.getChildren().setAll(detail);
        }
    }
}
