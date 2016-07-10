import Essentials.*;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lvanp on 03/07/2016.
 */
public class Main extends JFrame{

    public static void main(String[] args)
    {
        Board board = Board.getInstance();
        GameEngine gameLoop = new GameEngine();
        GraphicsEngine graphicsEngine = new GraphicsEngine();

        Main frame = new Main(graphicsEngine);

        gameLoop.start();


    }

    public Main(JPanel panel){
        this.setTitle("Chess");
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(panel);
        this.addMouseListener(new MouseEngine((GraphicsEngine) panel));
        this.setVisible(true);


    }
    private class MouseEngine extends MouseAdapter {
        private int counter=0;
        boolean white = true;
        Piece piece;
        GraphicsEngine graphicsEngine;


        public MouseEngine(GraphicsEngine graphicsEngine){
            this.graphicsEngine = graphicsEngine;
        }
        public Board board = Board.getInstance();
        @Override
        public void mouseClicked(MouseEvent event){
            int xPos = event.getX()-GraphicsSettings.leftSize;
            int yPos= event.getY()-GraphicsSettings.windowTitleSize;
            int x = xPos/GraphicsSettings.squareSize;
            int y = yPos/GraphicsSettings.squareSize;
            if (counter == 0){
                if (!board.myPiece(x, y, white)) {
                    return;
                }


                piece = board.getPieceAtPos(x,y);
                counter = counter +1;


            }

            if (counter == 1){
                if (!piece.isPossible(x,y)){
                    return;
                }
                piece.move(x,y);



                white = !white;
                counter = 0;
                graphicsEngine.repaint();


            }

        }
    }

}
