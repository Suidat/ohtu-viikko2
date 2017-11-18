package ohtu;

import java.util.Arrays;

/**
 * Created by frestmau on 18.11.2017.
 */
public class Stat {
    private int students;
    private int hour_total;
    private int exercise_total;
    private Integer[] hours;
    private Integer[] exercises;

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "students=" + students +
                ", hour_total=" + hour_total +
                ", exercise_total=" + exercise_total +
                ", hours=" + Arrays.toString(hours) +
                ", exercises=" + Arrays.toString(exercises) +
                '}';
    }

    public int gethour_total() {
        return hour_total;
    }

    public void sethour_total(int hour_total) {
        this.hour_total = hour_total;
    }

    public int getExercise_total() {
        return exercise_total;
    }

    public void setExercise_total(int exercise_total) {
        this.exercise_total = exercise_total;
    }

    public Integer[] getHours() {
        return hours;
    }

    public void setHours(Integer[] hours) {
        this.hours = hours;
    }

    public Integer[] getExercises() {
        return exercises;
    }

    public void setExercises(Integer[] exercises) {
        this.exercises = exercises;
    }
}
