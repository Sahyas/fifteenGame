import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {


    public static void main(String[] args) {
        FileManager file = new FileManager();
        String algorithm = "dfs"; //
        String order = "ULDR"; //args[1];
        String puzzleFile = "puzzles/4x4_03_00001.txt";
        String solutionFile = "solution.txt";
        String additionalFile = "additional.txt";
        int[][] solvedBoard =
                {
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
        Board board1 = new Board(puzzleFile);
        Board solution = null;
        Board goal = new Board(solvedBoard);
        BFS bfs = new BFS();
        DFS dfs = new DFS();
        ASTAR astar = new ASTAR();
        long start;
        long end;
        float elapsedTime = 0;
        switch(algorithm){
            case "bfs":
                start = System.currentTimeMillis();
                solution = bfs.bfs(board1, goal, order);
                end = System.currentTimeMillis();
                elapsedTime = end - start;
                break;
            case "dfs":
                start = System.currentTimeMillis();
                solution = dfs.dfs(board1, goal, order);
                end = System.currentTimeMillis();
                elapsedTime = end - start;
                break;
            case "astr":
                start = System.currentTimeMillis();
                solution = astar.astar(board1, goal, order);
                end = System.currentTimeMillis();
                elapsedTime = end - start;
                break;
            default:
                solution = board1;
        }
        if(solution != null) {
            System.out.print(Objects.requireNonNull(solution).solutionPath);
            solution.solutionSize = solution.solutionPath.length();
            file.saveSolution(solution.solutionPath, solution.solutionSize, solutionFile);
            file.saveAdditionalInfo(solution.solutionSize, solution.visitedStatesNumber,
                    solution.processedStatesNumber, solution.depth, elapsedTime, additionalFile);
        }
    }
}
