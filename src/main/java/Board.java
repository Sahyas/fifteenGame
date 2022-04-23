import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {

    private int[][] gameBoard;

    private int[] zero;

    private FileManager fileManager = new FileManager();

    public Board() {
        try {
            int counter = 2;
            int[] tmp = fileManager.initialFile();
            gameBoard = new int[tmp[0]][tmp[1]];
            for(int i = 0; i < tmp[0]; i++){
                for(int j = 0; j < tmp[1]; j++){
                    gameBoard[i][j] = tmp[counter];
                    counter++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getBoardNumber(int x, int y) {
        return gameBoard[x][y];
    }

    public void setGameBoard(int x, int y, int value) {
        this.gameBoard[x][y] = value;
    }

    public void printBoard(){
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                System.out.print(getBoardNumber(i, j));
                if(getBoardNumber(i, j)<10){
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    public void move(char direction) {
        int x0 = 0;
        int y0 = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == 0) {
                    x0 = i;
                    y0 = j;
                }
            }
        }
        switch (direction) {
            case 'L':
                if (y0 != 0) {
                    setGameBoard(x0, y0, gameBoard[x0][y0 - 1]);
                    setGameBoard(x0, y0 - 1, 0);
                }
                break;
            case 'R':
                if (y0 != gameBoard.length) {
                    setGameBoard(x0, y0, gameBoard[x0][y0 + 1]);
                    setGameBoard(x0, y0 + 1, 0);
                }
                break;
            case 'U':
                if (x0 != 0) {
                    setGameBoard(x0, y0, gameBoard[x0 - 1][y0]);
                    setGameBoard(x0 - 1, y0, 0);
                }
                break;
            case 'D':
                if (x0 != gameBoard.length) {
                    setGameBoard(x0, y0, gameBoard[x0 + 1][y0]);
                    setGameBoard(x0 + 1, y0, 0);
                }
                break;
        }
    }
}
