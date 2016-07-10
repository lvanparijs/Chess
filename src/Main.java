import Essentials.Board;
import Essentials.GameEngine;
import Essentials.GraphicsEngine;
import Pieces.Piece;
import Players.Human;
import Players.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lvanp on 03/07/2016.
 */
public class Main extends JFrame {

    public static void main(String[] args)
    {
        Board board = Board.getInstance();

        Timer t = new Timer();


        GameEngine gameLoop = new GameEngine();
        GraphicsEngine graphicsEngine = new GraphicsEngine(t);

        Main frame = new Main(graphicsEngine);

        gameLoop.start();


    }

    public Main(JPanel panel){
        this.setTitle("Chess");
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(panel);

        this.setVisible(true);

    }
}
