import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class ClimbingWorkout {

    public static void run() {

        //To-Do:
        // DONE --- UserInput & UserOutput handling all display and input from user
        // DONE --- write workout to a file, to showcase FileWriter
        // DONE --- options to calc another workout or exit
        // DONE --- include workout type, climb type, baseline details on log output
        // DONE - exception handling for input type checks
        // ----- # of routes Exception - DONE
        // ----- climb type - DONE
        // ----- workout type - DONE
        // ----- baseline input customer exception - DONE
        // DONE - w/ while loops - can try/catch return to specific menu? returns to home currently

        // DONE - exception handling for climbType, workout type, if not 1 or 2, throw error. ***Needed to throw exceptions in Methods
        // --- ask user for a file to log workout to
        // --- store grades in List vs Array, eliminate need to (Arrays.asList) in certain methods

        // --- Read in file with climbing grades (boulder.txt, topRope.txt) - comma separated values into an array

        //Map to hold climb# and route grade, will loop through later to display workout to the user.
        Map<Integer, String> workoutRoutes = new HashMap<>();


            while (true) {
            //Welcome statement, could include some instructions here? anything to greet user, to help positive user experience
            UserOutput.introDisplay();
                while (true) {
                //Asking for number of routes the user would like to climb, a route is one time up the wall. A "send" is the term for a completed climb.
                UserOutput.numberOfSendsPrompt();
                try {
                    //if user enters anything other than integer, will receive Invalid Input message and return to home
                    UserInput.numberOfSendsInput();
                } catch (IllegalArgumentException e) {
                    UserOutput.invalidInputMessage();
                    continue;}

                    while (true) {
                    //Type of Climbing: if User selects Top Rope, we'll utilize the top rope grading system, if selection is Bouldering, we'll use the boulder grades
                    UserOutput.climbTypeMenu();

                    try {
                        //if user enters anything other than 1 or 2, will receive Invalid Input message and return to home
                        UserInput.climbTypeInput();
                    } catch (IllegalArgumentException e) {
                        UserOutput.invalidInputMessage();
                        continue;}

                        while (true) {
                        //baseline is a current performance level, this baseline will serve as a reference point for the route grades assigned in workout
                        UserOutput.baseLinePrompt();
                        try {
                            //if user input is not within the current baseline specs in UserInput class, the baseLineInput() method throws custom exception
                            UserInput.baseLineInput();
                        } catch (InvalidGradeException e) {
                            System.out.println(e.getMessage());
                            continue;}

                            while (true) {
                                //Workout type. Starting with 2 options - more relaxed workout or a difficult workout (Full Send). Selection will determine deviation from baseline.
                                UserOutput.workoutIntensityMenu();
                                try {
                                    //if user enters anything other than 1 or 2, will receive Invalid Input message and return to home
                                    UserInput.workoutIntensityInput();
                                } catch (IllegalArgumentException e) {
                                    UserOutput.invalidInputMessage();
                                    continue;
                                }

                                if (UserInput.getWorkoutIntensity() == 1) {
                                    if (UserInput.getClimbType() == 1) {
                                        int climbNumber = 1;
                                        for (int i = 1; i <= UserInput.getRouteNumber(); i++) {/*Climbing grades are exponentially difficult, if user's baseline is among
                                              the first 2 grades, the workout can include -1 baseline at the easiest unless baseline is the lowest grade*/

                                            //if baseline is at index 0 of grades. Climbs will be baseline, baseline++ (avoids index out of bounds)
                                            if (UserInput.getBaseLineIndex() == 0) {
                                                int randomNumber = new Random().nextInt(2);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex()]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() + 1]);
                                                }
                                                climbNumber++;
                                                //if baseline is between index 1 and 3, grades will be baseline--, baseline, baseline++
                                            } else if (UserInput.getBaseLineIndex() <= 3) {
                                                int randomNumber = new Random().nextInt(3);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() - 1]);
                                                } else if (randomNumber == 1) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex()]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() + 1]);
                                                }
                                                climbNumber++;
                                                //if baseline is at last index of grade, workout will never go for baseline + 1, to avoid index out of bounds.
                                            } else if (UserInput.getBaseLineIndex() == UserInput.topRopeGradeArray.length - 1) {
                                                int randomNumber = new Random().nextInt(3);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() - 2]);
                                                } else if (randomNumber == 1) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() - 1]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex()]);
                                                }
                                                climbNumber++;
                                            } else {
                                                //workout will include grades: baseline-2, baseline--, baseline, baseline++
                                                int randomNumber = new Random().nextInt(4);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() - 2]);
                                                } else if (randomNumber == 1) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() - 1]);
                                                } else if (randomNumber == 2) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex()]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() + 1]);
                                                }
                                                climbNumber++;
                                            }
                                        }
                                    } else if (UserInput.getClimbType() == 2) {
                                        int climbNumber = 1;
                                        for (int i = 1; i <= UserInput.getRouteNumber(); i++) {/*Climbing grades are exponentially difficult, if user's baseline is among
                                              the first 2 grades, the workout can include -1 baseline at the easiest unless baseline is the lowest grade*/

                                            //if baseline is at index 0 of grades. Climbs will be baseline, baseline++ (avoids index out of bounds)
                                            if (UserInput.getBaseLineIndex() == 0) {
                                                int randomNumber = new Random().nextInt(2);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex()]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() + 1]);
                                                }
                                                climbNumber++;
                                                //if baseline is between index 1 and 3, grades will be baseline--, baseline, baseline++
                                            } else if (UserInput.getBaseLineIndex() <= 3) {
                                                int randomNumber = new Random().nextInt(3);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() - 1]);
                                                } else if (randomNumber == 1) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex()]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() + 1]);
                                                }
                                                climbNumber++;
                                                //if baseline is at last index of grade, workout will never go for baseline + 1, to avoid index out of bounds.
                                            } else if (UserInput.getBaseLineIndex() == (UserInput.boulderGradeArray.length - 1)) {
                                                int randomNumber = new Random().nextInt(3);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() - 2]);
                                                } else if (randomNumber == 1) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() - 1]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex()]);
                                                }
                                                climbNumber++;
                                            } else {
                                                //workout will include grades: baseline-2, baseline--, baseline, baseline++
                                                int randomNumber = new Random().nextInt(4);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() - 2]);
                                                } else if (randomNumber == 1) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() - 1]);
                                                } else if (randomNumber == 2) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex()]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() + 1]);
                                                }
                                                climbNumber++;
                                            }
                                        }
                                    }
                                } else if (UserInput.getWorkoutIntensity() == 2) {
                                    if (UserInput.getClimbType() == 1) {
                                        int climbNumber = 1;
                                        for (int i = 1; i <= UserInput.getRouteNumber(); i++) {
                                            //if baseline is at last index of grade, workout will never go for baseline + 1, to avoid index out of bounds.
                                            if (UserInput.getBaseLineIndex() == UserInput.topRopeGradeArray.length - 1) {
                                                workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex()]);
                                                climbNumber++;
                                                //if baseline is at grade array length - 2 , workout will never go for baseline + 2, to avoid index out of bounds.
                                            } else if (UserInput.getBaseLineIndex() == (UserInput.topRopeGradeArray.length - 2)) {
                                                int randomNumber = new Random().nextInt(2);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex()]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() + 1]);
                                                }
                                                climbNumber++;
                                            } else {
                                                //workout will include grades: baseline, baseline++, baseline+2
                                                int randomNumber = new Random().nextInt(3);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex()]);
                                                } else if (randomNumber == 1) {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() + 1]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.topRopeGradeArray[UserInput.getBaseLineIndex() + 2]);
                                                }
                                                climbNumber++;
                                            }
                                        }
                                    } else if (UserInput.getClimbType() == 2) {
                                        int climbNumber = 1;
                                        for (int i = 1; i <= UserInput.getRouteNumber(); i++) {
                                            //if baseline is at last index of grade, workout will never go for baseline + 1, to avoid index out of bounds.
                                            if (UserInput.getBaseLineIndex() == UserInput.boulderGradeArray.length - 1) {
                                                workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex()]);
                                                climbNumber++;
                                                //if baseline is at grade array length - 2 , workout will never go for baseline + 2, to avoid index out of bounds.
                                            } else if (UserInput.getBaseLineIndex() >= (UserInput.boulderGradeArray.length - 2)) {
                                                int randomNumber = new Random().nextInt(2);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex()]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() + 1]);
                                                }
                                                climbNumber++;
                                            } else {
                                                //workout will include grades: baseline, baseline++, baseline+2
                                                int randomNumber = new Random().nextInt(3);
                                                if (randomNumber == 0) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex()]);
                                                } else if (randomNumber == 1) {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() + 1]);
                                                } else {
                                                    workoutRoutes.put(climbNumber, UserInput.boulderGradeArray[UserInput.getBaseLineIndex() + 2]);
                                                }
                                                climbNumber++;
                                            }
                                        }
                                    }
                                }
                                //print workout w/ current date to existing file log.txt
                                try (PrintWriter workoutOutput = new PrintWriter(new FileOutputStream("log.txt", true))) {
                                    UserOutput.outroDisplay();
                                    workoutOutput.println("\nToday's Date: " + LocalDate.now() + "\n" + UserInput.logClimbType() + "\nBaseline: " + UserInput.getBaseLine() + "\nWorkout Type: " + UserInput.logWorkoutIntensity() + "\nYour workout: ");
                                    for (Map.Entry<Integer, String> entry : workoutRoutes.entrySet()) {
                                        System.out.printf("\t\t\t  " + UserOutput.workoutPrintFormat(), entry.getKey(), entry.getValue());
                                        workoutOutput.printf(UserOutput.workoutPrintFormat(), entry.getKey(), entry.getValue());
                                    }
                                } catch (FileNotFoundException e) {
                                    System.out.println("File not found");
                                }
                                while (true) {
                                    //prompt user to calculate another workout, if Yes, calls to re-run program. If No, exits program w/ a goodbye message.
                                    UserOutput.anotherWorkoutPrompt();
                                    try {
                                        UserInput.anotherWorkoutInput();
                                    } catch (IllegalArgumentException e) {
                                        UserOutput.invalidInputMessage();
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }













