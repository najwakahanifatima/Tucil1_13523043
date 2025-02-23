import java.util.ArrayList;

public class Solver {
    public static ArrayList<Block> solution; //storing blocks that fit (sudah disesuaikan dg orientasinya)
    public static boolean[][] board; //default value is false
    public static int counter = 0;

    // Check if board is full
    public static boolean isFull(boolean[][] p){
        for (boolean[] p1 : p) {
            for (int j = 0; j < p[0].length; j++){
                if (p1[j] == false) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check the top empty space left -- current coordinate
    public static Coordinate curCoord(boolean[][] b){
        for (int i = 0; i < b.length; i++){
            for (int j = 0; j < b[0].length; j++){
                if (b[i][j] == false){
                    return new Coordinate(j, i);
                }
            }
        }
        return null;
    }

    // Check if block is fit to the  board
    public static boolean isFit(Coordinate currentCoord, ArrayList<Coordinate> position, boolean[][] p){
        for (Coordinate pos : position) {
            int rowY = currentCoord.getY() + pos.getY();
            int colX = currentCoord.getX() + pos.getX();
            // Not fit ketika melewati batas baris/kolom papan atau jika sudah terisi (true)
            if (rowY < 0 || colX < 0 || rowY >= p.length || colX >= p[0].length || p[rowY][colX] == true ){
                return false;
            }
        }
        return true;
    }

    // Penyelesaian game secara rekursif
    public static boolean fitBlocksToBoard(ArrayList<Block> remainBlocks, ArrayList<Block> fitBlocks, boolean[][] curBoard){
        // Basis
        if (isFull(curBoard)){
            if (remainBlocks.isEmpty()){
                //Solusi didapatkan
                solution = new ArrayList<>(fitBlocks);
                return true;
            } else {
                System.out.println("Solution is not found: board is full and there are still remaining blocks.");
                return false;
            }
        } else if (remainBlocks.isEmpty()){
                System.out.println("Solution is not found: blocks are not enough to fill up the board.");
                return false;
        } else {

            // Rekurens ----- 
            Coordinate currCheckCoord = curCoord(curBoard);
            // System.out.println("x " + currCheckCoord.getX() + " y " + currCheckCoord.getY());
            for (int i = 0; i < remainBlocks.size(); i++){
                Block block = new Block(remainBlocks.get(i));

                // for each block, try each element as a ref
                for (int ref = 0 ; ref < block.getPosition().size() ; ref++){
                    // for each element as ref, try every orientation
                    for (int or = 1; or <= 8; or++) {
                        ArrayList<Coordinate> curBlockOrient = Block.changeOrientation(block.getPosition(), ref, or);
                        counter++;
                        // if the orientation fit
                        if (isFit(currCheckCoord, curBlockOrient, curBoard)){

                            // System.out.println("fit.");

                            boolean[][] newBoard = updateBoard(currCheckCoord, curBlockOrient, curBoard);
                            block.setLocation(currCheckCoord);
                            block.setRef(ref);
                            block.setPosition(curBlockOrient);

                            ArrayList<Block> newFitBlocks = new ArrayList<>(fitBlocks);
                            newFitBlocks.add(block);
                            ArrayList<Block> newRemainBlocks = new ArrayList<>(remainBlocks);
                            newRemainBlocks.remove(block);

                            //print remaining blocks
                            // System.out.print("Remaining blocks: ");
                            // for (Block bl : newRemainBlocks){
                            //     System.out.print(bl.getLetter());
                            // }

                            // System.out.println("");

                            // System.out.print("Fit blocks: ");
                            // for (Block bl : newFitBlocks){
                            //     System.out.print(bl.getLetter());
                            // }

                            // System.out.println("");

                            // checkBoard(newBoard);

                            // check the remaining blocks
                            // System.out.print("cek");
                            if (fitBlocksToBoard(newRemainBlocks, newFitBlocks, newBoard)){
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
}

    public static boolean[][] copyBoard(boolean[][] b){
        boolean[][] newBoard = new boolean[b.length][b[0].length];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++){
                newBoard[i][j] = b[i][j];
            }
        }
        return newBoard;
    }

    // Update board when block is fit
    public static boolean[][] updateBoard(Coordinate currentCoord, ArrayList<Coordinate> position, boolean[][]b){
        boolean[][] updated = copyBoard(b);
        for (Coordinate pos : position){
            int row = currentCoord.getY() + pos.getY();
            int col = currentCoord.getX() + pos.getX();
            updated[row][col] = true;
        }
        return updated;
    }

    public static void checkBoard(boolean[][] b){
        for (int i = 0; i < b.length; i++){
            for (int j = 0; j < b[0].length; j++){
                if (b[i][j]) {
                    System.out.print("T");
                } else {
                    System.out.print("F");
                }
            }
            System.out.println();
        }
    }

    public static void showSolution(Game game, ArrayList<Block> solution){
        char[][] solBoard = new char[board.length][board[0].length];
        for (Block block : solution){
            int locX = block.getLocation().getX();
            int locY = block.getLocation().getY();
            for (Coordinate pos : block.getPosition()){
                solBoard[pos.getY() + locY][pos.getX() + locX] = block.getLetter();
            }
            // System.out.print(block.getLetter());
            // Block.showShape(block.getPosition());
            // System.out.println();
        }
        for (int i = 0; i < solBoard.length; i++){
            for (int j = 0; j < solBoard[0].length; j++){
                String color = Game.getColorFromLetter(game, solBoard[i][j]);
                System.out.print(color + solBoard[i][j] + Setup.colors[0]);
            }
            System.out.println();
        }
    }
}
