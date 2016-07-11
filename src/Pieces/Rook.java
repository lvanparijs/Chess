package Pieces;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Rook extends Piece {
    public Rook(int x, int y, Color color) {
        super(x, y, color, "Rook");
        findImage();
    }

    public boolean firstMove = true;
    private int[][] legalR  = {{0,1}, {0,2}, {0,3},{0,4}, {0,5}, {0,6}, {0,7}, {0,8},
                            {0,-1}, {0,-2}, {0,-3},{0,-4}, {0,-5}, {0,-6}, {0,-7}, {0,-8},
                            {1,0}, {2,0}, {3,0},{4,0}, {5,0}, {6,0}, {7,0}, {8,0},
                            {-1,0}, {-2,0}, {-3,0},{-4,0}, {-5,0}, {-6,0}, {-7,0}, {-8,0}};

    public boolean isLegal(int x, int y){
        int [] displacement = {x-this.x, y-this.y};
        for(int i = 0; i < legalR.length; i++){
            if(legalR[i][0] == displacement[0] && legalR[i][1] == displacement[1]) {
                firstMove = false;
                return true;
            }
        }
        return false;
    }
}

