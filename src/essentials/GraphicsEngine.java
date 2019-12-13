package essentials;

import pieces.Piece;


import javax.swing.*;
import java.awt.*;
import java.util.Timer;

import static essentials.GraphicsSettings.squareSize;

/**
 * Created by Locoge on 3-7-2016.
 */
public class GraphicsEngine extends JPanel {

    Timer timer;

    int sqSize = squareSize;

    public GraphicsEngine() {

    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Board board = Board.getInstance();

        for(int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if ((i + j) % 2 == 0) {
                    g2d.setColor(GraphicsSettings.whiteField);
                } else {
                    g2d.setColor(GraphicsSettings.blackField);
                }
                g2d.fillRect(sqSize * i, sqSize * j, sqSize, sqSize);
            }
        }

        for(int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Piece curPiece = board.getPieceAtPos(i,j);
                if(curPiece instanceof Piece) {
                    if(curPiece.dragged){
                        curPiece.drawPiece(g2d, MouseHandler.mouseX-(int)(sqSize/2),MouseHandler.mouseY-(int)(sqSize/2),sqSize);
                    }else{
                        curPiece.drawPiece(g2d, sqSize * i, sqSize * j, sqSize);
                    }
                }
            }
        }
    }

    public void refresh(){

    }

}
