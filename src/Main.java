import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Welcoming to Program
        System.out.println("\u001B[46m" + "Welcome to IQ Puzzle Pro Solver!" + "\u001B[0m");
        
        String filename;
        try (Scanner scanner = new Scanner(System.in)) {
            // Input File Name
            System.out.println("\nPlease make sure the file is located in test/input folder.");
            System.out.print("Enter file name (without .txt): ");
            filename = scanner.nextLine();

            Game game = Setup.gameSetup(filename);

            if (game != null){

                // Solver.checkBoard(Solver.board);

                Instant start = Instant.now();

                if (Solver.fitBlocksToBoard(game.gameBlocks(), new ArrayList<>(), Solver.board)){
                    System.out.println("\nSucceed.\n");
                    Solver.showSolution(game, Solver.solution);
                    Instant end = Instant.now();
                    Duration duration = Duration.between(start, end);

                    // Print durasi pencarian
                    System.out.println("\nCases reviewed: " + Solver.counter + " cases.");
                    System.out.println("Duration: " + duration.toMillis() + "ms.");

                    // Save solution option
                    System.out.print("\nDo you want to save the solution? (yes/no): ");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equals("yes")){
                        System.out.print("Input file name (without .txt): ");
                        String fileOutput = scanner.nextLine();
                        Setup.saveSolution(fileOutput, duration);
                    }
                } else {
                    System.err.println("\nSolution not found.");
                }
            }
        }

        System.out.println("\nThank you for using IQ Puzzler Pro Solving Program!");
    }
}
