package essentials;

import pieces.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Locoge on 3-7-2016.
 * Adapted by Lvanp on 13-12-2019
 */
public class Board {

    private static Board board = new Board(); //Singleton design pattern, only one board

    int size; //Size of the board

    private long P;//White Pawns
    private long N;//White Knights
    private long B;//White Bishops
    private long R;//White Rooks
    private long K;//White King
    private long Q;//White Queen
    private long p;//Black Pawns
    private long n;//Black Knights
    private long b;//Black Bishops
    private long r;//Black Rooks
    private long k;//Black King
    private long q;//Black Queen

    private long[] pieces = new long[]{P,N,B,R,K,Q,p,n,b,r,k,q}; //All piece types in one list
    private String[] piecesNames = new String[]{"P","N","B","R","K","Q","p","n","b","r","k","q"}; //All names of piece types in one list

    private int[][] posTable;

    public static Board getInstance(){
        return board;
    }

    private Board(){
        this.size = 8;
        initBoard();
        posTable = genPosTable();
    }

    private void initBoard(){
        P = 0b0000000000000000000000000000000000000000000000001111111100000000L;
        N = 0b0000000000000000000000000000000000000000000000000000000001000010L;
        B = 0b0000000000000000000000000000000000000000000000000000000000100100L;
        R = 0b0000000000000000000000000000000000000000000000000000000010000001L;
        K = 0b0000000000000000000000000000000000000000000000000000000000001000L;
        Q = 0b0000000000000000000000000000000000000000000000000000000000010000L;
        p = 0b0000000011111111000000000000000000000000000000000000000000000000L;
        n = 0b0100001000000000000000000000000000000000000000000000000000000000L;
        b = 0b0010010000000000000000000000000000000000000000000000000000000000L;
        r = 0b1000000100000000000000000000000000000000000000000000000000000000L;
        k = 0b0001000000000000000000000000000000000000000000000000000000000000L;
        q = 0b0000100000000000000000000000000000000000000000000000000000000000L;
    }

    private List<Object[]> bitPos2BoardPos(List<Object[]> pos){
        List<Object[]> positions = new ArrayList<>();
        for(Object[] i : pos){
            int horPos = (int)i[0]%8;
            int verPos = (int)Math.floor(((int)i[0])/8);
            positions.add(new Object[]{horPos, verPos, i[1]});
        }
        return positions;
    }

