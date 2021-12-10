package MyGame1;

import java.awt.*;

public class Shot extends GameObject{

    public Shot(Game game) {
        super(game);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.fillRect(position.x, position.y, 2, 5);
    }

    @Override
    public void update() {
        super.update();
        position.y -= 10;
    }
}