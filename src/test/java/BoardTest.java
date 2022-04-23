import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;


public class BoardTest {
    private Board boardTest;

    @Before
    public void setUpTest() {
        boardTest = new Board();
    }

    @Test
    public void setGameBoardTest() {
        assertNotEquals(boardTest.getBoardNumber(0, 0), 2);
        boardTest.setGameBoard(0, 0, 2);
        assertEquals(boardTest.getBoardNumber(0, 0), 2);
    }

    @Test
    public void moveTest() {
        boardTest.printBoard();
        int counter = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                boardTest.setGameBoard(i, j, counter);
                counter++;
            }
        }
        boardTest.setZero(0,0);
        boardTest.setZero(1,0);
        assertEquals(boardTest.getBoardNumber(0, 0), 0);
        assertEquals(boardTest.getBoardNumber(0, 1), 1);
        boardTest.move('R');
        assertEquals(boardTest.getBoardNumber(0, 0), 1);
        assertEquals(boardTest.getBoardNumber(0, 1), 0);
        boardTest.move('D');
        assertEquals(boardTest.getBoardNumber(0, 1), 5);
        assertEquals(boardTest.getBoardNumber(1, 1), 0);
        boardTest.move('L');
        assertEquals(boardTest.getBoardNumber(1, 1), 4);
        assertEquals(boardTest.getBoardNumber(1, 0), 0);
        boardTest.move('U');
        assertEquals(boardTest.getBoardNumber(1, 0), 1);
        assertEquals(boardTest.getBoardNumber(0, 0), 0);
    }
}