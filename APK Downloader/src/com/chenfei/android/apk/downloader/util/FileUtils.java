package com.chenfei.android.apk.downloader.util;

import java.io.File;

public final class FileUtils
{
    public static boolean exists(final String path)
    {
        try
        {
            return new File(path).getCanonicalFile().exists();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private FileUtils()
    {
    }

}
