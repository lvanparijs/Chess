package Pieces;

import static java.lang.Math.*;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Piece {
    int x = Integer.MAX_VALUE;
    int y = Integer.MAX_VALUE;;

    boolean alive = true;

    boolean isPossible(int x, int y) {
    }
    boolean isOutOfBounds(int x, int y);

    public boolean isPathFree(int x, int y){
        if (x-this.x==0 || y-this.y==0){
            if (y==this.y && x>this.x){
                for (int i = this.x+1; i<x;i++){
                    if (Board.getInstance.isOccupied(i,y)){
                        return false;
                    }
                    return true;
                }
            }
            if (y==this.y && x<this.x){
                for (int i = this.x-1; i>x;i--){
                    if (Board.getInstance.isOccupied(i,y)){
                        return false;
                    }
                    return true;
                }
            }
            if (x==this.x && y>this.y){
                for (int i = this.y +1; i<y; i++){
                    if (Board.getInstance.isOccupied(x,i)){
                        return false;
                    }
                    return true;
                }
            }
            if (x==this.x && y<this.y){
                for (int i = this.y -1; i>y; i--){
                    if (Board.getInstance.isOccupied(x,i)){
                        return false;
                    }
                    return true;
                }
            }
        }

        else if (abs(x-this.x)==abs(y-this.y)){
            if (y>this.y && x>.this.x){
                for (int i= this.x +1; i<x; i++){
                    if (Board.getInstance.isOccupied(i,i)){
                        return false;
                    }
                    return true;
                }
            }
            if (y<this.y && x>this.x) {
                for (int i = 1; i < this.y - y; i++) {
                    if (Board.getInstance.isOccupied(this.x + i, this.y - i)) {
                        return false;
                    }
                    return true;
                }
            }
            if (y<this.y && x<this.x){
                for (int i=1; i<this.y-y;i++){
                    if (Board.getInstance.isOccupied(this.x-i,this.y-i)){
                        return false;
                    }
                    return true;
                }

            }
            if (y>this.y && x<this.x){
                for (int i=1; i<y-this.y;i++){
                    if (Board.getInstance.isOccupied(this.x-i,this.y+i)){
                        return false;
                    }
                    return true;
                }
            }



        }
        else {
            return true;
        }
    }


    public void move(int x, int y) {
        this.x=x;
        this.y=y;
    }

}
