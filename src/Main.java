import Essentials.*;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Timer;

/**
 * Created by lvanp on 03/07/2016.
 */
public class Main extends JFrame{

    Timer timer;



    public static void main(String[] args)
    {
        Board board = Board.getInstance();
        MouseHandler mouseHandler = new MouseHandler();
        GameEngine gameLoop = new GameEngine(mouseHandler);
        GraphicsEngine graphicsEngine = new GraphicsEngine();
        MenuPanel menuPanel = new MenuPanel();
        Main frame = new Main(graphicsEngine,mouseHandler);

        gameLoop.start();

    }

    public Main(JPanel panel, MouseHandler mouseHandler){
        this.setTitle("Chess");
        this.setSize(GraphicsSettings.frameWidth,GraphicsSettings.frameHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(panel);
        panel.addMouseListener(mouseHandler);
        this.setVisible(true);
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                panel.repaint();
            }
        },0,17);
    }

}
