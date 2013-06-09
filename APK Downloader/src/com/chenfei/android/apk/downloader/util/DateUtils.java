package com.chenfei.android.apk.downloader.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils
{
    private static Map<String, SimpleDateFormat> dateformatCache = new HashMap<String, SimpleDateFormat>();

    public static String getNow(String pattern)
    {
        return getDateFormat(pattern).format(new Date());
    }

    private static SimpleDateFormat getDateFormat(String pattern)
    {
        SimpleDateFormat dateFormat = dateformatCache.get(pattern);
        if (null == dateFormat)
        {
            dateFormat = new SimpleDateFormat(pattern);
            dateformatCache.put(pattern, dateFormat);
        }
        return dateFormat;
    }

    private DateUtils()
    {
    }
}
