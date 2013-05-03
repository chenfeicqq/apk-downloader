package com.chenfei.android.apk.downloader.ui.common;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.APKDownloader;
import com.chenfei.android.apk.downloader.session.SessionUtil;

public class LoginWorker extends SwingWorker<Boolean, Void>
{
    private static final Logger LOG = Logger.getLogger(LoginWorker.class);

    /** 登录失败 */
    public static final String SUCCESSFUL = "successful";

    @Override
    protected Boolean doInBackground()
        throws Exception
    {
        APKDownloader.setStatus("正在登录...");

        SessionUtil.refreshSession();

        return SessionUtil.getSession().isLogged();
    }

    @Override
    protected void done()
    {
        try
        {
            if (this.get())
            {
                APKDownloader.setStatus("登录成功");

                this.firePropertyChange(SUCCESSFUL, false, true);
            }
            else
            {
                APKDownloader.setStatus("登录失败");
            }
        }
        catch (Exception e)
        {
            LOG.error("登录失败", e);

            APKDownloader.setStatus("登录失败");
        }
    }
}
