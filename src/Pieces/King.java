package Pieces;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Locoge on 3-7-2016.
 */
public class King extends Piece {
    public King(int x, int y, Color color) {
        super(x, y, color, "King");
    }
    private int[][] legalK={ {1,1}, {1,0}, {0,1}, {1,-1}, {-1,-1}, {-1,0}, {0,-1}, {-1,1} };

    public boolean isLegal(int x, int y){
        int [] displacement = {x-this.x, y-this.y};
        return Arrays.asList(legalK).contains(displacement);
    }
}
