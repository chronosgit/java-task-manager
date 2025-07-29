package com.chronosgit.terminal;

import org.jline.utils.AttributedStyle;

import com.chronosgit.settings.Settings;

public class StylePreset {
    public static final AttributedStyle COMMAND = AttributedStyle.DEFAULT.bold().background(AttributedStyle.WHITE)
            .foreground(AttributedStyle.BLACK);

    public static int COLOR_MAIN;

    static {
        updateColorMain();
    }

    public static void updateColorMain() {
        String color = Settings.getPropertyFromSettings("color");

        switch (color) {
            case "BLUE":
                COLOR_MAIN = AttributedStyle.BLUE;

                break;
            case "MAGENTA":
                COLOR_MAIN = AttributedStyle.MAGENTA;

                break;
            case "YELLOW":
                COLOR_MAIN = AttributedStyle.YELLOW;

                break;
            default:
                COLOR_MAIN = AttributedStyle.CYAN;

                break;
        }
    }

}
