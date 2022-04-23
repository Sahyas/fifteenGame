import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class App {


    public static void main(String[] args) {
        int[][] solvedBoard =
                {
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
        Board board1 = new Board();
        Board goal = new Board(solvedBoard);
        BFS bfs = new BFS();

        if(bfs.search(board1, goal)){
            System.out.print("sukces\n");
        }
    }
}
