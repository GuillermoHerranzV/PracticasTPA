package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * CLASE PARA EL SONIDO QUE IMPLEMENTA LA INTERFAZ Playable
 */
public class Sonido implements Playable { 

    private Clip clip;
    private final URL[] URLsonido = new URL[30];

    public Sonido() {
        URLsonido[0] = getClass().getResource("/sonido/BlueBoyAdventure.wav");
        URLsonido[1] = getClass().getResource("/sonido/coin.wav");
        URLsonido[2] = getClass().getResource("/sonido/powerup.wav");
        URLsonido[3] = getClass().getResource("/sonido/unlock.wav");
        URLsonido[4] = getClass().getResource("/sonido/fanfare.wav");
    }

    //METODOS BASICOS DE LA INTERFAZ PLAYABLE
    
    @Override
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(URLsonido[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void play() {
        clip.start();
    }

    @Override
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void stop() {
        clip.stop();
    }
}