package com.chronosgit.terminal.enums;

public enum Color {
    RED("\033[31m"),
    BRIGHT_CYAN("\033[96m");

    private final String ansiCode;

    Color(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String getAnsiCode() {
        return this.ansiCode;
    }
}
