package Pieces;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(int x, int y, Color color) {
        super(x, y, color, "Pawn");
    }

    private int[][] legalP1 = {{0,-1},{1,-1},{-1,-1},{0,-2}};
    private int[][] legalP2 = {{0,1},{1,1},{-1,1},{0,2}};
    public boolean isLegal(int x, int y){
        int [] displacement = {x-this.x, y-this.y};
        //System.out.println(displacement.toString());
        for(int i = 0; i < displacement.length; i++){
            System.out.print(displacement[i]+" ");
        }
        System.out.println(color.toString());
        System.out.println(Color.white.toString());
        //System.out.println(legalP1.toString());
        if (color == Color.white){
            for(int i = 0; i < legalP1.length; i++){
                if(legalP1[i][0] == displacement[0] && legalP1[i][1] == displacement[1]) {
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
