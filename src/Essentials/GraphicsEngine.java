package Essentials;

import Pieces.Piece;


import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static Essentials.GraphicsSettings.squareSize;

/**
 * Created by Locoge on 3-7-2016.
 */
public class GraphicsEngine extends JPanel {

    Timer timer;

    int sqSize = squareSize;

    public GraphicsEngine() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        },0,17);
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Board board = Board.getInstance();

        for(int i = 0; i < board.getSize(); i++){
            for(int j = 0; j < board.getSize(); j++){
                if((i+j)%2==0){
                    g2d.setColor(GraphicsSettings.whiteField);
                }else{
                    g2d.setColor(GraphicsSettings.blackField);
                }
                g2d.fillRect(sqSize*i,sqSize*j,sqSize,sqSize);
                Piece curPiece = board.getPieceAtPos(i,j);
                if(curPiece instanceof Piece) {
                    board.spaces[i][j].drawPiece(g2d, sqSize * i, sqSize * j);
                }
            }
        }
    }

    public void refresh(){

    }

}
