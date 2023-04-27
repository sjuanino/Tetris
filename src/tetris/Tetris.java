package tetris;

import javax.swing.JOptionPane;

public class Tetris {
    
    private static GameForm gameForm;
    private static ScoreForm scoreForm;
    private static StartupForm startupForm;
    private static AudioPlayer audioPlayer = new AudioPlayer();
    
    public Tetris() {
    }
    
    public static void start() {
        gameForm.setVisible(true);
        gameForm.startGame();
    }
    
    public static void showScoreBoard() {
        scoreForm.setVisible(true);
    }
    
    public static void showStartup() {
        startupForm.setVisible(true);
    }
    
    public static void gameOver(int score) {
        playGameOver();
        String playerName = JOptionPane.showInputDialog("GAME OVER\nPlease enter your name");
        scoreForm.addPlayerName(playerName, score);
        gameForm.setVisible(false);
    }
    
    public static void playClear() {
        audioPlayer.playClearLine();
    }
    
    public static void playGameOver() {
        audioPlayer.playGameOver();
    } 
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameForm = new GameForm();
                scoreForm = new ScoreForm();
                startupForm = new StartupForm();
                
                startupForm.setVisible(true);
            }
        });
    }
    
}
