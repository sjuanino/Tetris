package tetris;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    
    private String soundsFolder = "tetrissounds" + File.separator;
    private String clearLinePath = soundsFolder + "clear.wav";
    private String gameOverPath = soundsFolder + "success.wav";
    private Clip clearLineSound;
    private Clip gameOverSound;
    

    public AudioPlayer() {
        try {
            clearLineSound = AudioSystem.getClip();
            gameOverSound = AudioSystem.getClip();
            
            clearLineSound.open(AudioSystem.getAudioInputStream(new File(clearLinePath).getAbsoluteFile()));
            gameOverSound.open(AudioSystem.getAudioInputStream(new File(gameOverPath).getAbsoluteFile()));
            
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playClearLine() {
        clearLineSound.setFramePosition(0);
        clearLineSound.start();
    }
    
    public void playGameOver() {
        gameOverSound.setFramePosition(0);
        gameOverSound.start();
    }

}
