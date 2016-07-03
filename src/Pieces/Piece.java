package Pieces;

import Essentials.Board;

import java.awt.*;

import static java.lang.Math.*;

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

    public String getType(){
        return this.type;
    }

    public boolean isPathFree(int x, int y){
        if (x-this.x==0 || y-this.y==0){
            if (y==this.y && x>this.x){
                for (int i = this.x+1; i<x;i++){
                    if (Board.getInstance().isOccupied(i,y)){
                        return false;
                    }
                    return true;
                }
            }
            if (y==this.y && x<this.x){
                for (int i = this.x-1; i>x;i--){
                    if (Board.getInstance().isOccupied(i,y)){
                        return false;
                    }
                    return true;
                }
            }
            if (x==this.x && y>this.y){
                for (int i = this.y +1; i<y; i++){
                    if (Board.getInstance().isOccupied(x,i)){
                        return false;
                    }
                    return true;
                }
            }
            if (x==this.x && y<this.y){
                for (int i = this.y -1; i>y; i--){
                    if (Board.getInstance().isOccupied(x,i)){
                        return false;
                    }
                    return true;
                }
            }
        }

        else if (abs(x-this.x)==abs(y-this.y)){
            if (y>this.y && x>this.x){
                for (int i= this.x +1; i<x; i++){
                    if (Board.getInstance().isOccupied(i,i)){
                        return false;
                    }
                    return true;
                }
            }
            if (y<this.y && x>this.x) {
                for (int i = 1; i < this.y - y; i++) {
                    if (Board.getInstance().isOccupied(this.x + i, this.y - i)) {
                        return false;
                    }
                    return true;
                }
            }
            if (y<this.y && x<this.x){
                for (int i=1; i<this.y-y;i++){
                    if (Board.getInstance().isOccupied(this.x-i,this.y-i)){
                        return false;
                    }
                    return true;
                }

            }
            if (y>this.y && x<this.x){
                for (int i=1; i<y-this.y;i++){
                    if (Board.getInstance().isOccupied(this.x-i,this.y+i)){
                        return false;
                    }
                    return true;
                }
            }



        }
        else {
            return true;
        }
        return true;
    }


    public void move(int x, int y) {
        this.x=x;
        this.y=y;
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

}
