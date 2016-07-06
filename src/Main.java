import Essentials.Board;
import Pieces.Piece;
import Players.Human;
import Players.Player;

import java.awt.*;
import java.util.Scanner;

/**
 * Created by lvanp on 03/07/2016.
 */
public class Main {

    public static void main(String[] args)
    {
        Board board = new Board();

        Player player1 = new Human(Color.white);
        Player player2 = new Human(Color.black);

        Player turn = player2;

        board.printBoard();
        board.init();
        board.printBoard();

        Scanner input = new Scanner(System.in);

        do {
            turn.changeColor();


            int x1;
            int y1;
            do {System.out.printf("%s, it is your turn, please choose two set of coordinates\n", turn.getColor());
                System.out.print("Choose the piece you want to move (first x then y): ");
                x1 = input.nextInt();
                y1 = input.nextInt();
            } while (!board.myPiece(x1, y1, turn.getColor()));


            Piece piece = board.getPieceAtPos(x1,y1);
            int x2;
            int y2;

            do {System.out.print("Choose the place you want to move it(first x then y): ");
                x2 = input.nextInt();
                y2 = input.nextInt();
            } while (!piece.isPossible(x2,y2));


            piece.move(x2,y2);

            board.printBoard();


        } while (!board.isCheckmate() && !board.isTie());

        if (board.isTie()) {
            System.out.print("It's a tie!");

        }

        if (board.isCheckmate()) {
            System.out.printf("%s won!", turn.getColor());
        }


    }
}
