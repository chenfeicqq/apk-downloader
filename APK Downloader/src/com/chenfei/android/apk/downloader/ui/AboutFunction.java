package com.chenfei.android.apk.downloader.ui;

import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.chenfei.ui.Browser;
import com.chenfei.ui.function.Function;

public class AboutFunction extends Function implements HyperlinkListener
{
    public AboutFunction(final URL url)
    {
        super("关于");

        this.initPanel(url);
    }

    private void initPanel(final URL url)
    {
        JEditorPane aboutHTML = null;

        try
        {
            aboutHTML = new JEditorPane(url);
            aboutHTML.setEditable(false);
            aboutHTML.setBorder(null);
            aboutHTML.addHyperlinkListener(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        super.add(aboutHTML);
    }

    @Override
    public void hyperlinkUpdate(final HyperlinkEvent event)
    {
        if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
        {
            Browser.browse(event.getURL());
        }
    }

}
