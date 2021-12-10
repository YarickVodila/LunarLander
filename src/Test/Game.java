package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private JFrame window;
    private GameCanvas gameCanvas;
    public Set<GameObject> gameObjects = new HashSet<>(); //множество
    public Set<Integer> pressedKeys = new HashSet<>(); //множество
    Ship ship = new Ship(this);
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void start(){
        createWindow();
        initKeyListeners();
        createInitialGameObjects();
        startTimer();
    }

    private void createInitialGameObjects() {

        ship.position = new Vector2(450, 10);
        gameObjects.add(ship);
        Terrarian ter = new Terrarian(this);
        gameObjects.add(ter);

    }
    public void createWindow(){
        gameCanvas = new GameCanvas(this);
        window = new JFrame("MyLunarLander");
        window.setSize(730, 630);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(gameCanvas);
        window.getContentPane().setBackground(Color.black);
        window.setVisible(true);
    }
    private void startTimer() {

        Timer timer = new Timer(50, new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();
    }

    private void tick(){//обновляет все состояния объектов каждые 50 мс
        if (ship.status==false){
            for(GameObject gameObject: gameObjects){
                gameObject.update();
            }
            gameCanvas.repaint();
        }
    }

    private void initKeyListeners(){
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
                if(pressedKeys.contains(32)){
                    ship.status=false;
                    ship.flag=false;
                    ship.position.x=450;
                    ship.position.y=10;
                    ship.vy=0;
                    ship.vx=0;
                    ship.fuel=70;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                pressedKeys.remove(e.getKeyCode());
            }
        });
    }
}
