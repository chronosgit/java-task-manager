package com.chronosgit.app;

import org.jline.utils.*;

import com.chronosgit.terminal.*;

class StartupMessageRenderer {
    private static void renderLogo() {
        new AttributedString("+-------------------------------+\n" + //
                "| T A S K   ✿ ✿   M A N A G E R |\n" + //
                "+-------------------------------+\n" + //
                "", AttributedStyle.DEFAULT.bold().foreground(StylePreset.COLOR_MAIN)).print(JLine.terminal);

        JLine.terminal.flush();
    }

    private static void renderWelcome() {
        AttributedStringBuilder sb = new AttributedStringBuilder();

        sb.append("Welcome to ");
        sb.append("Task Manager", AttributedStyle.DEFAULT.bold().foreground(StylePreset.COLOR_MAIN));
        sb.append("!\nA simple CLI to help you ");
        sb.append("organize tasks efficiently.", AttributedStyle.DEFAULT.italic());
        sb.append("\nMade by © chronosgit.\n\n");

        sb.toAttributedString().print(JLine.terminal);

        JLine.terminal.flush();
    }

    private static void renderHelp() {
        AttributedStringBuilder sb = new AttributedStringBuilder();

        sb.append("Type ");
        sb.append("help", StylePreset.COMMAND);
        sb.append(" to view available commands.\n\n");

        sb.toAttributedString().print(JLine.terminal);

        JLine.terminal.flush();
    }

    static void renderStartupMessage() {
        renderLogo();

        renderWelcome();

        renderHelp();
    }
}
