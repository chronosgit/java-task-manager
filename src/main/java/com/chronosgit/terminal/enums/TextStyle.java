package com.chronosgit.terminal.enums;

public enum TextStyle {
    ITALIC("\u001b[3m"),
    BOLD("\u001b[1m"),
    UNDELINED("\u001b[4m");

    private final String ansiCode;

    TextStyle(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String getAnsiCode() {
        return this.ansiCode;
    }
}
