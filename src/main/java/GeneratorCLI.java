import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneratorCLI {
    public static File topRopeInput = new File("TopRopeGrades.txt");
    public static File boulderInput = new File("BoulderGrades.txt");


    public static void main(String[] args) {

        //Load Grade Arrays with data from Txt files
        try (Scanner topRopeReader = new Scanner(topRopeInput);
             Scanner boulderReader = new Scanner(boulderInput)){
            while(topRopeReader.hasNext()){
                String lineOfInput = topRopeReader.nextLine();
                UserInput.topRopeGradeArray = lineOfInput.split(",");
            }
            while(boulderReader.hasNext()){
                String lineOfInput = boulderReader.nextLine();
                UserInput.boulderGradeArray = lineOfInput.split(",");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //Runs application
        ClimbingWorkout.run();
    }
}
