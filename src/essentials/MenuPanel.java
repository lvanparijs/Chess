package essentials;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by lvanp on 24/07/2016.
 */
public class MenuPanel extends JPanel {

    private boolean[][] stars = new boolean[GraphicsSettings.frameWidth][GraphicsSettings.frameHeight];
    private int starCount = 0;

    public MenuPanel(){
        super();

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.black);

        JLabel title = new JLabel("Chess v1.0");
        JLabel creators = new JLabel("Created by Andreas Bally, Lucas Vanparijs");

        Font titleFont = new Font("Serif", Font.ITALIC, 30);

        title.setFont(titleFont);

        JButton singlePlayer = new JButton("Single Player");
        JButton multiPlayer = new JButton("Multiplayer");

        this.add(title);
        this.add(singlePlayer);
        this.add(multiPlayer);
        this.add(creators);

        //fill stars array
        for (int i=0; i < stars.length; i++) {
            for (int j=0; j < stars[0].length; j++) {
                double num = Math.random();
                if (num < 0.0006) {
                    stars[i][j] = true;
                } else {
                    stars[i][j] = false;
                }
            }
        }


    }

    public void drawStars(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if(starCount == 100) {
            double pct = Math.random();

            /** vary the colors of the stars (twinkle) */
            if (pct > 0.5)
                g2.setPaint(Color.WHITE);
            else if (pct > 0.2)
                g2.setPaint(Color.gray);
            else if (pct > 0.1)
                g2.setPaint(Color.green);
            else
                g2.setPaint(Color.blue);

            starCount = 0;
        }else{
            starCount++;
        }

        for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < stars[0].length; j++) {
                if (stars[i][j]) {
                    g2.fill(new Ellipse2D.Double(i, j, 2, 2));
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){

        System.out.println("HERE");
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        drawStars(g2d);
    }


}
