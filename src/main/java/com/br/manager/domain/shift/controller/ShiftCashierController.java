package com.br.manager.domain.shift.controller;

import com.br.manager.config.SpringFxmlLoader;
import com.br.manager.domain.operational.entity.User;
import com.br.manager.domain.operational.service.UserService;
import com.br.manager.domain.shift.entity.CashSession;
import com.br.manager.domain.shift.entity.CashSessionStatusEnum;
import com.br.manager.domain.shift.entity.Shift;
import com.br.manager.domain.shift.service.CashSessionService;
import com.br.manager.domain.shift.service.ShiftService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Scope("prototype")
public class ShiftCashierController {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final ShiftService shiftService;
    private final CashSessionService cashSessionService;
    private final UserService userService;
    private final SpringFxmlLoader fxmlLoader;

    @FXML private StackPane root;
    @FXML private ComboBox<User> operatorCombo;
    @FXML private ComboBox<Shift> shiftCombo;
    @FXML private Button newShiftButton;
    @FXML private TextField openingAmountField;
    @FXML private Button openSessionButton;
    @FXML private TextField closingAmountField;
    @FXML private Button closeSessionButton;
    @FXML private Label sessionInfoLabel;
    @FXML private Label messageLabel;

    private CashSession currentSession;

    public ShiftCashierController(ShiftService shiftService,
                                  CashSessionService cashSessionService,
                                  UserService userService,
                                  SpringFxmlLoader fxmlLoader) {
        this.shiftService = shiftService;
        this.cashSessionService = cashSessionService;
        this.userService = userService;
        this.fxmlLoader = fxmlLoader;
    }

