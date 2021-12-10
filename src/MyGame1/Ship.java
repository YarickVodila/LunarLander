package MyGame1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ship extends GameObject { //наследуется от GameObject у него есть свойства game и position
    private BufferedImage texture;

    public Ship(Game game) {
        super(game);
        try {
            texture = ImageIO.read(new File("ship.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update() {
        super.update();
        if(game.pressedKeys.contains(37)){
            position.x -= 10;
        }
        if(game.pressedKeys.contains(39)){
            position.x += 10;
        }
        if(game.pressedKeys.contains(38)){
            position.y -= 10;
        }
        if(game.pressedKeys.contains(40)){
            position.y += 10;
        }
        if(game.pressedKeys.contains(32)){
            Shot shot = new Shot(game);
            shot.position = new Vector2(position.x, position.y - 70);
            game.newGameObjects.add(shot);
        }
    }
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(texture, position.x - 25, position.y - 70, null);
    }
}