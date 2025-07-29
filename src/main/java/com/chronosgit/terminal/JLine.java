package com.chronosgit.terminal;

import java.io.IOException;

import org.jline.terminal.*;
import org.jline.reader.*;

public class JLine {
    static final public Terminal terminal;
    static final public LineReader reader;

    static {
        try {
            terminal = TerminalBuilder
                    .builder()
                    .system(true)
                    .build();

            reader = LineReaderBuilder
                    .builder()
                    .terminal(terminal)
                    .appName("Task Manager")
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize JLine terminal and line reader", e);
        }
    }

    static public void clearScreen() {
        terminal.puts(org.jline.utils.InfoCmp.Capability.clear_screen);
        terminal.flush();
    }
}
