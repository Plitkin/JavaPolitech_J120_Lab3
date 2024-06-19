package com.example.javapolitech_j120_lab3.Puzzle_2;

import java.util.Stack;

public class PuzzleBoard {
    private final int SIZE;
    private int[][] board;
    private int emptyRow, emptyCol;
    private Stack<int[][]> previousStates;

    public PuzzleBoard(int size) {
        this.SIZE = size;
        board = new int[SIZE][SIZE];
        previousStates = new Stack<>();
        initializeBoard();
    }

    // Метод для инициализации доски
    private void initializeBoard() {
        int number = 1;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (row == SIZE - 1 && col == SIZE - 1) {
                    board[row][col] = 0; // Пустое место
                } else {
                    board[row][col] = number++;
                }
            }
        }
        emptyRow = SIZE - 1;
        emptyCol = SIZE - 1;
    }

    public int[][] getBoard() {
        return board;
    }

    // Метод для перемещения плитки
    public boolean moveTile(int row, int col) {
        if (Math.abs(emptyRow - row) + Math.abs(emptyCol - col) == 1) {
            saveCurrentState();
            board[emptyRow][emptyCol] = board[row][col];
            board[row][col] = 0;
            emptyRow = row;
            emptyCol = col;
            return true;
        }
        return false;
    }

    // Метод для проверки выигрыша
    public boolean checkWin() {
        boolean isWin = true;
        int number = 1;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (row == SIZE - 1 && col == SIZE - 1) {
                    if (board[row][col] != 0) {
                        isWin = false;
                    }
                } else {
                    if (board[row][col] != number++) {
                        isWin = false;
                    }
                }
            }
        }
        // Проверка для случая, когда 14 и 15 поменяны местами
        boolean specialWinCondition = (board[SIZE - 1][SIZE - 2] == 15 && board[SIZE - 1][SIZE - 3] == 14);
        return isWin || specialWinCondition;
    }

    // Метод для перемешивания доски
    public void shuffle() {
        for (int i = 0; i < 1000; i++) {
            int direction = (int) (Math.random() * 4);
            int newRow = emptyRow;
            int newCol = emptyCol;
            switch (direction) {
                case 0: newRow--; break; // Up
                case 1: newRow++; break; // Down
                case 2: newCol--; break; // Left
                case 3: newCol++; break; // Right
            }
            if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE) {
                board[emptyRow][emptyCol] = board[newRow][newCol];
                board[newRow][newCol] = 0;
                emptyRow = newRow;
                emptyCol = newCol;
            }
        }
        previousStates.clear();
    }

    // Метод для сохранения текущего состояния доски
    private void saveCurrentState() {
        int[][] currentState = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                currentState[row][col] = board[row][col];
            }
        }
        previousStates.push(currentState);
    }

    // Метод для отмены последнего хода
    public boolean undo() {
        if (!previousStates.isEmpty()) {
            board = previousStates.pop();
            findEmptyTile();
            return true;
        }
        return false;
    }

    // Метод для поиска пустой плитки
    private void findEmptyTile() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    emptyRow = row;
                    emptyCol = col;
                    return;
                }
            }
        }
    }
}
