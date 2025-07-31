package com.chronosgit.music;

import java.util.Optional;

import org.jline.utils.AttributedString;

import com.chronosgit.audio.WavClipPlayer;
import com.chronosgit.terminal.JLine;
import com.chronosgit.terminal.StylePreset;

public class Playback {
    private static int curTrackListId = 0;

    static {
        for (WavClipPlayer t : TracksManager.getTracks()) {
            t.addClipEndListener(() -> {

            });
        }
    }

    public static void startPlaybackFromStart() {
        TracksManager.runIfTracksExist(() -> {
            curTrackListId = 0;

            WavClipPlayer t = TracksManager
                    .getTrackFromList(curTrackListId)
                    .get();

            TracksManager.stopAllTracks();

            t.playFromStart();

            renderCurTrackInfo();
        });
    }

    public static void startPlaybackFromTrack(String id) {
        Optional<WavClipPlayer> ot = TracksManager.getTrackById(id);

        if (ot.isEmpty()) {
            new AttributedString("\nTrack \"" + id + "\" not found.\nUnable to start playback.\n\n", StylePreset.ERROR)
                    .print(JLine.terminal);
            JLine.terminal.flush();

            return;
        }

        WavClipPlayer t = ot.get();

        int trackListId = TracksManager.getTrackListId(t);

        if (trackListId == -1) {
            new AttributedString("\nTrack wasn't found in the list.\nUnable to start playback.\n\n", StylePreset.ERROR)
                    .print(JLine.terminal);
            JLine.terminal.flush();

            return;
        }

        TracksManager.stopAllTracks();

        curTrackListId = trackListId;
        t.playFromStart();

        renderCurTrackInfo();
    }

    public static void loopCurTrack() {
        TracksManager.runIfTracksExist(() -> {
            WavClipPlayer t = TracksManager
                    .getTrackFromList(curTrackListId)
                    .get();

            t.loop();

            JLine.terminal.writer().write("\nLooped.\n\n");
            JLine.terminal.flush();
        });
    }

    public static void unloopCurTrack() {
        TracksManager.runIfTracksExist(() -> {
            WavClipPlayer t = TracksManager
                    .getTrackFromList(curTrackListId)
                    .get();

            t.unloop();

            JLine.terminal.writer().write("\nUnlooped.\n\n");
            JLine.terminal.flush();
        });
    }

    public static void restartCurTrack() {
        TracksManager.runIfTracksExist(() -> {
            WavClipPlayer t = TracksManager
                    .getTrackFromList(curTrackListId)
                    .get();

            t.playFromStart();

            renderCurTrackInfo();
        });
    }

    public static void pauseCurTrack() {
        TracksManager.runIfTracksExist(() -> {
            WavClipPlayer t = TracksManager
                    .getTrackFromList(curTrackListId)
                    .get();

            t.pause();

            JLine.terminal.writer().write("\nPaused.\n\n");
            JLine.terminal.flush();
        });
    }

    public static void continueCurTrack() {
        TracksManager.runIfTracksExist(() -> {
            WavClipPlayer t = TracksManager
                    .getTrackFromList(curTrackListId)
                    .get();

            t.resume();

            JLine.terminal.writer().write("\nContinuing.\n\n");
            JLine.terminal.flush();
        });
    }

    public static void playNextTrack() {
        TracksManager.runIfTracksExist(() -> {
            WavClipPlayer t = TracksManager
                    .getTrackFromList(curTrackListId)
                    .get();

            if (t.isPlaying()) {

            }

            int trackListId = TracksManager.getTrackListId(t);

            if (trackListId == -1) {
                new AttributedString("\nCurrent track wasn't found in the list.\nUnable to play the text track.\n\n",
                        StylePreset.ERROR)
                        .print(JLine.terminal);
                JLine.terminal.flush();

                return;
            }

            if (trackListId + 1 == TracksManager.getTrackListSize()) {
                trackListId = 0;
            }
        });
    }

    public static void stopPlayback() {
        curTrackListId = 0;

        TracksManager.stopAllTracks();

        JLine.terminal.writer().write("\nStopped the playback.\n\n");
        JLine.terminal.flush();
    }

    public static void renderCurTrackInfo() {
        Optional<WavClipPlayer> ot = TracksManager.getTrackFromList(curTrackListId);

        if (ot.isEmpty()) {
            JLine.terminal.writer().write("\nCurrent track not found.\n\n");
            JLine.terminal.flush();

            return;
        }

        WavClipPlayer t = ot.get();

        String name = t.getFileName();
        String elapsed = t.getElapsedTime();
        String total = t.getClipTime();
        String loopInfo = t.isLooping() ? "Looped." : "";

        JLine.terminal.writer().write(
                "\nQueued: \"" + name + "\" [" + elapsed + " / " + total + "]. " + loopInfo + "\n\n");
        JLine.terminal.flush();
    }

    public static int getCurTrackListId() {
        return curTrackListId;
    }
}
