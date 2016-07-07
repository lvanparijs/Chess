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
        Board board = Board.getInstance();

        Player player1 = new Human(Color.white);
        Player player2 = new Human(Color.black);

        boolean white = false;

        board.printBoard();
        System.out.println("================");
        board.init();
        board.printBoard();

        Scanner input = new Scanner(System.in);

        do {
            white = !white;


            int x1;
            int y1;
            do {
                if(white){
                    System.out.printf("%s, it is your turn, please choose two set of coordinates\n", "White");
                }else{
                    System.out.printf("%s, it is your turn, please choose two set of coordinates\n", "Black");
                }
                System.out.print("Choose the piece you want to move (first x then y): ");
                x1 = input.nextInt();
                y1 = input.nextInt();
            } while (!board.myPiece(x1, y1, white));


            Piece piece = board.getPieceAtPos(x1,y1);
            System.out.println(piece.getType());
            int x2;
            int y2;

            do {System.out.print("Choose the place you want to move it(first x then y): ");
                x2 = input.nextInt();
                y2 = input.nextInt();
            } while (!piece.isPossible(x2,y2));

            System.out.println("MOVE");
            piece.move(x2,y2);

            board.printBoard();


        } while (!board.isCheckmate() && !board.isTie());

        if (board.isTie()) {
            System.out.print("It's a tie!");

        }

        if (board.isCheckmate()) {
            if(white){
                System.out.printf("%s won!", "White");
            }else{
                System.out.printf("%s won!", "Black");
            }

        }


    }
}
