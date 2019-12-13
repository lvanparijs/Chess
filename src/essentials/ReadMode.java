package essentials;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Locoge on 12-7-2016.
 */
public class ReadMode {
    private static Scanner input;
    private int xMove;
    private int yMove;
    private int xMoveRok;
    private int yMoveRok;
    private boolean isRok;

    public ReadMode() {
    }


    public void startReadMode(String file){

        try {
            input = new Scanner(Paths.get(file));
        }
        catch(IOException ioException){
            System.err.println("Error opening file. Terminating");
            System.exit(1);
        }

    }
    public int getxMove() {
        return this.xMove;
    }
    public int getyMove() {
        return this.yMove;
    }
    public int getxMoveRok(){
        return this.xMoveRok;
    }
    public int getyMoveRok(){
        return this.yMoveRok;
    }
    public boolean getIsRok(){
        return this.isRok;
    }
    public void setIsRok(boolean b){
        isRok = b;
    }


    public void getNextMove(){
        if (input.hasNext()){
            String a = input.next();
            while (a.charAt(1)==46) {
                a = input.next();

            }
            if (a=="O-O"){
                if (GameEngine.white){
                    xMove = 4;
                    yMove = 7;
                    xMoveRok = 6;
                    yMoveRok = 7;
                }
                else {
                    xMove = 4;
                    yMove = 0;
                    xMoveRok = 6;
                    yMoveRok = 0;
                }
                setIsRok(true);
                return;

            }
            if (a=="O-O-O"){
                if (GameEngine.white){
                    xMove = 4;
                    yMove = 7;
                    xMoveRok = 2;
                    yMoveRok = 7;
                }
                else {
                    xMove = 4;
                    yMove = 0;
                    xMoveRok = 2;
                    yMoveRok = 0;
                }
                setIsRok(true);
            }

            int asci1;
            int asci2;
            if (a.length()==3){
                asci1 = (int) a.charAt(1);
                asci2 = (int) a.charAt(2);
            }
            else{
                asci1 = (int) a.charAt(0);
                asci2 = (int) a.charAt(1);
            }



            if (asci1 ==79){
                asci2 = (int) input.next().charAt(0);
                asci2 = (int) input.next().charAt(0);
                if ((int) input.next().charAt(0) == 45){//lange rokade
                    if (GameEngine.white){
                        xMove = 4;
                        yMove = 7;
                        xMoveRok = 2;
                        yMoveRok = 7;
                    }
                    else {
                        xMove = 4;
                        yMove = 0;
                        xMoveRok = 2;
                        yMoveRok = 0;
                    }
                }
                else{//korte rokade
                    if (GameEngine.white){
                        xMove = 4;
                        yMove = 7;
                        xMoveRok = 6;
                        yMoveRok = 7;
                    }
                    else {
                        xMove = 4;
                        yMove = 0;
                        xMoveRok = 6;
                        yMoveRok = 0;
                    }
                }
                isRok = true;
                return;


            }

            if (asci1 == 97){
                asci1 =0;
            }

            if (asci1 == 98){
                asci1 =1;
            }
            if (asci1 == 99){
                asci1 =2;
            }
            if (asci1 == 100){
                asci1 =3;
            }
            if (asci1 == 101){
                asci1 =4;
            }
            if (asci1 == 102){
                asci1 =5;
            }
            if (asci1 == 103){
                asci1 =6;
            }
            if (asci1 == 104){
                asci1 =7;
            }


            if (asci2 == 49) {
                asci2 = 7;
            }
            if (asci2 == 50) {
                asci2 = 6;
            }
            if (asci2 == 51) {
                asci2 = 5;
            }
            if (asci2 == 52) {
                asci2 = 4;
            }
            if (asci2 == 53) {
                asci2 = 3;
            }
            if (asci2 == 54) {
                asci2 = 2;
            }
            if (asci2 == 55) {
                asci2 = 1;
            }
            if (asci2 == 56) {
                asci2 = 0;
            }
            this.xMove = asci1;
            this.yMove = asci2;

        }
        else{
            System.out.println("GameOver");
            System.exit(1);

        }
    }

}
