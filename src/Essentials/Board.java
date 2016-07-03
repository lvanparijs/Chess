package Essentials;

import Pieces.Piece;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Board {

    private static Board board = new Board();

    int size;
    Piece[][] spaces;

    public static Board getInstance(){
        return board;
    }

    private Board(){
        this.size = 8;
        spaces = new Piece[size][size];
    }

    public boolean isOccupied(int x, int y){
        if(spaces[x][y] == null)
            return false;
        else
            return true;
    }

    public int getSize(){
        return size;
    }

    public Piece getPieceAtPos(int x, int y){
        return spaces[x][y];
    }
}
