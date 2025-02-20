import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Solver.board = new boolean[5][5];

        Block A = new Block('A', "1");
        A.addInitPosition(0, 0);
        A.addInitPosition(0, 1);
        A.addInitPosition(1, 1);
        Block B = new Block('B', "1");
        B.addInitPosition(0, 0);
        B.addInitPosition(0, 1);
        B.addInitPosition(1, 1);
        Block C = new Block('C', "1");
        C.addInitPosition(0, 0);
        C.addInitPosition(0, 1);
        C.addInitPosition(1, 1);
        Block D = new Block('D', "1");
        D.addInitPosition(0, 0);
        D.addInitPosition(0, 1);
        D.addInitPosition(1, 1);
        Block E = new Block('E', "1");
        E.addInitPosition(0, 0);
        E.addInitPosition(1, 0);
        E.addInitPosition(0, 1);
        E.addInitPosition(1, 1);
        E.addInitPosition(0, 2);
        Block F = new Block('F', "1");
        F.addInitPosition(0, 0);
        F.addInitPosition(1, 0);
        F.addInitPosition(0, 1);
        F.addInitPosition(1, 1);
        F.addInitPosition(0, 2);
        Block G = new Block('G', "1");
        G.addInitPosition(0, 0);
        G.addInitPosition(1, 0);
        G.addInitPosition(2, 0);

        ArrayList<Block> remainBlocks = new ArrayList<>();
        remainBlocks.add(A);
        remainBlocks.add(B);
        remainBlocks.add(C);
        remainBlocks.add(D);
        remainBlocks.add(E);
        remainBlocks.add(F);
        remainBlocks.add(G);

        Instant start = Instant.now();

        if (Solver.fitBlocksToBoard(remainBlocks, new ArrayList<>(), Solver.board)){
            System.out.println("Berhasil.");
            Solver.showSolution(Solver.solution);
        } else {
            System.err.println("Gagal.");
        }

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);

        // Print durasi pencarian
        System.out.println("Duration: " + duration.toMillis() + "ms.");

        
        // if (isFit(new Coordinate(0,0), Block.changeOrientation(E.getPosition(), 3, 4), board)) {
        //     System.out.println("Success.");
        //     boolean[][] newB = updateBoard(new Coordinate(0,0), Block.changeOrientation(E.getPosition(), 3, 4), board);
        //     showBoard(newB);
        // } else {
        //     System.err.println("Not matched.");
        // }


        // for (int i = 0; i < remainBlocks.size(); i++){
        //     Block block = remainBlocks.get(i);
        //     Block.showShape(block.getPosition());
        //     System.err.println("");
        //     Block.showShape(Block.changeOrientation(block.getPosition(), 1, 2));
        //     System.err.println("");
        //     System.err.println("");
        // }

    }
}
