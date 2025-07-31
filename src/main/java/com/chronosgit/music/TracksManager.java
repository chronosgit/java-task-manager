package com.chronosgit.music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jline.utils.*;

import com.chronosgit.audio.WavClipPlayer;
import com.chronosgit.terminal.JLine;
import com.chronosgit.terminal.StylePreset;

public class TracksManager {
    private static List<WavClipPlayer> tracks;
    private static Map<String, String> tracksMeta;

    static {
        tracks = new ArrayList<>();
        tracksMeta = new HashMap<>();

        loadTracksMeta();
        loadTracks();
    }

    public static boolean isTracksListEmpty() {
        return tracks.isEmpty();
    }

    public static void runIfTracksExist(Runnable action) {
        if (isTracksListEmpty()) {
            new AttributedString(
                    "\nNo tracks available to play.\nOperation cancelled.\n\n",
                    StylePreset.ERROR).print(JLine.terminal);

            JLine.terminal.flush();
            return;
        }

        action.run();
    }

    public static List<WavClipPlayer> getTracks() {
        return tracks;
    }

    public static int getTrackListSize() {
        return tracks.size();
    }

    public static Optional<WavClipPlayer> getTrackFromList(int index) {
        if (index < 0 || index >= tracks.size()) {
            return Optional.empty();
        }

        return Optional.of(tracks.get(index));
    }

    public static Optional<WavClipPlayer> getTrackById(String id) {
        for (WavClipPlayer t : tracks) {
            if (t.getId().equals(id)) {
                return Optional.of(t);
            }
        }

        return Optional.empty();
    }

    public static int getTrackListId(WavClipPlayer t) {
        for (int i = 0; i < tracks.size(); i++) {
            if (tracks.get(i) == t) {
                return i;
            }
        }
        return -1;
    }

    public static void stopAllTracks() {
        for (WavClipPlayer t : tracks) {
            t.stop();
        }
    }

    public static void renderTrackList() {
        runIfTracksExist(() -> {
            JLine.terminal.writer().write("\nList of available tracks:\n\n");

            for (WavClipPlayer t : tracks) {
                String id = t.getId();
                String name = t.getFileName();

                JLine.terminal.writer().write(String.format("ID: %s\nName: %s\n", id, name));
            }

            JLine.terminal.writer().println();
            JLine.terminal.flush();
        });
    }

    private static void loadTracksMeta() {
        tracksMeta.put(
                "src/main/resources/persona_5_beneath_the_mask.wav",
                "Persona 5. Beneath the Mask.");
    }

    private static void loadTracks() {
        for (Map.Entry<String, String> e : tracksMeta.entrySet()) {
            try {
                WavClipPlayer wcp = new WavClipPlayer(e.getKey(), e.getValue());

                tracks.add(wcp);
            } catch (Exception ex) {
                new AttributedString("\nCouldn't initialize track: \"" + e.getValue() +
                        "\".\n",
                        StylePreset.WARNING).print(JLine.terminal);
                JLine.terminal.flush();
            }
        }
    }
}
