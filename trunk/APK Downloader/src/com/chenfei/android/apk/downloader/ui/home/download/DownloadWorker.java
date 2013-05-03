package com.chenfei.android.apk.downloader.ui.home.download;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.session.Session.DownloadException;
import com.chenfei.android.apk.downloader.session.Session.DownloadListener;
import com.chenfei.android.apk.downloader.session.SessionUtil;

public class DownloadWorker extends SwingWorker<Void, Void>
{
    /** 下载错误 */
    public static final String ERROR = "error";

    private static final Logger LOG = Logger.getLogger(DownloadWorker.class);

    private App app;

    private DownloadListener listener;

    private String message;

    public DownloadWorker(final App app, final DownloadListener listener)
    {
        this.app = app;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground()
        throws Exception
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Download starting.");
        }

        try
        {
            SessionUtil.getSession().downloadApp(this.app, this.listener);
        }
        catch (DownloadException e)
        {
            this.message = e.getMessage();

            this.firePropertyChange(ERROR, false, true);
        }
        catch (Exception e)
        {
            LOG.error("Download app error.", e);

            this.firePropertyChange(ERROR, false, true);
        }
        finally
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("Download ended.");
            }
        }

        return null;
    }

    public String getMessage()
    {
        return this.message;
    }
}
