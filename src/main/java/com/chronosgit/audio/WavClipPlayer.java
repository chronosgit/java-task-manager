package com.chronosgit.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.chronosgit.utils.IdGenerator;

public class WavClipPlayer {
    private AudioInputStream ais;
    private Clip clip;

    private String filePath;
    private String fileName;
    private String id;

    private int pausePosition = 0;
    private boolean isLooping = false;

    public WavClipPlayer(String filePath, String fileName) throws RuntimeException {
        this.filePath = filePath;
        this.fileName = fileName;
        this.id = IdGenerator.generateRandomHash();

        loadClip();
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getId() {
        return this.id;
    }

    public String getClipTime() {
        long microseconds = this.clip.getMicrosecondLength();

        int totalSeconds = (int) (microseconds / 1_000_000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public String getElapsedTime() {
        AudioFormat format = this.clip.getFormat();
        float frameRate = format.getFrameRate();

        int totalSeconds = (int) (this.pausePosition / frameRate);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public boolean isLooping() {
        return this.isLooping;
    }

    public boolean isPlaying() {
        return this.clip.isRunning();
    }

    public void playFromStart() {
        this.clip.setFramePosition(0);

        this.clip.start();
    }

    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);

        isLooping = true;
    }

    public void unloop() {
        this.clip.loop(0);

        this.isLooping = false;
    }

    public void pause() {
        this.pausePosition = clip.getFramePosition();

        this.clip.stop();
    }

    public void stop() {
        this.clip.stop();
        this.clip.setFramePosition(0);
        pausePosition = 0;

        this.isLooping = false;
    }

    public void resume() {
        this.clip.setFramePosition(pausePosition);

        this.clip.start();
    }

    public void addClipEndListener(Runnable callback) {
        this.clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                if (clip.getFramePosition() >= clip.getFrameLength()) {
                    callback.run();
                }
            }
        });
    }

    private void loadClip() throws RuntimeException {
        try {
            this.ais = AudioSystem.getAudioInputStream(new File(this.filePath));

            this.clip = AudioSystem.getClip();

            this.clip.open(this.ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("Failed to load audio", e); // or custom exception
        }
    }
}
