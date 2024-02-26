package com.gussoft.farmaciabe.core.utils;

import static com.gussoft.farmaciabe.core.utils.Constrains.FORMAT_DATE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String getFormatDate(LocalDateTime localdatetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        return localdatetime.format(formatter);
    }

    public static String getFormatDateNow() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        return localDateTime.format(formatter);
    }

    public static LocalDateTime getLocalDateNow() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        String format = date.format(formatter);
        return LocalDateTime.parse(format, formatter);
    }
}
