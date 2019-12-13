package essentials;

import pieces.Piece;
import agents.Human;
import agents.Player;

import java.awt.*;


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
            white = !white; //switches turns

            int x = -1;
            int y = -1;
            Piece piece = null;

            while (!mouseHandler.sendMove){

            }

            piece = Board.getInstance().getPieceAtPos(mouseHandler.x1,mouseHandler.y1);
            x = mouseHandler.x2;
            y = mouseHandler.y2;

            mouseHandler.sendMove = false;

            piece.move(x,y);

            board.printBoard();


            if (board.isCheck(!white))
                if (board.isCheckmate(!white)){
                    if(white){
                        System.out.printf("%s won!\n", "White");
                    }else{
                        System.out.printf("%s won!\n", "Black");
                    }
                    System.exit(1);
                }
                else {
                    if(white){
                        System.out.printf("Watch out Black, you are check\n");
                    }else{
                        System.out.printf("Watch out White, you are check\n");
                    }


                }
            else if (board.isTie(!white)) {
                System.out.printf("It is a tie!\n");
                System.exit(1);
            }

        } while (true);
    }

    public void selectedPiece(Piece p){

    }

}
