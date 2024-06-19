package com.example.javapolitech_j120_lab3.Puzzle_2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FifteenPuzzleApp extends Application {

    private static final int SIZE = 4; // Размер поля 4x4
    private Button[][] buttons;
    private PuzzleBoard puzzleBoard;
    private TimelineManager timelineManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fifteen Puzzle");

        buttons = new Button[SIZE][SIZE];
        puzzleBoard = new PuzzleBoard(SIZE);
        timelineManager = new TimelineManager();

        GridPane grid = createGrid();

        // Создание кнопок для перемешивания и отмены хода
        Button shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction(e -> handleShuffle());
        shuffleButton.setStyle("-fx-font-size: 18; -fx-background-color: #d32f2f; -fx-text-fill: white;");

        Button undoButton = new Button("Undo");
        undoButton.setOnAction(e -> handleUndo());
        undoButton.setStyle("-fx-font-size: 18; -fx-background-color: #ff9800; -fx-text-fill: white;");

        // Создание метки для таймера
        Label timerLabel = timelineManager.getTimerLabel();

        VBox root = new VBox(10, timerLabel, grid, shuffleButton, undoButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #2c3e50;");

        Scene scene = new Scene(root, 300, 450);
        primaryStage.setScene(scene);
        primaryStage.show();

        timelineManager.startTimer();
    }

    // Метод для создания и настройки сетки кнопок
    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                final int r = row;
                final int c = col;
                Button button = new Button();
                button.setMinSize(60, 60);
                button.setStyle("-fx-font-size: 18; -fx-background-color: #444; -fx-text-fill: white;");
                button.setOnAction(e -> handleButtonClick(r, c));
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }

        updateBoard();
        return grid;
    }

    // Метод для обновления отображения доски
    private void updateBoard() {
        int[][] board = puzzleBoard.getBoard();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    buttons[row][col].setText("");
                } else {
                    buttons[row][col].setText(String.valueOf(board[row][col]));
                }
            }
        }
    }

    // Метод для обработки нажатия кнопок
    private void handleButtonClick(int row, int col) {
        if (puzzleBoard.moveTile(row, col)) {
            updateBoard();
            if (puzzleBoard.checkWin()) {
                timelineManager.stopTimer();
                showWinAlert();
            }
        }
    }

    // Метод для отображения сообщения о победе
    private void showWinAlert() {
        AlertManager.showInformationAlert("Congratulations!", "You have won the game!");
    }

    // Метод для обработки перемешивания
    private void handleShuffle() {
        if (AlertManager.showConfirmationAlert("Shuffle Confirmation", "Are you sure you want to shuffle the board?")) {
            puzzleBoard.shuffle();
            updateBoard();
            timelineManager.resetTimer();
        }
    }

    // Метод для обработки отмены хода
    private void handleUndo() {
        if (puzzleBoard.undo()) {
            updateBoard();
        }
    }
}
