import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileProcessing {
    public static boolean isLetterBlockInList(ArrayList<Block> blocks, char letter){
        int i = 0;
        while (i < blocks.size()) {
            if (letter == blocks.get(i).getLetter()){
                return true;
            }
            i++;
        }
        return  false; // true letter sudah ada dalam list block
    }

    public static boolean isIdentical(char[] chars){
        char letter = chars[0];
        int idx = 0;
        while (idx < chars.length){
            if (chars[idx] != letter && chars[idx] != ' '){
                return false;
            }
            idx++;
        }
        return true;
    }

    public static boolean gameSetup(String filename){
       try {
        String filepath = "../test/input/";
        String pathname = filepath.concat(filename);
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
                    return false;
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
                        return false;
                    }
                    default -> {
                        // Case conf tidak ada opsi
                        System.out.println("Configuration doesn't exist.");
                        return false;
                    } 
                }

                if (!scanner.hasNextLine()){
                    System.out.println("Input is incomplete!");
                }

                data = scanner.nextLine();
                // Read blocks, iterate dari 1 sampai P
                for (int k = 1; k <= P; k++) {
                    // Setup initial koordinat and block
                    int x, y = 0;
                    Block block = new Block(data.charAt(0), "white"); // CHANGE COLOR NYA
                    // iterate sampai next line first char udh bukan yg sama
                    do {
                        x = 0;
                        char[] chars = data.toCharArray();
                        if (!isIdentical(chars) || isLetterBlockInList(listBlocks, chars[0])){
                            System.out.println("Blocks are not valid.");
                            return false;
                        }
                        for (char c : chars){
                            if (c == chars[0]){
                                block.addInitPosition(x, y);
                            }
                            x++;
                        }
                        y++;
                    } while (scanner.hasNextLine());
                }

                System.out.println(N);
                System.out.println(M);
                System.out.println(P);
                System.out.println(S);
                System.out.println(data);
                System.out.println(data.length());

                // while (scanner.hasNextLine()){
                //     String data = scanner.nextLine();
                //     System.out.println(data);
                return true;
            }
       } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            // e.printStackTrace();
       }
       return false;
    }

    public static void main(String[] args) {
        gameSetup("input.txt");
    }
}
