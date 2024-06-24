package com.huuphuoc.webBH.common.url;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFomat {


    public final static String DATETIME_FOMAT = "dd-mm-yyyy hh:mm:ss";

    static LocalDateTime localDateTime = LocalDateTime.now();
    public static String strDateTime = localDateTime.format(DateTimeFormatter.ofPattern(DATETIME_FOMAT));


}
