import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    List<Integer> random = new ArrayList<>();

    private int[][] gameBoard = new int[4][4];

    public int getBoardNumber(int x, int y) {
        return gameBoard[x][y];
    }

    public void setGameBoard(int x, int y, int value) {
        gameBoard[x][y] = value;
    }

    public Board(){
        int counter = 0;
        rng();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                setGameBoard(i, j, random.get(counter));
                counter++;
            }
        }
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

    public void rng(){
        for (int i = 0; i < 16; i++) {
            random.add(i);
        }
        Collections.shuffle(random);
    }
}

