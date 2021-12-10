package Test;
import java.lang.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ship extends GameObject { //наследуется от GameObject у него есть свойства game и position
    private BufferedImage texture;
    double vy=0;
    double vx;
    boolean flag;
    boolean status=false;
    int val1;
    int val2;
    int score=0;
    int record;
    int fuel=70;
    Robot r;

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
        if(game.pressedKeys.contains(37)&&(fuel>0)){
            //position.x -= 10;
            vx=vx-0.8;
            fuel--;
        }
        if(game.pressedKeys.contains(39)&&(fuel>0)){
            //position.x += 10;
            vx=vx+0.8;
            fuel--;
        }
        if(game.pressedKeys.contains(38) &&(fuel>0)){
            //position.y -= 1;
            vy=vy-0.8;
            fuel--;
        }
        /*
        if(game.pressedKeys.contains(40)){
            position.y += 10;
            //vy=vy-2;
        }
         */
       // System.out.println(position.x+" - "+position.y);
        val1 = (int) vy;
        position.y += val1;
        vy+=0.3;
        if (vx!=0){
            val2 = (int) vx;
            position.x +=val2;
            if(vx<0){
                vx+=0.1;
            }
            if(vx>0){
                vx-=0.1;
            }
        }
        try {
            r = new Robot();
            Color color = r.getPixelColor(position.x+25,position.y+100);
            if ((color.getRed()==255) && (color.getGreen()==0) && (color.getBlue()==0) && vy<=4){
                status=true;
                flag=true;
                //System.out.println(color);
            }
            else if ((color.getRed()==255) && (color.getGreen()==0) && (color.getBlue()==0) && vy>4){
                status=true;
                flag=false;
                //System.out.println(color);
            }
            else if ((color.getRed()==128) && (color.getGreen()==128) && (color.getBlue()==128) || (position.y<0)){
                status=true;
                flag=false;
                //System.out.println(color);
            }
            //System.out.println(color);
        } catch (AWTException ex) {}

    }
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(texture, position.x , position.y , null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Счёт = "+score, 500,30);
        g.drawString("Рекорд = "+record, 500,60);
        g.drawString("Топливо = "+fuel, 500,90);
        if (status==true && flag==true){
            g.drawString("You are Winner!", 290,200);
            g.drawString("Нажать Space для перезапуска", 230,250);
            score+=1;
            if (record<score){
                record=score;
            }
        }
        else if (status==true && flag==false){
            g.drawString("Houston, we have a problem!", 250,200);
            g.drawString("Нажать Space для перезапуска", 230,250);
            score=0;
        }
    }
}