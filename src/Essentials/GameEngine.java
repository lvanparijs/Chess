package Essentials;

import Pieces.Piece;
import Players.Human;
import Players.Player;

import java.awt.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Locoge on 3-7-2016.
 */
public class GameEngine {

    Board board = Board.getInstance();

    Player player1 = new Human(Color.white);
    Player player2 = new Human(Color.black);

    boolean white = false;

    public GameEngine(){
    }

    public void start(){

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

            //nodig om mijn isCheck method te gebruiken, color wordt de kleur van degene die niet aan de beurt is
            Color color;
            if (white){
                color = Color.black;
            }
            else {
                color = Color.white;
            }

            if (board.isCheck(color))
                if (board.isCheckmate(color)){
                    if(white){
                        System.out.printf("%s won!\n", "White");
                    }else{
                        System.out.printf("%s won!\n", "Black");
                    }
                    break;
                }
                else {
                    if(white){
                        System.out.printf("Watch out Black, you are body checked\n");
                    }else{
                        System.out.printf("Watch out White, you are body checked\n");
                    }
                }
            else if (board.isTie(color))
                System.out.printf("It is a tie!\n");
            break;


        } while (true);
    }

}
