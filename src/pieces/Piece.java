package pieces;

import essentials.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.lang.Math.*;

/**
 * Created by Locoge on 3-7-2016.
 */
public abstract class Piece {

    public Piece(int x, int y, boolean color, String type){
        this.x = x;
        this.y = y;
        this.color = color;
        this.type = type;
    }

    protected boolean firstMove = true;
    public boolean dragged = false;
    boolean color;//True means white, false means black
    public int x;
    public int y;
    public int[][] legal;

    String type;
    public BufferedImage img;

    public boolean isPathFree(int x, int y){

        if (x-this.x==0 || y-this.y==0){
            if (y==this.y && x>this.x){
                for (int i = this.x+1; i<x;i++){
                    if (Board.getInstance().isOccupied(i,y)){
                        return false;
                    }
                }
            }
            if (y==this.y && x<this.x){
                for (int i = this.x-1; i>x;i--){
                    if (Board.getInstance().isOccupied(i,y)){
                        return false;
                    }
                }
            }
            if (x==this.x && y>this.y){
                for (int i = this.y+1; i<y; i++){
                    if (Board.getInstance().isOccupied(x,i)){
                        return false;
                    }
                }
            }
            if (x==this.x && y<this.y){
                for (int i = this.y -1; i>y; i--){
                    if (Board.getInstance().isOccupied(x,i)){
                        return false;
                    }
                }
            }
        }

        else if (abs(x-this.x)==abs(y-this.y)){
            int lenPath = abs(x-this.x);
            if (y>this.y && x>this.x){
                for (int i=1; i<lenPath; i++){
                    if (Board.getInstance().isOccupied(this.x+i,this.y+i)){
                        return false;
                    }
                }
            }
            if (y<this.y && x>this.x) {
                for (int i = 1; i < lenPath; i++) {
                    if (Board.getInstance().isOccupied(this.x + i, this.y - i)) {
                        return false;
                    }
                }
            }
            if (y<this.y && x<this.x){
                for (int i=1; i<lenPath;i++){
                    if (Board.getInstance().isOccupied(this.x-i,this.y-i)){
                        return false;
                    }
                }

            }
            if (y>this.y && x<this.x){
                for (int i=1; i<lenPath;i++){
                    if (Board.getInstance().isOccupied(this.x-i,this.y+i)){
                        return false;
                    }
                }
            }

        }
        else {
            return true;
        }
        return true;
    }

    public abstract boolean isLegal(int x, int y );

    public boolean isPossible(int x, int y){
        //Outside the board
        if(isOutOfBounds(x,y)) {
            return false;
        }
        //If it's a knight this does not apply
        if(type != "Horse")
            if(!isPathFree(x,y)) {
                return false;
            }
        //If it from the same player you cannot go there
        if (Board.getInstance().getPieceAtPos(x,y)!=null) {
            if (Board.getInstance().getPieceAtPos(x, y).getColor() == color) {
                return false;
            }
        }

        //IF it is a legal move for the specific piece, ie. if its moveset allows it to move there
        if (!isLegal(x,y)){
            return false;
        }

        return true;

    }

    boolean isOutOfBounds(int x, int y){
        if (x < 0 || y < 0 || x > Board.getInstance().getSize()-1 || y > Board.getInstance().getSize()-1)
            return true;
        else
            return false;
    }

    public void move(int x, int y) {
        firstMove = false;
        Board.getInstance().moveXtoY(this.x,this.y,x,y);
    }

    public void findImage(){
        String col;
        if(color){
            col = "White";
        }else{
            col = "Black";
        }
        try {
            String username = System.getProperty("user.name");
            if (username.contains("Locoge")){
                username = "darius";
            }
            this.img = ImageIO.read(new File("C:\\Users\\"+username+"\\IdeaProjects\\Chess\\res/"+type+col+".png"));
        }catch (Exception e){
            System.out.println("File Not Found");
        }
    }

    public void drawPiece(Graphics2D g2d, int screenX, int screenY, int size){
        g2d.drawImage(img,screenX,screenY,size,size,null);
    }

    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public boolean getColor() {return this.color; }
    public String getType(){
        return this.type;
    }
}
