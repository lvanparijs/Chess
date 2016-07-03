import Essentials.Board;

/**
 * Created by lvanp on 03/07/2016.
 */
public class Main {

    public static void main(String[] args)
    {
        Board board = new Board();
        board.printBoard();
        board.init();
        board.printBoard();
    }
}
