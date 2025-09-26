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

    //Arrays to hold grades for both Top Rope and Bouldering.
    //Grade is level of difficulty, sorted in ascending order from easy to more difficult
    //Arrays are built using File I/o in GeneratorCLI class
    public static String[] topRopeGradeArray;
    public static String[] boulderGradeArray;

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
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static int getClimbType(){
        return climbType;
    }

    // this method will take user's input for their baseline grade, check if that grade is 1) valid and 2) that grade exists in current range, if not, throws custom exception InvalidGradeException
    // GS 9/25/25 - added functionality where user can see a full list of current baseline options by entering 'help'
    public static void baseLineInput() throws InvalidGradeException {
        String baselineInput = userInput.nextLine();
        if (climbType == 1) {
            List<String> topRopeList = new ArrayList<>(Arrays.asList(topRopeGradeArray));
            if (baselineInput.equals("help")) {
                baselineInput = getHelpWithBaselineChoice(climbType).toLowerCase();
                if (!topRopeList.contains(baselineInput.toLowerCase())){
                    throw new InvalidGradeException("\n\tYou must enter a valid top-rope grade! Try again.");
                }
            } else if (topRopeList.contains(baselineInput.toLowerCase())){
                baselineInput = baselineInput.toLowerCase();
            } else {
                throw new InvalidGradeException("\n\tYou must enter a valid top-rope grade! Try again.");
            }
        }
        else if (climbType == 2) {
            List<String> boulderList = new ArrayList<>(Arrays.asList(boulderGradeArray));
            if (baselineInput.equals("help")) {
                baselineInput = getHelpWithBaselineChoice(climbType).toUpperCase();
                if (!boulderList.contains(baselineInput.toUpperCase())){
                    throw new InvalidGradeException("\n\tYou must enter a valid Boulder grade! Try again.");
                }
            } else if (boulderList.contains(baselineInput.toUpperCase())){
                baselineInput = baselineInput.toUpperCase();
            } else {
                throw new InvalidGradeException("\n\tYou must enter a valid Boulder grade! Try again.");
            }
        }
        baseLineValue = baselineInput;
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

    // based on climbType (top-rope or boulder), help the user by displaying a list of current baseline options
    private static String getHelpWithBaselineChoice(int climbType) {
        System.out.println("\nChoose from the following baseline options:\n");
        if (climbType == 1) {
            for (String each : topRopeGradeArray) {
                System.out.println(each);
            }
        } else {
            for (String each : boulderGradeArray) {
                System.out.println(each);
            }
        }

        System.out.print("\nReady to roll? Enter baseline here: ");
        return userInput.nextLine();
    }
}
