package com.example.myapplication;


public class Cuotas {
    public double prediction_goals;
    public double prediction_away;

    public Cuotas(double prediction_goals, double prediction_away) {
        this.prediction_goals = prediction_goals;
        this.prediction_away = prediction_away;
    }

    public double getPrediction_goals() {
        return prediction_goals;
    }

    @Override
    public String toString() {
        return "Cuotas{" +
                "prediction_goals=" + prediction_goals +
                ", prediction_away=" + prediction_away +
                '}';
    }

    public void setPrediction_goals(double prediction_goals) {
        this.prediction_goals = prediction_goals;
    }

    public double getPrediction_away() {
        return prediction_away;
    }

    public void setPrediction_away(double prediction_away) {
        this.prediction_away = prediction_away;
    }
}

