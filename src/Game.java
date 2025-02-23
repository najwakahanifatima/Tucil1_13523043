import java.util.ArrayList;

public class Game {
    private int rows;
    private int cols;
    private int numBlocks;
    private String conf;
    private ArrayList<Block> gameBlocks;

    public Game(int N, int M, int P, String S, ArrayList<Block> blocks){
        rows = N;
        cols = M;
        numBlocks = P;
        conf = S;
        gameBlocks = blocks;
    }

    public int boardRows(){
        return rows;
    }

    public int boardCols(){
        return cols;
    }

    public int numBlocks(){
        return numBlocks;
    }

    public String conf(){
        return conf;
    }

    public ArrayList<Block> gameBlocks(){
        return gameBlocks;
    }
}
