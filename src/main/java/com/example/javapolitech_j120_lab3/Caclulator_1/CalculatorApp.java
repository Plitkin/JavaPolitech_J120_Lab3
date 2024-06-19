package com.example.javapolitech_j120_lab3.Caclulator_1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");

        Calculator calculator = new Calculator();
        CalculatorUI calculatorUI = new CalculatorUI(calculator);

        VBox root = new VBox();
        root.getChildren().add(calculatorUI);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 310, 470);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
