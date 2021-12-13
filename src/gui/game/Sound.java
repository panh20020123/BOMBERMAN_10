package src.gui.game;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {

    String[] soundPaths = new String[20];
    Media sound;
    MediaPlayer mediaPlayer;

    public Sound() {
        soundPaths[0] = "./res/sound/JB Theme BBC.mp3";
        soundPaths[1] = "./res/sound/new_level.wav";
        soundPaths[2] = "./res/sound/bomb_set.mp3";
        soundPaths[3] = "./res/sound/bomb_timer.wav";
        soundPaths[4] = "./res/sound/bomb_explode.wav";
        soundPaths[5] = "./res/sound/player_explode.wav";
        soundPaths[6] = "./res/sound/power_up.wav";
        soundPaths[7] = "./res/sound/enemy_explode.wav";
        soundPaths[8] = "./res/sound/menu_bg_music.mp3";
        soundPaths[9] = "./res/sound/victory.wav";
    }

    public void setSound(int soundNumber) {
        sound = new Media(new File(soundPaths[soundNumber]).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
    }

    public void setSound(int soundNumber, double volume) {
        setSound(soundNumber);
        mediaPlayer.setVolume(volume);
    }

    public void play() {
        mediaPlayer.play();
    }

    public void loop() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void stop() {
        mediaPlayer.stop();
    }

}
