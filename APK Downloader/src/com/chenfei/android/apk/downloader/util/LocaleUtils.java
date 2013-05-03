package com.chenfei.android.apk.downloader.util;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public final class LocaleUtils
{
    private static final Map<String, Locale> localeMap = new LinkedHashMap<String, Locale>(10);

    static
    {
        localeMap.put("默认", Locale.getDefault());
        localeMap.put("中国", Locale.CHINA);
        localeMap.put("中国-台湾", Locale.TAIWAN);
        localeMap.put("美国", Locale.US);
        localeMap.put("英国", Locale.UK);
        localeMap.put("法国", Locale.FRANCE);
        localeMap.put("德国", Locale.GERMANY);
        localeMap.put("意大利", Locale.ITALY);
        localeMap.put("加拿大", Locale.CANADA);
        localeMap.put("韩国", Locale.KOREA);
        localeMap.put("日本", Locale.JAPAN);
    }

    public static String[] getNames()
    {
        return localeMap.keySet().toArray(new String[10]);
    }

    public static Locale getLocale(final String name)
    {
        return localeMap.get(name);
    }

    private LocaleUtils()
    {
    }
}
