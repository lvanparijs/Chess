package Essentials;

import Pieces.Piece;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by lvanp on 11/07/2016.
 */
public class MouseHandler implements MouseListener {

        private int counter = 0;
        public int mouseX,mouseY;
        public boolean click = false;
        public Board board = Board.getInstance();
        Piece piece;
        GraphicsEngine graphicsEngine;
        GameEngine gameEngine;

        //boolean white = gameEngine.white;

        public MouseHandler(){
            //this.gameEngine = gameEngine;
            //this.graphicsEngine = graphicsEngine;
        }

        public int getX(){
            return (int)Math.floor(mouseX/GraphicsSettings.squareSize);
        }

        public int getY(){
            return (int)Math.floor(mouseY/GraphicsSettings.squareSize);
        }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
        public void mousePressed(MouseEvent event){
            mouseX = event.getX()-GraphicsSettings.leftSize;
            mouseY = event.getY()-GraphicsSettings.topSize;

            click = true;
        }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
