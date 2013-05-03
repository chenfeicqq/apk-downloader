package com.chenfei.android.apk.downloader.util;

import java.io.Closeable;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Scanner;

public final class IOUtils
{
    public static void close(final Closeable... closeables)
    {
        for (Closeable closeable : closeables)
        {
            try
            {
                if (closeable != null)
                {
                    closeable.close();
                }
            }
            catch (IOException ioe)
            {
            }
        }
    }

    public static void close(final URLConnection connection)
    {
        if (connection instanceof HttpURLConnection)
        {
            ((HttpURLConnection) connection).disconnect();
        }
    }

    public static void close(final Scanner scanner)
    {
        if (scanner != null)
        {
            scanner.close();
        }
    }

    private IOUtils()
    {
    }

}
