package essentials;

import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lvanp on 11/07/2016.
 */
public class MouseHandler extends MouseAdapter{

    private int counter = 0;
    public static int mouseX,mouseY;
    public int x1,x2,y1,y2;
    boolean sendMove = false;
    public Board board = Board.getInstance();
    Piece piece;
    GraphicsEngine ge;
    GameEngine gameEngine;


    public MouseHandler(GraphicsEngine ge){
        this.ge = ge;
    }

    public int getX(){
            return (int)Math.floor(mouseX/GraphicsSettings.squareSize);
        }

    public int getY(){
            return (int)Math.floor(mouseY/GraphicsSettings.squareSize);
        }

    public int getMouseX(){
        return mouseX;
    }

    public int getMouseY(){
        return mouseY;
    }

    @Override
    public void mousePressed(MouseEvent event){
        sendMove = false;
        System.out.println("press");
        mouseX = event.getX();
        mouseY = event.getY();
        Piece tmp = Board.getInstance().getPieceAtPos(getX(),getY());
        if(tmp != null){
            piece = tmp;
            piece.dragged = true;
            x1 = getX();
            y1 = getY();
        }
    }

    public Piece getSelectedPiece(){
        return piece;
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("release");
        if(piece != null){
            mouseX = e.getX();
            mouseY = e.getY();
            piece.dragged = false;
            if(piece.isPossible(getX(),getY())){
                x2 = getX();
                y2 = getY();
                sendMove = true;
                piece = null;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
        ge.repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
