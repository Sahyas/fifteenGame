import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static String INITIAL_FILE = "uklad_poczatkowy.txt";

    public static void main(String[] args) throws FileNotFoundException {

        //LOAD INITIAL FILE
        File file = new File(INITIAL_FILE);
        Scanner read = new Scanner(new File(INITIAL_FILE));
        int ROW = read.nextInt();
        int COLUMN = read.nextInt();

        //MAKE BOARD
        int[][] gameBoard = new int[ROW][COLUMN];
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COLUMN; j++){
                gameBoard[i][j] = read.nextInt();
            }
        }
        printBoard(gameBoard);

    }

    public static void printBoard(int[][] board) {
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                System.out.print(board[i][j]);
                if(board[i][j] < 10){
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }
}
