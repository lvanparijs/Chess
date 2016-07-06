package Players;

import java.awt.*;

/**
 * Created by Locoge on 3-7-2016.
 */
public class Player {
    public Color color;
    public String type;

    public Player(Color color, String type) {
        this.color=color;
        this.type=type;

    }
    public Color getColor(){
        return this.color;
    }
    public void changeColor(){
        if (this.color==Color.black){
            this.color=Color.white;
        }
        else
            this.color=color.black;
    }
}
