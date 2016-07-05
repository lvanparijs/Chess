package Essentials;

import Pieces.*;

import java.awt.*;

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

    public Board(){
        this.size = 8;
        spaces = new Piece[size][size];
    }

    public boolean isOccupied(int x, int y){
        if(spaces[x][y] == null)
            return false;
        else
            return true;
    }



    public void printBoard(){
        for (int i=0; i<spaces.length;i++){
            for (int j=0; j<spaces.length;j++){
                //String x = Board.getInstance().spaces[j][i].getType();

                if (spaces[j][i]!=null){
                    System.out.print(spaces[j][i].getType().charAt(0)+" ");
                }
                else {
                    System.out.print("- ");
                }

            }
            System.out.println();
        }
    }



    public void init(){
        spaces[0][0] = new Rook(0,0,Color.black);
        spaces[1][0] = new Knight(1,0,Color.black);
        spaces[2][0] = new Bishop(2,0,Color.black);
        spaces[3][0] = new Queen(3,0,Color.black);
        spaces[4][0] = new King(4,0,Color.black);
        spaces[5][0] = new Bishop(5,0,Color.black);
        spaces[6][0] = new Knight(6,0,Color.black);
        spaces[7][0] = new Rook(7,0,Color.black);
        spaces[0][1] = new Pawn(0,1,Color.black);
        spaces[1][1] = new Pawn(1,1,Color.black);
        spaces[2][1] = new Pawn(2,1,Color.black);
        spaces[3][1] = new Pawn(3,1,Color.black);
        spaces[4][1] = new Pawn(4,1,Color.black);
        spaces[5][1] = new Pawn(5,1,Color.black);
        spaces[6][1] = new Pawn(6,1,Color.black);
        spaces[7][1] = new Pawn(7,1,Color.black);

        spaces[0][7] = new Rook(0,7,Color.white);
        spaces[1][7] = new Knight(1,7,Color.white);
        spaces[2][7] = new Bishop(2,7,Color.white);
        spaces[3][7] = new Queen(3,7,Color.white);
        spaces[4][7] = new King(4,7,Color.white);
        spaces[5][7] = new Bishop(5,7,Color.white);
        spaces[6][7] = new Knight(6,7,Color.white);
        spaces[7][7] = new Rook(7,7,Color.white);
        spaces[0][6] = new Pawn(0,6,Color.white);
        spaces[1][6] = new Pawn(1,6,Color.white);
        spaces[2][6] = new Pawn(2,6,Color.white);
        spaces[3][6] = new Pawn(3,6,Color.white);
        spaces[4][6] = new Pawn(4,6,Color.white);
        spaces[5][6] = new Pawn(5,6,Color.white);
        spaces[6][6] = new Pawn(6,6,Color.white);
        spaces[7][6] = new Pawn(7,6,Color.white);

    }

    public int getSize(){
        return size;
    }

    public Piece getPieceAtPos(int x, int y){
        return spaces[x][y];
    }
}
