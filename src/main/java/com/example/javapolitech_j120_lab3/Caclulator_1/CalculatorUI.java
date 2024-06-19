package com.example.javapolitech_j120_lab3.Caclulator_1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CalculatorUI extends VBox {
    private final Label display;
    private final Calculator calculator;

    public CalculatorUI(Calculator calculator) {
        this.calculator = calculator;

        display = new Label("0");
        display.setStyle("-fx-font-size: 24; -fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setMinSize(270, 60); // Установка размера дисплея

        VBox displayBox = new VBox(display);
        displayBox.setAlignment(Pos.CENTER);
        displayBox.setPadding(new Insets(10, 10, 10, 10));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "C", "+"
        };

        int row = 0;
        int col = 0;
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setMinSize(60, 60);
            button.setStyle("-fx-font-size: 18; -fx-background-color: #444; -fx-text-fill: white;");
            button.setOnAction(e -> handleButtonClick(label));
            grid.add(button, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        Button equalsButton = new Button("=");
        equalsButton.setMinSize(270, 60); // Установка размера кнопки "Равно"
        equalsButton.setStyle("-fx-font-size: 18; -fx-background-color: #ff9800; -fx-text-fill: white;");
        equalsButton.setOnAction(e -> handleEquals());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(displayBox, grid, equalsButton);

        getChildren().add(layout);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #2c3e50;");
    }

    private void handleButtonClick(String label) {
        display.setText(calculator.process(label, display.getText()));
    }

    private void handleEquals() {
        display.setText(calculator.calculate(display.getText()));
    }

    private void handleClear() {
        calculator.reset();
        display.setText("0");
    }
}
