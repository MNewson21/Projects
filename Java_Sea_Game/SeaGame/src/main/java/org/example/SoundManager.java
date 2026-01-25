package org.example;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;



public class SoundManager {

    private MediaPlayer mediaPlayer;

    public void playBackgroundMusic(String filename) {
        String path = Paths.get("src/main/resources/Sounds/" + filename).toUri().toString();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);

        //so the music loops indefinitely
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void resumeMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }
}

