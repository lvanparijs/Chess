package Essentials;

import Pieces.Piece;
import Players.Human;
import Players.Player;

import java.awt.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static sun.misc.Version.println;

/**
 * Created by Locoge on 3-7-2016.
 */
public class GameEngine {

    Board board = Board.getInstance();

    Player player1 = new Human(Color.white);
    Player player2 = new Human(Color.black);

    MouseHandler mouseHandler;

    private boolean whiteCheck = false;
    private boolean blackCheck = false;

    public static boolean white = false;



    boolean readMode;

    public GameEngine(MouseHandler mouseHandler){

        this.mouseHandler = mouseHandler;

    }



    public void start()  {

        board.init();
        board.printBoard();

        do {
            white = !white;

            int x1;
            int y1;
            do {
                mouseHandler.setClick(false);
                while (!mouseHandler.getClick()){
                    System.out.print("");}
                System.out.println(" ");

                x1 = mouseHandler.getX();
                y1 = mouseHandler.getY();
                mouseHandler.setClick(false);


            } while (!board.myPiece(x1, y1, white));


            Piece piece = board.getPieceAtPos(x1,y1);
            System.out.printf("Type: %20s X: %d Y: %d", piece.getType(),piece.getX(),piece.getY());

            int x2;
            int y2;

            do {
                while (!mouseHandler.getClick()){
                    System.out.print("");
                }
                System.out.println(" ");
                x2 = mouseHandler.getX();
                y2 = mouseHandler.getY();
                mouseHandler.setClick(false);

            } while (!piece.isPossible(x2,y2));

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
                    System.exit(1);
                }
                else {
                    if(white){
                        System.out.printf("Watch out Black, you are body check\n");
                    }else{
                        System.out.printf("Watch out White, you are body check\n");
                    }


                }
            else if (board.isTie(color)) {
                System.out.printf("It is a tie!\n");
                System.exit(1);
            }

        } while (true);
    }

}
