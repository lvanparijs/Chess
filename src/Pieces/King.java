package Pieces;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Locoge on 3-7-2016.
 */
public class King extends Piece {
    public King(int x, int y, Color color) {
        super(x, y, color, "King");
        findImage();
    }
    private int[][] legalK={ {1,1}, {1,0}, {0,1}, {1,-1}, {-1,-1}, {-1,0}, {0,-1}, {-1,1} };

    public boolean isLegal(int x, int y){
        int [] displacement = {x-this.x, y-this.y};
        for(int i = 0; i < legalK.length; i++){
            if(legalK[i][0] == displacement[0] && legalK[i][1] == displacement[1]) {
                return true;
            }
        }
        return false;
    }
}
