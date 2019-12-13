package pieces;

import essentials.Board;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Pawn extends Piece {

    public Pawn(int x, int y, boolean color) {
        super(x, y, color, "Pawn");
        findImage();
    }

    public int[][] legalP1 = {{0,-1},{1,-1},{-1,-1},{0,-2}};
    public int[][] legalP2 = {{0,1},{1,1},{-1,1},{0,2}};

    public boolean isLegal(int x, int y){
        int [] displacement = {x-this.x, y-this.y};

        if (color){
            for(int i = 0; i < legalP1.length; i++){
                if(legalP1[i][0] == displacement[0] && legalP1[i][1] == displacement[1]) {
                    if(i == 1 || i == 2){
                        if(!Board.getInstance().isOccupied(x,y)){
                            return false;
                        }
                    }else if(i == 0){
                        if(Board.getInstance().isOccupied(x,y)){
                            return false;
                        }
                    }else if(i == 3){
                        if(!firstMove)
                            return false;
                    }
                    if(firstMove) {
                        legalP1 = new int[][]{{0,-1},{1,-1},{-1,-1}};
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
                    }else if(i == 0){
                        if(Board.getInstance().isOccupied(x,y)){
                            return false;
                        }
                    }else if(i == 3){
                        if(!firstMove)
                            return false;
                    }
                    if(firstMove) {
                        legalP2 = new int[][]{{0,1},{1,1},{-1,1}};
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
