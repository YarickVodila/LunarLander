package MyGame1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class Game { //В Teste убрал стрельбу и изменил метод tick
    private JFrame window;
    private GameCanvas gameCanvas;
    public Set<GameObject> gameObjects = new HashSet<>(); //множество
    public Set<Integer> pressedKeys = new HashSet<>(); //множество
    public Set<GameObject> newGameObjects = new HashSet<>();//множество
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
        Ship ship = new Ship(this);
        ship.position = new Vector2(250, 450);
        gameObjects.add(ship);
        /*
        Shot shot = new Shot(this);
        shot.position = new Vector2(250, 450);
        newGameObjects.add(shot);
         */
    }
    public void createWindow(){
        gameCanvas = new GameCanvas(this);
        window = new JFrame("Game");
        window.setSize(500, 500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(gameCanvas);
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
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
        for(GameObject gameObject: gameObjects){
            gameObject.update();
        }
        gameCanvas.repaint();
    }

    private void initKeyListeners(){
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
            }
            @Override
            public void keyReleased(KeyEvent e) {
                pressedKeys.remove(e.getKeyCode());
            }
        });
    }
}
