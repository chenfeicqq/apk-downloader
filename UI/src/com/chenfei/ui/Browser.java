package com.chenfei.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class Browser
{
    public static void browse(final URI uri)
    {
        try
        {
            Desktop.getDesktop().browse(uri);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void browse(final URL url)
    {
        try
        {
            browse(url.toURI());
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
    }

    public static void browse(final String url)
    {
        try
        {
            browse(new URI(url));
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
    }
}
