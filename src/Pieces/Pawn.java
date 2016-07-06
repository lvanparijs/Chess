package Pieces;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Pawn extends Piece {

    public Pawn(int x, int y, Color color) {
        super(x, y, color, "Pawn");
    }

    private int[][] legalP1 = {{0,-1},{0,-2},{1,-1},{-1,1}};
    private int[][] legalP2 = {{0,1},{0,2},{1,1},{-1,1}};
    public boolean isLegal(int x, int y){
        int [] displacement = {x-this.x, y-this.y};
        System.out.println(displacement);
        System.out.println(legalP1);
        if (color == Color.white){
            System.out.println(Arrays.asList(legalP1).contains(displacement));

            return Arrays.asList(legalP1).contains(displacement);

        }

        else if (color == Color.black){
            return Arrays.asList(legalP2).contains(displacement);
        }
        return false;
    }
}
