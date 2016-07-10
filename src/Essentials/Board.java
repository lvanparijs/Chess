package Essentials;

import Pieces.*;
import javafx.scene.paint.*;

import java.awt.*;
import java.awt.Color;

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
        System.out.print("0 1 2 3 4 5 6 7 \n");
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

            System.out.println(i);
        }
        System.out.println();
    }


    public void init(){
        spaces[0][0] = new Rook(0,0,Color.black);
        spaces[1][0] = new Horse(1,0,Color.black);
        spaces[2][0] = new Bishop(2,0,Color.black);
        spaces[3][0] = new Queen(3,0,Color.black);
        spaces[4][0] = new King(4,0,Color.black);
        spaces[5][0] = new Bishop(5,0,Color.black);
        spaces[6][0] = new Horse(6,0,Color.black);
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
        spaces[1][7] = new Horse(1,7,Color.white);
        spaces[2][7] = new Bishop(2,7,Color.white);
        spaces[3][7] = new Queen(3,7,Color.white);
        spaces[4][7] = new King(4,7,Color.white);
        spaces[5][7] = new Bishop(5,7,Color.white);
        spaces[6][7] = new Horse(6,7,Color.white);
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

    public boolean myPiece(int x, int y, boolean white){
        if (spaces[x][y] == null || ((spaces[x][y].getColor() == Color.WHITE) != white) ) {
            return false;
        }
        else return true;
    }
    public boolean isTie(Color color){
        int x = getCoordinatesKing(color)[0];
        int y = getCoordinatesKing(color)[1];
        //als je check staat kan het geen Tie zijn
        if (isCheck(color)){
            return false;
        }
        Piece piece = getPieceAtPos(x,y);
        if (piece.isPossible(x,y+1)){
            return false;
        }
        if (piece.isPossible(x,y-1)){
            return false;
        }
        if (piece.isPossible(x+1,y+1)){
            return false;
        }
        if (piece.isPossible(x+1,y)){
            return false;
        }
        if (piece.isPossible(x+1,y-1)){
            return false;
        }
        if (piece.isPossible(x-1,y+1)){
            return false;
        }
        if (piece.isPossible(x-1,y)){
            return false;
        }
        if (piece.isPossible(x-1,y-1)){
            return false;
        }
        return true;
    }

    public boolean isCheckmate(Color color){
        if (isCheck(color)){
            int x = getCoordinatesKing(color)[0];
            int y = getCoordinatesKing(color)[1];
            Piece piece = getPieceAtPos(x,y);
            if (piece.isPossible(x,y+1)){
                return false;
            }
            if (piece.isPossible(x,y-1)){
                return false;
            }
            if (piece.isPossible(x+1,y+1)){
                return false;
            }
            if (piece.isPossible(x+1,y)){
                return false;
            }
            if (piece.isPossible(x+1,y-1)){
                return false;
            }
            if (piece.isPossible(x-1,y+1)){
                return false;
            }
            if (piece.isPossible(x-1,y)){
                return false;
            }
            if (piece.isPossible(x-1,y-1)){
                return false;
            }
            return true;
        }

        return false;
    }

    public boolean isCheck(Color color){
        int x = getCoordinatesKing(color)[0];
        int y = getCoordinatesKing(color)[1];
        for (int i=0;i<8;i++){
            for (int j=0; j<8;j++){
                if (i == x && j == y){
                    continue;
                }
                Piece piece = getPieceAtPos(i,j);

                if (piece != null) {
                    if (piece.getColor() == color){
                        continue;
                    }
                    if (piece.isPossible(x,y)){
                        return true;
                    }
                }


            }
        }

        return false;
    }

    public int[] getCoordinatesKing(Color color){
        int[] k = {8,8};
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++) {
                Piece piece = getPieceAtPos(i, j);
                if (piece != null){
                    if (piece.getColor()== color && piece.getType()=="King"){
                        k[0]=i;
                        k[1]=j;
                        return k;
                    }
                }

            }
        }
        return k;
    }
    public Piece getPieceAtPos(int x, int y){
        return spaces[x][y];
    }

    public void moveXtoY(int x1, int y1, int x2, int y2){
        spaces[x2][y2] = spaces[x1][y1];
        spaces[x1][y1] = null;
    }

    public void drawBoard(Graphics2D g2d){

    }
}
