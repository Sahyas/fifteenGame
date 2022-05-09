

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class App {


    public static void main(String[] args) {
        FileManager file = new FileManager();

        String algorithm = "bfs";
        String order = "LURD";
        String puzzleFile = "puzzles/4x4_02_00002.txt";
        String solutionFile = "solution.txt";
        String additionalFile = "additional.txt";

        /*String algorithm = args[0];
        String order = args[1];
        String puzzleFile = args[2];
        String solutionFile = args[3];
        String additionalFile = args[4];*/

        int[][] solvedBoard =
                {
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
        Board board1 = new Board(puzzleFile);
        Board solution;
        Board goal = new Board(solvedBoard);
        BFS bfs = new BFS();
        DFS dfs = new DFS();
        ASTAR astar = new ASTAR();
        BigDecimal start = BigDecimal.valueOf(0);
        BigDecimal end = BigDecimal.valueOf(0);
        BigDecimal elapsedTime;
        switch(algorithm){
            case "bfs":
                start = BigDecimal.valueOf(System.nanoTime());
                solution = bfs.bfs3(board1, goal, order);
                end = BigDecimal.valueOf(System.nanoTime());
                break;
            case "dfs":
                start = BigDecimal.valueOf(System.nanoTime());
                solution = dfs.dfs(board1, goal, order);
                end = BigDecimal.valueOf(System.nanoTime());
                break;
            case "astr":
                start = BigDecimal.valueOf(System.nanoTime());
                solution = astar.astar(board1, goal, order);
                end = BigDecimal.valueOf(System.nanoTime());
                break;
            default:
                solution = board1;
        }
        elapsedTime = end.subtract(start);
        elapsedTime = elapsedTime.divide(BigDecimal.valueOf(1000000));

        if(!"dfs".equals(algorithm)){
            solution.solutionSize = solution.solutionPath.length();
        }
            file.saveSolution(solution.solutionPath, solution.solutionSize, solutionFile);
            file.saveAdditionalInfo(solution.solutionSize, solution.visitedStatesNumber,
                    solution.processedStatesNumber, solution.depth, elapsedTime, additionalFile);
    }
}
