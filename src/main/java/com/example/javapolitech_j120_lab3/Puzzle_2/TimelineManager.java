package com.example.javapolitech_j120_lab3.Puzzle_2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimelineManager {
    private int timeSeconds;
    private Timeline timeline;
    private Label timerLabel;

    public TimelineManager() {
        timerLabel = new Label("Time: 0s");
        timerLabel.setStyle("-fx-font-size: 18; -fx-text-fill: white;");
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    // Метод для запуска таймера
    public void startTimer() {
        timeSeconds = 0;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeSeconds++;
            timerLabel.setText("Time: " + timeSeconds + "s");
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Метод для сброса таймера
    public void resetTimer() {
        timeSeconds = 0;
        timerLabel.setText("Time: 0s");
        timeline.playFromStart();
    }

    // Метод для остановки таймера
    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}
