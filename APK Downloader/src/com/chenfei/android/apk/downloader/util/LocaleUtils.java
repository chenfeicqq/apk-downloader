package com.chenfei.android.apk.downloader.util;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import com.chenfei.android.apk.downloader.ui.i18n.I18N;

public final class LocaleUtils
{
    private static final Map<Locale, String> LOCALE_NAME_MAP = new LinkedHashMap<Locale, String>(10);

    private static final Map<String, Locale> NAME_LOCALE_MAP = new LinkedHashMap<String, Locale>(10);

    static
    {
        LOCALE_NAME_MAP.put(Locale.CHINA, I18N.get("locale.china"));
        LOCALE_NAME_MAP.put(Locale.TAIWAN, I18N.get("locale.china.taiwan"));
        LOCALE_NAME_MAP.put(Locale.US, I18N.get("locale.US"));
        LOCALE_NAME_MAP.put(Locale.UK, I18N.get("locale.UK"));
        LOCALE_NAME_MAP.put(Locale.FRANCE, I18N.get("locale.france"));
        LOCALE_NAME_MAP.put(Locale.GERMANY, I18N.get("locale.germany"));
        LOCALE_NAME_MAP.put(Locale.ITALY, I18N.get("locale.italy"));
        LOCALE_NAME_MAP.put(Locale.CANADA, I18N.get("locale.canada"));
        LOCALE_NAME_MAP.put(Locale.KOREA, I18N.get("locale.korea"));
        LOCALE_NAME_MAP.put(Locale.JAPAN, I18N.get("locale.japan"));

        // 如果当前系统默认Locale不在已知的名称列表中，则添加一个默认项
        if (!LOCALE_NAME_MAP.containsKey(Locale.getDefault()))
        {
            LOCALE_NAME_MAP.put(Locale.getDefault(), I18N.get("locale.default"));
            NAME_LOCALE_MAP.put(I18N.get("locale.default"), Locale.getDefault());
        }

        NAME_LOCALE_MAP.put(I18N.get("locale.china"), Locale.CHINA);
        NAME_LOCALE_MAP.put(I18N.get("locale.china.taiwan"), Locale.TAIWAN);
        NAME_LOCALE_MAP.put(I18N.get("locale.US"), Locale.US);
        NAME_LOCALE_MAP.put(I18N.get("locale.UK"), Locale.UK);
        NAME_LOCALE_MAP.put(I18N.get("locale.france"), Locale.FRANCE);
        NAME_LOCALE_MAP.put(I18N.get("locale.germany"), Locale.GERMANY);
        NAME_LOCALE_MAP.put(I18N.get("locale.italy"), Locale.ITALY);
        NAME_LOCALE_MAP.put(I18N.get("locale.canada"), Locale.CANADA);
        NAME_LOCALE_MAP.put(I18N.get("locale.korea"), Locale.KOREA);
        NAME_LOCALE_MAP.put(I18N.get("locale.japan"), Locale.JAPAN);
    }

    public static String[] getLocaleNames()
    {
        return NAME_LOCALE_MAP.keySet().toArray(new String[10]);
    }

    public static Locale getLocale(final String name)
    {
        return NAME_LOCALE_MAP.get(name);
    }

    public static String getName(Locale locale)
    {
        return LOCALE_NAME_MAP.get(locale);
    }

    private LocaleUtils()
    {
    }
}
