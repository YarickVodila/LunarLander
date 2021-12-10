package Test;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    private final Game game;

    public GameCanvas(Game game){
        this.game = game;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(GameObject gameObject: game.gameObjects){
            gameObject.draw(g);
        }
    }
}