package com.tsybulko.model.bookproject.service.validator;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookValidator {
    private static final int MIN_YEAR = 1800;
    private static final int MAX_YEAR;
    private static final String BOOK_TITLE_REGEX = "[A-Za-z-!.,0-9\\s]{1,30}";
    private static final String AUTHOR_NAME_REGEX = "[A-Za-z-]{1,25}\\s[A-Za-z-]{1,25}";

    static {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(),
                Locale.getDefault());
        MAX_YEAR = calendar.get(Calendar.YEAR);
    }

    public static boolean validateYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    public static boolean validateTitle (String label) {
        if (label == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(BOOK_TITLE_REGEX);
        Matcher matcher = pattern.matcher(label);
        return matcher.matches();
    }

    public static boolean validateAuthor (String name) {
        if (name == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(AUTHOR_NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
