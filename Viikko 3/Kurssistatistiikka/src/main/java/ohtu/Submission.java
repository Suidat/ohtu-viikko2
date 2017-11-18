package ohtu;

import java.util.Arrays;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private int max = 0;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return  "Viikko " + week +
                ":\n\ttehtyjä tehtäviä yhteensä "+ exercises.length + " (maksimi "+ max +"), aikaa kului " + hours +
                " tuntia, tehdyt tehtävät: " + Arrays.toString(exercises);
    }
}