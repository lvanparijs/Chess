package Pieces;

import Essentials.Board;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(int x, int y, Color color) {
        super(x, y, color, "Pawn");
        findImage();
    }

    private int[][] legalP1 = {{0,-1},{1,-1},{-1,-1},{0,-2}};
    private int[][] legalP2 = {{0,1},{1,1},{-1,1},{0,2}};
    public boolean isLegal(int x, int y){
        int [] displacement = {x-this.x, y-this.y};
        //System.out.println(displacement.toString());
        /*for(int i = 0; i < displacement.length; i++){
            System.out.print(displacement[i]+" ");
        }*/
        //System.out.println(legalP1.toString());
        if (color == Color.white){
            for(int i = 0; i < legalP1.length; i++){
                if(legalP1[i][0] == displacement[0] && legalP1[i][1] == displacement[1]) {
                    if(i == 1 || i == 2){
                        if(!Board.getInstance().isOccupied(x,y)){
                            return false;
                        }
                    }else if(i == 0 || (!firstMove && i == 3)){
                        if(Board.getInstance().isOccupied(x,y)){
                            return false;
                        }
                    }
                    if(firstMove) {
                        legalP1[legalP1.length-1] = legalP1[0];
                        firstMove = false;
                    }
                    return true;
                }
            }

        } else{
            for(int i = 0; i < legalP2.length; i++){
                if(legalP2[i][0] == displacement[0] && legalP2[i][1] == displacement[1]) {
                    if(i == 1 || i == 2){
                        if(!Board.getInstance().isOccupied(x,y)){
                            return false;
                        }
                    }else if(i == 0 || (!firstMove && i == 3)){
                        if(Board.getInstance().isOccupied(x,y)){
                            return false;
                        }
                    }
                    if(firstMove) {
                        legalP2[legalP2.length-1] = legalP2[0];
                        firstMove = false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
