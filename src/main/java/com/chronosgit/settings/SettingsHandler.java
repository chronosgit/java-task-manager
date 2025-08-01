package com.chronosgit.settings;

import java.util.Properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.chronosgit.terminal.JLine;
import com.chronosgit.terminal.StylePreset;

public class SettingsHandler {
    private static String filePath = "src/main/.data/.settings.properties";
    public static String[] allowedColors = new String[] { "BLUE", "MAGENTA", "YELLOW", "CYAN" };

    private static void loadProperties(Properties props) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
        } catch (IOException e) {
            return;
        }
    }

    private static boolean storeProperties(Properties props) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            props.store(fos, "Stored property");
        } catch (IOException e) {

            return false;
        }

        return true;
    }

    public static String getPropertyFromSettings(String key) {
        Properties props = new Properties();

        loadProperties(props);

        return props.getProperty(key);
    }

    public static void renderColor() {
        String color = SettingsHandler.getPropertyFromSettings("color");

        if (color == null || color.isEmpty()) {
            JLine.terminal.writer().write("\nLooks like you haven't set a color yet.\n\n");
        } else {
            JLine.terminal.writer().write("\nThe current color: " + color + ".\n\n");
        }

        JLine.terminal.flush();
    }

    public static void renderColorList() {
        JLine.terminal.writer().write("\nAllowed colors: BLUE, MAGENTA, CYAN.\n\n");
        JLine.terminal.flush();
    }

    public static void renderUsername() {
        String uname = SettingsHandler.getPropertyFromSettings("username");

        if (uname == null || uname.isEmpty()) {
            JLine.terminal.writer().write("\nLooks like you haven't set a username yet.\n\n");
        } else {
            JLine.terminal.writer().write("\nThe current user's username: " + uname + ".\n\n");
        }

        JLine.terminal.flush();
    }

    public static boolean setProperty(String key, String value) {
        Properties p = new Properties();

        SettingsHandler.loadProperties(p);

        p.setProperty(key, value);

        return SettingsHandler.storeProperties(p);
    }

    public static void setColor(String newColor) {
        newColor = newColor.toUpperCase();

        boolean flag = false;

        for (String ac : SettingsHandler.allowedColors) {
            if (ac.equals(newColor)) {
                flag = true;
            }
        }

        if (!flag) {
            JLine.terminal.writer().write("\nInvalid color.\nAllowed colors: BLUE, MAGENTA, YELLOW, CYAN.\n\n");
            JLine.terminal.flush();

            return;
        }

        if (!setProperty("color", newColor)) {
            return;
        }

        StylePreset.updateColorMain();

        JLine.terminal.writer().write("\nColor \"" + newColor + "\" has been successfully set.\n");
        JLine.terminal.writer().write("P.S. Earlier messages will stay in their original color.\n\n");
    }

    public static void setUsername(String newUsername) {
        if (newUsername == null || newUsername.isEmpty()) {
            JLine.terminal.writer().write("\nUsername must not be empty.\n\n");
            JLine.terminal.flush();

            return;
        }

        if (!setProperty("username", newUsername)) {
            return;
        }

        JLine.terminal.writer().write("\nUsername \"" + newUsername + "\" has been successfully set.\n\n");
    }
}
