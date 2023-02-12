import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInput {

    static Scanner userInput = new Scanner(System.in);

    private static int routeNumberValue;
    private static int climbType;
    private static String baseLineValue;
    private static int workoutIntensity;
    //Arrays to hold grades for both Top Rope and Bouldering. Grade is level of difficulty, sorted in ascending order from easy to more difficult
    public static String[] topRopeGradeArray = {"5.5", "5.6", "5.7", "5.8", "5.9", "10a", "10b", "10c", "10d", "11a", "11b", "11c", "11d", "12a", "12b", "12c", "12d"};
    public static String[] boulderGradeArray = {"VB", "V0", "V1", "V2", "V3", "V4", "V5", "V6", "V7", "V8", "V9", "V10"};


    public static void numberOfSendsInput(){
        String routeNumberString = userInput.nextLine();
        routeNumberValue = Integer.parseInt(routeNumberString);
    }

    public static int getRouteNumber(){
        return routeNumberValue;
    }

    public static void climbTypeInput(){
        String climbTypeString = userInput.nextLine();
        if (climbTypeString.equals("1") || climbTypeString.equals("2")){
            climbType = Integer.parseInt(climbTypeString);
        }
        else {
            throw new IllegalArgumentException();
        }

    }

    public static int getClimbType(){
        return climbType;
    }

    // this method will take user's input for their baseline grade, check if that grade is 1) valid and 2) that grade exists in current range, if not, throws custom exception InvalidGradeException
    public static void baseLineInput() throws InvalidGradeException {
        String baseline = userInput.nextLine();
        if (climbType == 1) {
            List<String> topRopeList = new ArrayList<>(Arrays.asList(topRopeGradeArray));
            if (topRopeList.contains(baseline.toLowerCase())){
                    baseline = baseline.toLowerCase();
                }
                else {
                    throw new InvalidGradeException("\n\tYou must enter a valid top-rope grade! Try again.");
                }
            }
         else if (climbType == 2) {
            List<String> boulderList = new ArrayList<>(Arrays.asList(boulderGradeArray));
            if (boulderList.contains(baseline.toUpperCase())){
                baseline = baseline.toUpperCase();
            }
                else {
                    throw new InvalidGradeException("\n\tYou must enter a valid Boulder grade! Try again.");
                }
            }
        baseLineValue = baseline;
    }

    public static String getBaseLine(){
        return baseLineValue;
    }

    // this method returns the index of user's baseline grade in either top-rope or boulder grades
    public static int getBaseLineIndex(){
        int baselineIndex = 0;
        if (climbType == 1) {
            baselineIndex = Arrays.asList(topRopeGradeArray).indexOf(getBaseLine());
        } else if (climbType == 2){
            baselineIndex = Arrays.asList(boulderGradeArray).indexOf(getBaseLine());
        }
        return baselineIndex;
    }

    public static void workoutIntensityInput(){
        String workoutIntensityString = userInput.nextLine();
        if (workoutIntensityString.equals("1") || workoutIntensityString.equals("2")){
            workoutIntensity = Integer.parseInt(workoutIntensityString);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public static int getWorkoutIntensity(){
        return workoutIntensity;
    }

    // implemented when logging to log.txt file to print workout type
    public static String logWorkoutIntensity(){
        if (workoutIntensity == 1){
            return "Normal";
        } else if (workoutIntensity == 2){
            return "Full Send";
        }
        return "";
    }

    // implemented when logging to log.txt file to print climb type
    public static String logClimbType(){
        if (climbType == 1){
            return "Top-Rope";
        } else if (climbType == 2){
            return "Bouldering";
        }
        return "";
    }

    // after displaying workout to user & logging to file, this method will give the user an option to run through again(Y) or exit the program(N)
    public static void anotherWorkoutInput(){
        String response = userInput.nextLine();
        if (response.equalsIgnoreCase("Y")){
            ClimbingWorkout.run();
        } else if (response.equalsIgnoreCase("N")) {
            UserOutput.goodbyeMessage();
            System.exit(1);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
