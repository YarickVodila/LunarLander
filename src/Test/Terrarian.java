package Test;
import java.awt.*;

public class Terrarian extends GameObject {
    public int terrainX[] = {0, 60, 120, 150, 180, 210, 240, 270, 420, 480, 540, 630, 690, 710, 710, 0};
    public int terrainY[] = {0, 90, 150, 300, 240, 240, 390, 480, 480, 360, 240, 180, 150, 0, 600, 600};
    public Terrarian(Game game) {
        super(game);

    }
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.gray);
        g.fillPolygon(terrainX, terrainY, terrainX.length);
        g.setColor(Color.RED);
        g.fillRect(270,480,150,5);
    }
}
