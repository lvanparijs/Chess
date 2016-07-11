package Pieces;

import Essentials.Board;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Locoge on 3-7-2016.
 */
public class King extends Piece {

    private boolean firstMove = true;
    public int[][] castlingK = {{-2, 0}, {2, 0}};


    public King(int x, int y, Color color) {
        super(x, y, color, "King");
        findImage();
        legal = new int[][] {{1, 1}, {1, 0}, {0, 1}, {1, -1}, {-1, -1}, {-1, 0}, {0, -1}, {-1, 1}};

    }

    public boolean isLegal(int x, int y) {
        System.out.println(color.toString());
        int[] displacement = {x - this.x, y - this.y};
        for (int i = 0; i < legal.length; i++) {
            if (legal[i][0] == displacement[0] && legal[i][1] == displacement[1]) {
                if(!Board.getInstance().isPlaceCheck(x,y,color)) {
                    firstMove = false;
                    return true;
                }
            }
        }
        if (firstMove) {
            for (int i = 0; i < castlingK.length; i++) {
                if (castlingK[i][0] == displacement[0] && castlingK[i][1] == displacement[1]) {
                    //Wil rokeren
                    if (!Board.getInstance().isCheck(color)) {
                        if (i == 0) {
                            if (Board.getInstance().isOccupied(0, y)) {
                                Piece p = Board.getInstance().getPieceAtPos(0, y);
                                if (p instanceof Rook && ((Rook) p).firstMove) {
                                    for (int k = 2; k < this.x; k++) {
                                        if (Board.getInstance().isOccupied(k, y) || Board.getInstance().isPlaceCheck(k, y, color)) {
                                            return false;
                                        }
                                    }
                                    firstMove = false;
                                    ((Rook) p).firstMove = false;
                                    Board.getInstance().moveXtoY(0, y, 3, y);
                                    return true;
                                }
                            }
                        } else if (i == 1) {
                            if (Board.getInstance().isOccupied(7, y)) {
                                Piece p = Board.getInstance().getPieceAtPos(7, y);
                                if (p instanceof Rook && ((Rook) p).firstMove) {
                                    for (int k = 6; k > this.x; k--) {
                                        if (Board.getInstance().isOccupied(k, y) || Board.getInstance().isPlaceCheck(k, y, color)) {
                                            return false;
                                        }
                                    }
                                    firstMove = false;
                                    ((Rook) p).firstMove = false;
                                    Board.getInstance().moveXtoY(7, y, 5, y);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}