package com.chenfei.android.apk.downloader.util;

public final class ThreadUtils
{
    public static boolean isInterrupted()
    {
        return Thread.currentThread().isInterrupted();
    }

    public static boolean sleep(final long millis)
    {
        try
        {
            Thread.sleep(millis);

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private ThreadUtils()
    {
    }
}
