package com.uvolchyk.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateOfBirthGenerator {

    public static Date generateDateBetweenBounds(Integer upperBound, Integer lowerBound) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(upperBound, lowerBound);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return gc.getTime();
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
