package com.chronosgit.utils;

public class CSVEscaper {
    public static String escapeCsv(String input) {
        if (input.contains(",") || input.contains("\"") || input.contains("\n")) {
            input = input.replace("\"", "\"\"");

            return "\"" + input + "\"";
        }

        return input;
    }
}
