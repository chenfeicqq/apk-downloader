package com.chenfei.android.apk.downloader.util;

public class StringUtils
{
    public static boolean isEquals(String stringOne, String stringTwo)
    {
        if (null != stringOne)
        {
            return stringOne.equals(stringTwo);
        }

        if (null != stringTwo)
        {
            return stringTwo.equals(stringOne);
        }

        return true;
    }

    public static boolean isBlank(String string)
    {
        return null == string || 0 == string.trim().length();
    }

    public static boolean isNotBlank(String string)
    {
        return !isBlank(string);
    }

    private StringUtils()
    {
    }
}
