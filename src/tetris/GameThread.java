package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class GameThread extends Thread {
    
    private GameArea gameArea;

    public GameThread(GameArea gameArea) {
        this.gameArea = gameArea;
    }   
    
    @Override
    public void run() {
        while (true) {
            gameArea.spawnBlock();
            while (gameArea.moveBlockDown()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
