package Essentials;

import Pieces.*;
import javafx.scene.paint.*;

import java.awt.*;
import java.awt.Color;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Board {

    private static Board board = new Board(); //Singleton design pattern

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


    public void printBoard(){//For debugging purposes
        System.out.print("0 1 2 3 4 5 6 7 \n");
        for (int i=0; i<spaces.length;i++){
            for (int j=0; j<spaces.length;j++){
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


    public void init(){//Initialises the pieces on the board at the beginning of a game

        spaces[0][0] = new Rook(0,0,false);
        spaces[1][0] = new Horse(1,0,false);
        spaces[2][0] = new Bishop(2,0,false);
        spaces[3][0] = new Queen(3,0,false);
        spaces[4][0] = new King(4,0,false);
        spaces[5][0] = new Bishop(5,0,false);
        spaces[6][0] = new Horse(6,0,false);
        spaces[7][0] = new Rook(7,0,false);
        spaces[0][1] = new Pawn(0,1,false);
        spaces[1][1] = new Pawn(1,1,false);
        spaces[2][1] = new Pawn(2,1,false);
        spaces[3][1] = new Pawn(3,1,false);
        spaces[4][1] = new Pawn(4,1,false);
        spaces[5][1] = new Pawn(5,1,false);
        spaces[6][1] = new Pawn(6,1,false);
        spaces[7][1] = new Pawn(7,1,false);

        spaces[0][7] = new Rook(0,7,true);
        spaces[1][7] = new Horse(1,7,true);
        spaces[2][7] = new Bishop(2,7,true);
        spaces[3][7] = new Queen(3,7,true);
        spaces[4][7] = new King(4,7,true);
        spaces[5][7] = new Bishop(5,7,true);
        spaces[6][7] = new Horse(6,7,true);
        spaces[7][7] = new Rook(7,7,true);
        spaces[0][6] = new Pawn(0,6,true);
        spaces[1][6] = new Pawn(1,6,true);
        spaces[2][6] = new Pawn(2,6,true);
        spaces[3][6] = new Pawn(3,6,true);
        spaces[4][6] = new Pawn(4,6,true);
        spaces[5][6] = new Pawn(5,6,true);
        spaces[6][6] = new Pawn(6,6,true);
        spaces[7][6] = new Pawn(7,6,true);

    }

    public int getSize(){
        return size;
    }

    public boolean myPiece(int x, int y, boolean white){
        if (spaces[x][y] == null) {
            return false;
        }
        if (white){
            if (!spaces[x][y].getColor()){
                return false;
            }

        } else if (!white){
            if (spaces[x][y].getColor()){
                return false;
            }
        }
    return true;
    }


    public boolean isTie(boolean color){

        //als je check staat kan het geen Tie zijn
        if (isCheck(color)){
            return false;
        }

        //Returns false if there is a possible move
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Piece p = getPieceAtPos(i,j);
                if(p != null && p.getColor() == color){
                    if(p instanceof Pawn){
                        Pawn pa = (Pawn)p;
                        if(!color){
                            for (int k = 0; k < pa.legalP2.length; k++) {
                                if (pa.isPossible(p.x-pa.legalP2[k][0], p.y-pa.legalP2[k][1])) {
                                    return false;
                                }
                            }
                        }else{
                            for (int k = 0; k < pa.legalP1.length; k++) {
                                if (pa.isPossible(p.x-pa.legalP1[k][0], p.y-pa.legalP1[k][1])) {
                                    return false;
                                }
                            }
                        }
                    }else {
                        for (int k = 0; k < p.legal.length; k++) {
                            if (p.isPossible(p.x-p.legal[k][0], p.y-p.legal[k][1])) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        /*

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
        */
        return true;
    }

    public boolean isCheckmate(boolean color){
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

    public boolean isCheck(boolean color){
        int x = board.getCoordinatesKing(color)[0];
        int y = board.getCoordinatesKing(color)[1];
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

    public boolean isPlaceCheck(int x, int y, boolean c){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == x && j == y) {

                }else {
                    Piece piece = getPieceAtPos(i, j);
                    if (piece != null) {
                        if (piece.getColor() != c) {
                            if (piece.isPossible(x, y)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int[] getCoordinatesKing(boolean color){
        int[] k = {8,8};
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++) {
                Piece piece = getPieceAtPos(i, j);
                if (piece != null){
                    if (piece.getColor() == color && piece.getType()=="King"){
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
        spaces[x2][y2].x = x2;
        spaces[x2][y2].y = y2;
    }
}
