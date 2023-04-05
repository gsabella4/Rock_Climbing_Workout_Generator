package model;

import java.time.LocalDate;

public class Workout {

    private int numOfRoutes;
    private String climbType;
    private String climberBaseline;
    private String workoutIntensity;
    private LocalDate workoutDate;

    public Workout(int numOfRoutes, String climbType, String climberBaseline, String workoutIntensity, LocalDate workoutDate) {
        this.numOfRoutes = numOfRoutes;
        this.climbType = climbType;
        this.climberBaseline = climberBaseline;
        this.workoutIntensity = workoutIntensity;
        this.workoutDate = workoutDate;
    }

    public int getNumOfRoutes() {
        return numOfRoutes;
    }

    public void setNumOfRoutes(int numOfRoutes) {
        this.numOfRoutes = numOfRoutes;
    }

    public String getClimbType() {
        return climbType;
    }

    public void setClimbType(String climbType) {
        this.climbType = climbType;
    }

    public String getClimberBaseline() {
        return climberBaseline;
    }

    public void setClimberBaseline(String climberBaseline) {
        this.climberBaseline = climberBaseline;
    }

    public String getWorkoutIntensity() {
        return workoutIntensity;
    }

    public void setWorkoutIntensity(String workoutIntensity) {
        this.workoutIntensity = workoutIntensity;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "numOfRoutes=" + numOfRoutes +
                ", climbType='" + climbType + '\'' +
                ", climberBaseline='" + climberBaseline + '\'' +
                ", workoutIntensity='" + workoutIntensity + '\'' +
                ", workoutDate=" + workoutDate +
                '}';
    }
}
