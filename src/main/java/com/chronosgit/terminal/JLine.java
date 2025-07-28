package com.chronosgit.terminal;

import java.io.IOException;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class JLine {
    static Terminal terminal;
    static LineReader reader;

    static {
        try {
            terminal = TerminalBuilder
                    .builder()
                    .system(true)
                    .build();
            reader = LineReaderBuilder
                    .builder()
                    .terminal(terminal)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize JLine terminal", e);
        }
    }
}
