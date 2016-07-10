package Pieces;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Bishop extends Piece{
    public Bishop(int x, int y, Color color) {
        super(x, y, color, "Bishop");
        findImage();
    }

    private int[][] legalB = {{1,1}, {2,2}, {3,3}, {4,4}, {5,5}, {6,6}, {7,7}, {8,8},
                             {1,-1}, {2,-2}, {3,-3}, {4,-4}, {5,-5}, {6,-6}, {7,-7}, {8,-8},
                             {-1,1}, {-2,2}, {-3,3}, {-4,4}, {-5,5}, {-6,6}, {-7,7}, {-8,8},
                             {-1,-1}, {-2,-2}, {-3,-3}, {-4,-4}, {-5,-5}, {-6,-6}, {-7,-7}, {-8,-8}};

    public boolean isLegal(int x, int y){
        int [] displacement = {x-this.x, y-this.y};
        for(int i = 0; i < legalB.length; i++){
            if(legalB[i][0] == displacement[0] && legalB[i][1] == displacement[1]) {
                return true;
            }
        }
        return false;

    }
}
