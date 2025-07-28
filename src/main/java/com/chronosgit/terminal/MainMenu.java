package com.chronosgit.terminal;

import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

public class MainMenu {
    private void printLogo() {
        new AttributedString("+-------------------------------+\n" + //
                "| T A S K   ✿ ✿   M A N A G E R |\n" + //
                "+-------------------------------+\n" + //
                "", AttributedStyle.DEFAULT.bold().foreground(AttributedStyle.CYAN)).print(JLine.terminal);

        JLine.terminal.flush();
    }

    private void printAppInfo() {
        AttributedStringBuilder sb = new AttributedStringBuilder();

        sb.append("Welcome to ", AttributedStyle.DEFAULT);
        sb.append("Task Manager", AttributedStyle.DEFAULT.bold().foreground(AttributedStyle.CYAN));
        sb.append("!\nA simple CLI to help you ", AttributedStyle.DEFAULT);
        sb.append("organize tasks efficiently\n", AttributedStyle.DEFAULT.italic());
        sb.append("Made by © chronosgit\n", AttributedStyle.DEFAULT);

        sb.toAttributedString().print(JLine.terminal);
        JLine.terminal.flush();
    }

    public void printMainMenu() {
        printLogo();

        printAppInfo();

        JLine.terminal.writer().println();
        JLine.terminal.flush();
    }
}
