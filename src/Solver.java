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

    // Penyelesaian game 
    public static boolean fitBlocksToBoard(ArrayList<Block> remainBlocks, ArrayList<Block> fitBlocks, boolean[][] curBoard){
        
        if (isFull(curBoard)){
            if (remainBlocks.isEmpty()){
                //Solusi didapatkan
                solution = new ArrayList<>(fitBlocks);
                return true;
            } else {
                // System.out.println("cek1");
                return false;
            }
        } else if (remainBlocks.isEmpty()){
                // System.out.println("cek2");
                return false;
        } else {

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

                        boolean[][] newBoard = placeBlock(currCheckCoord.getY(), currCheckCoord.getX(), curBlockOrient, curBoard);

                        if (newBoard != null){

                            // System.out.println("fit.");
                            block.setLocation(currCheckCoord);
                            block.setRef(ref);
                            block.setPosition(curBlockOrient);

                            ArrayList<Block> newFitBlocks = new ArrayList<>(fitBlocks);
                            newFitBlocks.add(block);
                            ArrayList<Block> newRemainBlocks = new ArrayList<>(remainBlocks);
                            newRemainBlocks.remove(block);

                            if (fitBlocksToBoard(newRemainBlocks, newFitBlocks, newBoard)){
                                return true;
                            }
                        }
                    }
                }
            }
            // System.out.println("cek3");
            return false;
        }
    }

    public static boolean[][] placeBlock(int startY, int startX, ArrayList<Coordinate> position, boolean[][] board) {
        boolean[][] newBoard = copyBoard(board);
        for (Coordinate pos : position) {
            int row = startY + pos.getY();
            int col = startX + pos.getX();

            if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || newBoard[row][col]) {
                return null; // invalid placement
            }
            newBoard[row][col] = true;
        }
        return newBoard;
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
        }
        for (int i = 0; i < solBoard.length; i++){
            for (int j = 0; j < solBoard[0].length; j++){
                String color = Game.getColorFromLetter(game, solBoard[i][j]);
                if (solBoard[i][j] >= 'A' && solBoard[i][j] <= 'Z'){
                    System.out.print(color + solBoard[i][j] + Setup.colors[0]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
