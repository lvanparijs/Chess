import Essentials.*;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lvanp on 03/07/2016.
 */
public class Main extends JFrame{


    public static void main(String[] args)
    {
        Board board = Board.getInstance();
        MouseHandler mouseHandler = new MouseHandler();
        GameEngine gameLoop = new GameEngine(mouseHandler);
        GraphicsEngine graphicsEngine = new GraphicsEngine();
        Main frame = new Main(graphicsEngine,mouseHandler);

        gameLoop.start();

    }

    public Main(JPanel panel, MouseHandler mouseHandler){
        this.setTitle("Chess");
        this.setSize(486,509);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.add(panel);
        this.addMouseListener(mouseHandler);
        this.setVisible(true);
    }

}