    private int[][] genPosTable(){
        int[][] table = new int[size][size];
        int cnt = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                table[i][j] = cnt;
                cnt++;
            }
        }
        return table;
    }

    private int board2bitPos(int x, int y){
        return posTable[x][y];
    }

    private int[] bit2BoardPos(int pos){
        for(int i = 0; i < posTable[0].length; i++){
            for(int j = 0; j < posTable.length; j++){
                if(posTable[i][j] == pos){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    private List<Object[]> bitPositions(long number, String name) {
        List<Object[]> positions = new ArrayList<>();
        int position = 1;
        while (number != 0) {
            if ((number & 1) != 0) {
                positions.add(new Object[]{position-1, name});
            }
            position++;
            number = number >>> 1L;
        }
        return positions;
    }

    public boolean isOccupied(int x, int y, int player){
        int toBeReplaced = board2bitPos(x,y);
        long spot2Check = 0b0000000000000000000000000000000000000000000000000000000000000000;

        spot2Check |= (1L<<(toBeReplaced)); //Sets the index from toBeReplaced to 1

        long res = spot2Check & getPiecesOfPlayerN(player);

        List<Object[]> resList = bitPositions(res, "X");
        if(resList.size() > 0){
            int[] resCoords = bit2BoardPos(64-(int)resList.get(0)[0]);
            System.out.println("HIT");
            System.out.println(resCoords[0]+", "+resCoords[1]);
            return true;
        }else{
            System.out.println("NO HIT");
            return false;
        }
    }

    public void printBoard(){//For debugging purposes
        List<Object[]> boardState = new ArrayList();
        for(String name : piecesNames){
            List<Object[]> tmpState = bitPos2BoardPos(bitPositions(getPieceType(name),name));
            if(tmpState.size() != 0){
                boardState.addAll(tmpState);
            }
        }
        //System.out.print("x x x x x x x x \n");
        System.out.print("x 0 1 2 3 4 5 6 7 \n");
        System.out.print(" _________________ \n");
        for (int i=0; i<size;i++){
            System.out.print("| ");
            for (int j=0; j<size;j++){
                boolean found = false;
                for(Object[] ia : boardState){
                    if((int)ia[0] == j && (int)ia[1] == i){
                        System.out.print(ia[2]+" ");
                        found = true;
                    }
                }
                if(found == false){
                    System.out.print("- ");
                }
            }
            System.out.print("| ");
            System.out.println(i);
        }
        System.out.print(" -----------------  y\n");
        System.out.println();
    }

    public BufferedImage getPieceImg(String type){
        try {
            switch (type) {
                case "P":
                    return ImageIO.read(new File("res/PawnWhite.png"));
                case "N":
                    return ImageIO.read(new File("res/HorseWhite.png"));
                case "B":
                    return ImageIO.read(new File("res/BishopWhite.png"));
                case "R":
                    return ImageIO.read(new File("res/RookWhite.png"));
                case "K":
                    return ImageIO.read(new File("res/KingWhite.png"));
                case "Q":
                    return ImageIO.read(new File("res/QueenWhite.png"));
                case "p":
                    return ImageIO.read(new File("res/PawnBlack.png"));
                case "n":
                    return ImageIO.read(new File("res/HorseBlack.png"));
                case "b":
                    return ImageIO.read(new File("res/BishopBlack.png"));
                case "r":
                    return ImageIO.read(new File("res/RookBlack.png"));
                case "k":
                    return ImageIO.read(new File("res/KingBlack.png"));
                case "q":
                    return ImageIO.read(new File("res/QueenBlack.png"));
                default:
                    throw new IllegalArgumentException("Invalid piece type: " + type);
            }
        }catch (Exception e){
            System.out.println("File Not Found");
            return null;
        }
    }

    public long getPieceType(String type){
        switch (type){
            case "P":
                return getPawns(0);
            case "N":
                return getKnights(0);
            case "B":
                return getBishops(0);
            case "R":
                return getRooks(0);
            case "K":
                return getKing(0);
            case "Q":
                return getQueen(0);
            case "p":
                return getPawns(1);
            case "n":
                return getKnights(1);
            case "b":
                return getBishops(1);
            case "r":
                return getRooks(1);
            case "k":
                return getKing(1);
            case "q":
                return getQueen(1);
            default:
                return (long)0;
        }
    }

    public void paintBoard(Graphics2D g2d, int screenX, int screenY, int size){
        List<Object[]> boardState = new ArrayList();
        for(String name : piecesNames){
            List<Object[]> tmpState = bitPos2BoardPos(bitPositions(getPieceType(name),name));
            if(tmpState.size() != 0){
                boardState.addAll(tmpState);
            }
        }
        BufferedImage pieceImg = null;
        for (int i=0; i<size;i++){
            for (int j=0; j<size;j++){
                for(Object[] ia : boardState){
                    if((int)ia[0] == i && (int)ia[1] == j){
                        pieceImg = getPieceImg((String)ia[2]);
                    }
                }
                if(pieceImg != null){
                    g2d.drawImage(pieceImg,screenX*i,screenY*j,size,size,null);
                    pieceImg = null;
                }
            }
        }
    }


    public void init(){//Initialises the pieces on the board at the beginning of a game

        /*spaces[0][0] = new Rook(0,0,false);
        spaces[1][0] = new Horse(1,0,false);
        spaces[2][0] = new Bishop(2,0,false);
        spaces[3][0] = new Queen(3,0,false);
        spaces[4][0] = new King(4,0,false);
        spaces[5][0] = new Bishop(5,0,false);
        spaces[6][0] = new Horse(6,0,false);
        spaces[7][0] = new Rook(7,0,false);
        spaces[0][1] = new Pawn(0,1,false);
        spaces[1][1] = new Pawn(1,1,false);
        spaces[2][1] = new Pawn(2,1,false);
        spaces[3][1] = new Pawn(3,1,false);
        spaces[4][1] = new Pawn(4,1,false);
        spaces[5][1] = new Pawn(5,1,false);
        spaces[6][1] = new Pawn(6,1,false);
        spaces[7][1] = new Pawn(7,1,false);

        spaces[0][7] = new Rook(0,7,true);
        spaces[1][7] = new Horse(1,7,true);
        spaces[2][7] = new Bishop(2,7,true);
        spaces[3][7] = new Queen(3,7,true);
        spaces[4][7] = new King(4,7,true);
        spaces[5][7] = new Bishop(5,7,true);
        spaces[6][7] = new Horse(6,7,true);
        spaces[7][7] = new Rook(7,7,true);
        spaces[0][6] = new Pawn(0,6,true);
        spaces[1][6] = new Pawn(1,6,true);
        spaces[2][6] = new Pawn(2,6,true);
        spaces[3][6] = new Pawn(3,6,true);
        spaces[4][6] = new Pawn(4,6,true);
        spaces[5][6] = new Pawn(5,6,true);
        spaces[6][6] = new Pawn(6,6,true);
        spaces[7][6] = new Pawn(7,6,true);*/

    }

    public long getPiecesOfPlayerN(int player){
        if(player == 0){//White
            return P|B|N|R|K|Q;
        }else{//Black
            return p|b|n|r|k|q;
        }
    }

    public long getPawns(int player) {
        if (player == 0) {
            return P;
        } else {
            return p;
        }
    }

    public long getRooks(int player) {
        if (player == 0) {
            return R;
        } else {
            return r;
        }
    }

    public long getKnights(int player) {
        if (player == 0) {
            return N;
        } else {
            return n;
        }
    }

    public long getBishops(int player) {
        if (player == 0) {
            return B;
        } else {
            return b;
        }
    }

    public long getQueen(int player) {
        if (player == 0) {
            return Q;
        } else {
            return q;
        }
    }

    public long getKing(int player) {
        if (player == 0) {
            return K;
        } else {
            return k;
        }
    }

    public void drawBoard(long board){
        List<Object[]> pos = bitPositions(board,"X");
        System.out.print("x x x x x x x x \n");
        System.out.print("0 1 2 3 4 5 6 7 \n");
        int cnt = 0;
        for (int i=0; i<size;i++){
            for (int j=0; j<size;j++){
                boolean found = false;
                for(Object[] ia : pos){
                    if((int)ia[0] == cnt){
                        System.out.print(ia[1]+" ");
                        found = true;
                    }
                }
                if(found == false){
                    System.out.print("- ");
                }
                cnt++;
            }
            System.out.println(i+" y");
        }
        System.out.println();
    }

    public int getSize(){
        return size;
    }

    public boolean myPiece(int x, int y, boolean white){
        /*if (spaces[x][y] == null) {
            return false;
        }
        if (white){
            if (!spaces[x][y].getColor()){
                return false;
            }

        } else if (!white){
            if (spaces[x][y].getColor()){
                return false;
            }
        }*/
    return true;
    }


    public boolean isTie(boolean color){

        //als je check staat kan het geen Tie zijn
        if (isCheck(color)){
            return false;
        }

        //Returns false if there is a possible move
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Piece p = getPieceAtPos(i,j);
                if(p != null && p.getColor() == color){
                    if(p instanceof Pawn){
                        Pawn pa = (Pawn)p;
                        if(!color){
                            for (int k = 0; k < pa.legalP2.length; k++) {
                                if (pa.isPossible(p.x-pa.legalP2[k][0], p.y-pa.legalP2[k][1])) {
                                    return false;
                                }
                            }
                        }else{
                            for (int k = 0; k < pa.legalP1.length; k++) {
                                if (pa.isPossible(p.x-pa.legalP1[k][0], p.y-pa.legalP1[k][1])) {
                                    return false;
                                }
                            }
                        }
                    }else {
                        for (int k = 0; k < p.legal.length; k++) {
                            if (p.isPossible(p.x-p.legal[k][0], p.y-p.legal[k][1])) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        /*

        Piece piece = getPieceAtPos(x,y);
        if (piece.isPossible(x,y+1)){
            return false;
        }
        if (piece.isPossible(x,y-1)){
            return false;
        }
        if (piece.isPossible(x+1,y+1)){
            return false;
        }
        if (piece.isPossible(x+1,y)){
            return false;
        }
        if (piece.isPossible(x+1,y-1)){
            return false;
        }
        if (piece.isPossible(x-1,y+1)){
            return false;
        }
        if (piece.isPossible(x-1,y)){
            return false;
        }
        if (piece.isPossible(x-1,y-1)){
            return false;
        }
        */
        return true;
    }

    public boolean isCheckmate(boolean color){
        if (isCheck(color)){
            int x = getCoordinatesKing(color)[0];
            int y = getCoordinatesKing(color)[1];
            Piece piece = getPieceAtPos(x,y);
            if (piece.isPossible(x,y+1)){
                return false;
            }
            if (piece.isPossible(x,y-1)){
                return false;
            }
            if (piece.isPossible(x+1,y+1)){
                return false;
            }
            if (piece.isPossible(x+1,y)){
                return false;
            }
            if (piece.isPossible(x+1,y-1)){
                return false;
            }
            if (piece.isPossible(x-1,y+1)){
                return false;
            }
            if (piece.isPossible(x-1,y)){
                return false;
            }
            if (piece.isPossible(x-1,y-1)){
                return false;
            }
            return true;
        }

        return false;
    }

    public boolean isCheck(boolean color){
        int x = board.getCoordinatesKing(color)[0];
        int y = board.getCoordinatesKing(color)[1];
        for (int i=0;i<8;i++){
            for (int j=0; j<8;j++){
                if (i == x && j == y){
                    continue;
                }
                Piece piece = getPieceAtPos(i,j);

                if (piece != null) {
                    if (piece.getColor() == color){
                        continue;
                    }
                    if (piece.isPossible(x,y)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isPlaceCheck(int x, int y, boolean c){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == x && j == y) {

                }else {
                    Piece piece = getPieceAtPos(i, j);
                    if (piece != null) {
                        if (piece.getColor() != c) {
                            if (piece.isPossible(x, y)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int[] getCoordinatesKing(boolean color){
        int[] k = {8,8};
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++) {
                Piece piece = getPieceAtPos(i, j);
                if (piece != null){
                    if (piece.getColor() == color && piece.getType()=="King"){
                        k[0]=i;
                        k[1]=j;
                        return k;
                    }
                }

            }
        }
        return k;
    }
    public Piece getPieceAtPos(int x, int y){
        return null;
    }

    public void moveXtoY(int x1, int y1, int x2, int y2){
        /*spaces[x2][y2] = spaces[x1][y1];
        spaces[x1][y1] = null;
        spaces[x2][y2].x = x2;
        spaces[x2][y2].y = y2;*/
    }

    private static long parseLong(String s, int base) {
        return new BigInteger(s, base).longValue();
    }
}
