package com.gurianova.aquariumapi.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    static String timestamp = "dd-MM-yyyy";
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timestamp);

    public static java.sql.Timestamp parseTimestamp(String timestamp) {
        try {
            return new Timestamp(simpleDateFormat.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