    @FXML
    private void initialize() {
        operatorCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(User user) {
                return user == null ? "" : user.getName();
            }

            @Override
            public User fromString(String string) {
                return null;
            }
        });
        shiftCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(Shift shift) {
                return shift == null ? "" : shift.getName() + " (" + shift.getStatus().getLabel() + ")";
            }

            @Override
            public Shift fromString(String string) {
                return null;
            }
        });

        operatorCombo.setItems(FXCollections.observableArrayList(userService.findAllActive()));
        reloadShifts();
        shiftService.getActiveShift().ifPresent(active ->
                selectShiftById(active.getId().toString()));
        updateState();
    }

    /** Loads the context of an existing cash session (null for a new one). */
    public void setCashSession(CashSession session) {
        this.currentSession = session;
        if (session != null) {
            selectShiftById(session.getShift().getId().toString());
            selectUserById(session.getOpenedBy().getId().toString());
            openingAmountField.setText(session.getOpeningAmount() != null
                    ? session.getOpeningAmount().toPlainString() : "0");
            if (session.getClosingAmount() != null) {
                closingAmountField.setText(session.getClosingAmount().toPlainString());
            }
        }
        updateState();
    }

    @FXML
    private void onNewShift() {
        try {
            Shift shift = shiftService.openShift("Turno " + DATE_TIME_FORMAT.format(LocalDateTime.now()));
            reloadShifts();
            selectShiftById(shift.getId().toString());
            showMessage("Turno aberto: " + shift.getName(), false);
        } catch (Exception e) {
            showMessage("Erro ao abrir turno: " + e.getMessage(), true);
        }
    }

    @FXML
    private void onOpenSession() {
        User operator = operatorCombo.getValue();
        Shift shift = shiftCombo.getValue();
        if (operator == null) {
            showMessage("Selecione o operador.", true);
            return;
        }
        if (shift == null) {
            showMessage("Selecione o turno.", true);
            return;
        }
        try {
            BigDecimal openingAmount = parseAmount(openingAmountField.getText());
            currentSession = cashSessionService.openSession(shift, operator, openingAmount);
            showMessage("Caixa aberto com sucesso.", false);
            updateState();
        } catch (Exception e) {
            showMessage("Erro ao abrir caixa: " + e.getMessage(), true);
        }
    }

    @FXML
    private void onCloseSession() {
        if (currentSession == null) {
            return;
        }
        User closedBy = operatorCombo.getValue() != null ? operatorCombo.getValue() : currentSession.getOpenedBy();
        try {
            BigDecimal closingAmount = parseAmount(closingAmountField.getText());
            currentSession = cashSessionService.closeSession(currentSession.getId(), closedBy, closingAmount);
            showMessage("Caixa fechado com sucesso.", false);
            updateState();
        } catch (Exception e) {
            showMessage("Erro ao fechar caixa: " + e.getMessage(), true);
        }
    }

    @FXML
    private void onBack() {
        Parent list = fxmlLoader.load("/fxml/cash-session-list-view.fxml");
        if (root.getParent() instanceof Pane parent) {
            parent.getChildren().setAll(list);
        }
    }

    private void reloadShifts() {
        shiftCombo.setItems(FXCollections.observableArrayList(shiftService.findAll()));
    }

    private void selectShiftById(String id) {
        shiftCombo.getItems().stream()
                .filter(shift -> shift.getId().toString().equals(id))
                .findFirst()
                .ifPresent(shift -> shiftCombo.getSelectionModel().select(shift));
    }

    private void selectUserById(String id) {
        operatorCombo.getItems().stream()
                .filter(user -> user.getId().toString().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        user -> operatorCombo.getSelectionModel().select(user),
                        () -> {
                            // operador pode estar inativo; adiciona para exibição
                            if (currentSession != null && currentSession.getOpenedBy() != null) {
                                operatorCombo.getItems().add(currentSession.getOpenedBy());
                                operatorCombo.getSelectionModel().select(currentSession.getOpenedBy());
                            }
                        });
    }

    private void updateState() {
        boolean hasSession = currentSession != null;
        boolean open = hasSession && currentSession.getStatus() == CashSessionStatusEnum.OPEN;

        operatorCombo.setDisable(hasSession);
        shiftCombo.setDisable(hasSession);
        newShiftButton.setDisable(hasSession);
        openingAmountField.setDisable(hasSession);
        openSessionButton.setDisable(hasSession);
        closingAmountField.setDisable(!open);
        closeSessionButton.setDisable(!open);

        if (!hasSession) {
            sessionInfoLabel.setText("Nenhum caixa selecionado. Escolha o operador e o turno para abrir um novo caixa.");
        } else {
            StringBuilder info = new StringBuilder()
                    .append("Caixa ")
                    .append(currentSession.getSessionNumber() != null
                            ? "#" + currentSession.getSessionNumber() : "")
                    .append(" — ")
                    .append(currentSession.getStatus().getLabel())
                    .append(" | Aberto por: ")
                    .append(currentSession.getOpenedBy() != null ? currentSession.getOpenedBy().getName() : "-")
                    .append(" em ")
                    .append(currentSession.getOpenedAt() != null
                            ? DATE_TIME_FORMAT.format(currentSession.getOpenedAt()) : "-");
            if (currentSession.getStatus() == CashSessionStatusEnum.CLOSED) {
                info.append(" | Fechado por: ")
                        .append(currentSession.getClosedBy() != null ? currentSession.getClosedBy().getName() : "-")
                        .append(" em ")
                        .append(currentSession.getClosedAt() != null
                                ? DATE_TIME_FORMAT.format(currentSession.getClosedAt()) : "-");
            }
            sessionInfoLabel.setText(info.toString());
        }
    }

    private BigDecimal parseAmount(String text) {
        if (text == null || text.isBlank()) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(text.trim().replace(",", "."));
    }

    private void showMessage(String message, boolean error) {
        messageLabel.setText(message);
        messageLabel.getStyleClass().removeAll("status-connected", "status-disconnected");
        messageLabel.getStyleClass().add(error ? "status-disconnected" : "status-connected");
    }
}
