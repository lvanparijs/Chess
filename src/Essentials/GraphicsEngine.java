package Essentials;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Locoge on 3-7-2016.
 */
public class GraphicsEngine extends JPanel{

    int sqSize = GraphicsSettings.squareSize;

    public GraphicsEngine(){

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
                    g2d.setColor(Color.LIGHT_GRAY);
                }else{
                    g2d.setColor(Color.DARK_GRAY);
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
