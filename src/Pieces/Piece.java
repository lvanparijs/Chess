package Pieces;

import Essentials.Board;

import java.awt.*;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Piece {

    int x;
    int y;

    boolean alive = true;

    Color color;

    String type;

    public Piece(int x, int y, Color color, String type){
        this.x = x;
        this.y = y;
        this.color = color;
        this.type = type;
    }

    boolean isPossible(int x, int y){
        //Outside the board
        if(isOutOfBounds(x,y))
            return false;
        //If it's a knight this does not apply
        if(!type.contains("Knight"))
            if(!isPathFree(x,y))
                return false;
        //If it from the same player you cannot go there
        if(Board.getInstance().getPieceAtPos(x,y).color == color)
            return false;

        return true;

    }
    boolean isOutOfBounds(int x, int y){
        if (x < 0 || y < 0 || x > Board.getInstance().getSize()-1 || y > Board.getInstance().getSize()-1)
            return false;
        else
            return true;
    }
    boolean isPathFree(int x, int y){
        return false;
    }
    boolean move(int x, int y){
        return false;
    }

}
