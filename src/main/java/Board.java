import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {

    private int[][] gameBoard;

    private int[] zero = new int[2];

    private FileManager fileManager = new FileManager();

    public Board() {
        try {
            //INITIALIZE BOARD AND VALUES FROM FILE
            int counter = 2;
            int[] tmp = fileManager.initialFile();
            gameBoard = new int[tmp[0]][tmp[1]];
            for(int i = 0; i < tmp[0]; i++){
                for(int j = 0; j < tmp[1]; j++){
                    gameBoard[i][j] = tmp[counter];
                    counter++;
                }
            }
            //FIND ZERO PLACE
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard.length; j++) {
                    if (gameBoard[i][j] == 0) {
                        zero[0] = i;
                        zero[1] = j;
                    }
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

    public void setZero(int index, int value) {
        this.zero[index] = value;
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
        switch (direction) {
            case 'L':
                if (zero[1] != 0) {
                    setGameBoard(zero[0], zero[1], gameBoard[zero[0]][zero[1] - 1]);
                    setGameBoard(zero[0], zero[1] - 1, 0);
                    setZero(1,zero[1] - 1);
                }
                break;
            case 'R':
                if (zero[1] != gameBoard.length) {
                    setGameBoard(zero[0], zero[1], gameBoard[zero[0]][zero[1] + 1]);
                    setGameBoard(zero[0], zero[1] + 1, 0);
                    setZero(1,zero[1] + 1);
                }
                break;
            case 'U':
                if (zero[0] != 0) {
                    setGameBoard(zero[0], zero[1], gameBoard[zero[0] - 1][zero[1]]);
                    setGameBoard(zero[0] - 1, zero[1], 0);
                    setZero(0,zero[0] - 1);
                }
                break;
            case 'D':
                if (zero[0] != gameBoard.length) {
                    setGameBoard(zero[0], zero[1], gameBoard[zero[0] + 1][zero[1]]);
                    setGameBoard(zero[0] + 1, zero[1], 0);
                    setZero(0,zero[0] + 1);
                }
                break;
        }
    }
}
