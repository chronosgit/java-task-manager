package com.chronosgit.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ISODateVerifier {
    public static boolean verifyDate(String date) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate.parse(date, f);

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}