package com.chenfei.android.apk.downloader.ui.home.download;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.session.Session.DownloadException;
import com.chenfei.android.apk.downloader.session.Session.DownloadListener;
import com.chenfei.android.apk.downloader.session.SessionUtils;

public class DownloadWorker extends SwingWorker<Void, Void>
{
    /** 下载错误 */
    public static final String ERROR = "error";

    private static final Logger LOG = Logger.getLogger(DownloadWorker.class);

    private App app;

    private DownloadListener listener;

    private String status;

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
            SessionUtils.getSession().downloadApp(this.app, this.listener);
        }
        catch (DownloadException e)
        {
            this.status = e.getMessage();

            this.firePropertyChange(ERROR, false, true);
        }
        catch (Exception e)
        {
            LOG.error("Download app error.", e);

            // 部分情况下：当下载尚未开始，取消下载会抛出异常
            // 如果下载已取消，则跳过异常处理
            if (!this.isCancelled())
            {
                this.firePropertyChange(ERROR, false, true);
            }
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

    public String getStatus()
    {
        return this.status;
    }
}
