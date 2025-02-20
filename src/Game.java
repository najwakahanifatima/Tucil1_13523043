import java.util.ArrayList;

public class Game {
    private static int rows;
    private static int cols;
    private static int numBlocks;
    private static String conf;
    private static ArrayList<Block> gameBlocks;

    public Game(int N, int M, int P, String S, ArrayList<Block> blocks){
        Game.rows = N;
        Game.cols = M;
        Game.numBlocks = P;
        Game.conf = S;
        Game.gameBlocks = blocks;
    }

    // public static int boardRows(){
    //     return Game.rows;
    // }

    // public static int boardCols(){
    //     return Game.cols;
    // }

    // public static int numBlocks(){
    //     return Game.numBlocks;
    // }

    // public static String conf(){
    //     return Game.conf;
    // }
}
