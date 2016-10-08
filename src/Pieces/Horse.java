package Pieces;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Horse extends Piece {

    public Horse(int x, int y, boolean color) {
        super(x, y, color, "Horse");
        findImage();
        legal = new int[][]{ {1,2}, {1,-2}, {-1,2}, {-1,-2}, {2,1}, {2,-1}, {-2,1}, {-2,-1} };

    }

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

