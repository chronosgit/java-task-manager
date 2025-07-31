package com.chronosgit.terminal;

import org.jline.utils.AttributedStyle;

import com.chronosgit.settings.SettingsHandler;

public class StylePreset {
    public static final AttributedStyle COMMAND = AttributedStyle.DEFAULT.bold().background(AttributedStyle.WHITE)
            .foreground(AttributedStyle.BLACK);
    public static final AttributedStyle ERROR = AttributedStyle.DEFAULT.foreground(AttributedStyle.RED + 60);
    public static final AttributedStyle WARNING = AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW);

    public static int COLOR_MAIN;

    static {
        try {
            updateColorMain();
        } catch (ExceptionInInitializerError e) {
            COLOR_MAIN = AttributedStyle.CYAN;
        }
    }

    public static void updateColorMain() {
        String color = SettingsHandler.getPropertyFromSettings("color");

        if (color == null) {
            COLOR_MAIN = AttributedStyle.CYAN;

            return;
        }

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
