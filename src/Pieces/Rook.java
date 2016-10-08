package Pieces;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Rook extends Piece {
    public Rook(int x, int y, boolean color) {
        super(x, y, color, "Rook");
        findImage();
        legal  = new int[][]{{0,1}, {0,2}, {0,3},{0,4}, {0,5}, {0,6}, {0,7},
                {0,-1}, {0,-2}, {0,-3},{0,-4}, {0,-5}, {0,-6}, {0,-7},
                {1,0}, {2,0}, {3,0},{4,0}, {5,0}, {6,0}, {7,0},
                {-1,0}, {-2,0}, {-3,0},{-4,0}, {-5,0}, {-6,0}, {-7,0}};
    }

    public boolean firstMove = true;

    public boolean isLegal(int x, int y){
        int [] displacement = {x-this.x, y-this.y};
        for(int i = 0; i < legal.length; i++){
            if(legal[i][0] == displacement[0] && legal[i][1] == displacement[1]) {
                return true;
            }
        }
        return false;
    }
}

