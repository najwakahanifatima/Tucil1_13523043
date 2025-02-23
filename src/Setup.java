import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class Setup {
    public static final String[] colors = {
        "\u001B[0m",  // Reset
        "\u001B[31m", // Red
        "\u001B[32m", // Green
        "\u001B[33m", // Yellow
        "\u001B[34m", // Blue
        "\u001B[35m", // Magenta
        "\u001B[36m", // Cyan
        "\u001B[37m", // White
        "\u001B[91m", // Bright Red
        "\u001B[92m", // Bright Green
        "\u001B[93m", // Bright Yellow
        "\u001B[94m", // Bright Blue
        "\u001B[95m", // Bright Magenta
        "\u001B[96m", // Bright Cyan
        "\u001B[97m", // Bright White
        "\u001B[41m", // Red Background
        "\u001B[42m", // Green Background
        "\u001B[43m", // Yellow Background
        "\u001B[44m", // Blue Background
        "\u001B[45m", // Magenta Background
        "\u001B[46m", // Cyan Background
        "\u001B[47m", // White Background
        "\u001B[101m", // Bright Red Background
        "\u001B[102m", // Bright Green Background
        "\u001B[103m", // Bright Yellow Background
        "\u001B[104m", // Bright Blue Background
        "\u001B[105m" // Bright Magenta Background
    };

    public static boolean isPieceLetterValid(char character){
        return ((character >= 'A' && character <= 'Z') || character == ' ');
    }

    public static boolean isLetterBlockInList(ArrayList<Block> blocks, char letter){
        int i = 0;
        while (i < blocks.size() - 1) { //kecuali last blocks.
            if (letter == blocks.get(i).getLetter()){
                return true;
            }
            i++;
        }
        return  false; // false letter belum ada dalam list block
    }

    public static boolean isIdentical(char[] chars){
        char letter = findFirstLetter(chars);
        int idx = 0;
        while (idx < chars.length){
            if (chars[idx] != letter && chars[idx] != ' '){
                return false;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
            }
            idx++;
        }
        return true;
    }

    public static char findFirstLetter(char[] chars){
        int i = 0;
        while (i < chars.length){
            if (chars[i] != ' '){
                return chars[i];
            }
            i++;
        }
        return '.';
    }

    public static Game gameSetup(String filename){
       try {
        String filepath = "../test/input/";
        String path = filepath.concat(filename);
        String pathname = path.concat(".txt");
        File input = new File(pathname);
        
           try (Scanner scanner = new Scanner(input)) {
                // Success in reading input.txt game nya
                int N, M, P;
                String S;
                ArrayList<Block> listBlocks = new ArrayList<>();

                // Read N M P
                String data = scanner.nextLine();
                String[] splitted = data.split(" ");
                if (splitted.length == 3) {
                    N = Integer.parseInt(splitted[0]);
                    M = Integer.parseInt(splitted[1]);
                    P = Integer.parseInt(splitted[2]);
                } else {
                    System.out.println("Input is incomplete!");
                    return null;
                }

                // Read S (conf)
                S = scanner.nextLine();
                switch (S) {
                    case "DEFAULT" -> {
                        // Default
                    }
                    case "CUSTOM" -> {
                        // Algo read custom matrix
                    }
                    case "PYRAMID" -> {
                        System.out.println("Program is currently unavailable for PYRAMID.");
                        return null;
                    }
                    default -> {
                        // Case conf tidak ada opsi
                        System.out.println("Configuration doesn't exist.");
                        return null;
                    } 
                }

                if (!scanner.hasNextLine()){
                    System.out.println("Input is incomplete!");
                    return null;
                }

                int counterBlock = 0, x, y = 0;
                char prevLetter = 0;
                
                while (scanner.hasNextLine()) {
                    x = 0; //every new line reset x
                    data = scanner.nextLine();
                    char[] chars = data.toCharArray(); //convert string read "data" to array of char
                    if (!isIdentical(chars) || isLetterBlockInList(listBlocks, chars[0])){
                        System.out.println("Blocks are invalid.");
                        return null;
                    } else if (findFirstLetter(chars) == prevLetter) {
                        // satu line identical dan sama kyk huruf line sebelumnya
                        for (int q = 0; q < data.length(); q++){
                            if (!isPieceLetterValid(chars[q])){
                                System.out.println("Piece letter is invalid.");
                                return null;
                            }
                            if (chars[q] == prevLetter) {
                                Block.addBlockInitPosition(listBlocks.get(counterBlock-1), x, y);  
                            }
                            x++;
                        }
                    } else {
                        // satu line identical dan blm ada di list -> block baru, reset y, counterblock + 1
                        y = 0; counterBlock++;
                        Block block = new Block(findFirstLetter(chars), colors[counterBlock]); // ganti warnaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                        for (int p = 0; p < data.length(); p++){
                            if (chars[p] == findFirstLetter(chars)) {
                               block.addInitPosition(x, y);
                            }
                            x++; 
                        }
                        listBlocks.add(block);
                    }
                    y++;
                    prevLetter = findFirstLetter(chars);
                }

                // System.out.println("N: " + N);
                // System.out.println("M: " + M);
                // System.out.println("P: " + P);
                // System.out.println("S: " + S);
                // System.out.println("Blocks: ");
                // for (Block block : listBlocks){
                //     System.out.print(block.getLetter());
                //     Block.showShape(block.getPosition());
                //     System.out.println();
                // }

                if (counterBlock != P){
                    System.out.println("Number of blocks is not equivalent to input P.");
                }

                Game game = new Game(N, M, P, S, listBlocks);

                scanner.close();

                return game;
            }
       } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            // e.printStackTrace();
       }
       return null;
    }

    public static void saveSolution(String outputFilename, Duration duration){
        
        char[][] solutionBoard = new char[Solver.board.length][Solver.board[0].length];

        for (Block block : Solver.solution) {
            int x = block.getLocation().getX();
            int y = block.getLocation().getY();
            for (Coordinate pos : block.getPosition()) {
                solutionBoard[y + pos.getY()][x + pos.getX()] = block.getLetter();
            }
        }

        String filepath = "../test/output/";
        String path = filepath.concat(outputFilename);
        String pathname = path.concat(".txt");

        try {
            File output = new File(pathname);
            if (output.createNewFile()){
                try {
                    // printing solution di file output
                    FileWriter writer = new FileWriter(pathname);
                    writer.write("Solution:\n");

                    writeHorizontalLine(solutionBoard[0].length, writer);
                    for (char[] row : solutionBoard) {
                        writer.write("|");
                        for (char cell : row) {
                            writer.write(" " + cell + " |");
                        }
                        writer.write("\n");
                        writeHorizontalLine(row.length, writer);
                    }

                    writer.write("\nBlocks in solution:");
                    for (Block block : Solver.solution) {
                        writer.write("\nBlock letter: " + block.getLetter() + " at position: (" + block.getLocation().getX() + "," + block.getLocation().getY() + ")");
                    }

                    writer.write("\n");
                    writer.write("\nCases reviewed: " + Solver.counter + " cases.");
                    writer.write("\nDuration: " + duration.toMillis() + "ms.");

                    writer.close();
                    System.out.println("\nSolution successfully saved as " + outputFilename + ".txt in test/output.");
                } catch (IOException e) {
                    System.out.println("An error occured in writing solution in file.");
                }
            } else {
                System.out.println("File already exist. Save solution failed.");
            }
        } catch (IOException e){
            System.out.println("An error occured in creating file.");
        }

    }

    private static void writeHorizontalLine(int width, FileWriter writer) {
        try {
            writer.write("+");
            for (int i = 0; i < width; i++) {
                writer.write("---+");
            }
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("An error occured.");
        }
    }

    // public static void main(String[] args) {
    //     gameSetup("input.txt");
    // }
}
