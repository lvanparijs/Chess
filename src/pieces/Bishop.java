package pieces;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Bishop extends Piece{
    public Bishop(int x, int y, boolean color) {
        super(x, y, color, "Bishop");
        findImage();
        legal = new int[][] {   {1,1}, {2,2}, {3,3}, {4,4}, {5,5}, {6,6}, {7,7},
                                {1,-1}, {2,-2}, {3,-3}, {4,-4}, {5,-5}, {6,-6}, {7,-7},
                                {-1,1}, {-2,2}, {-3,3}, {-4,4}, {-5,5}, {-6,6}, {-7,7},
                                {-1,-1}, {-2,-2}, {-3,-3}, {-4,-4}, {-5,-5}, {-6,-6}, {-7,-7}};

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
