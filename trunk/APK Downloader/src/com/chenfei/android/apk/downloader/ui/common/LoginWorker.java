package com.chenfei.android.apk.downloader.ui.common;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.APKDownloader;
import com.chenfei.android.apk.downloader.session.SessionUtils;
import com.chenfei.android.apk.downloader.ui.i18n.I18N;

public class LoginWorker extends SwingWorker<Boolean, Void>
{
    private static final Logger LOG = Logger.getLogger(LoginWorker.class);

    /** 登录失败 */
    public static final String SUCCESSFUL = "successful";

    @Override
    protected Boolean doInBackground()
        throws Exception
    {
        APKDownloader.setStatus(I18N.get("login.ing"));

        SessionUtils.refreshSession();

        return SessionUtils.getSession().isLogged();
    }

    @Override
    protected void done()
    {
        try
        {
            if (this.get())
            {
                APKDownloader.setStatus(I18N.get("login.successful"));

                this.firePropertyChange(SUCCESSFUL, false, true);
            }
            else
            {
                APKDownloader.setStatus(I18N.get("login.unsuccessful"));
            }
        }
        catch (Exception e)
        {
            LOG.error("登录失败", e);

            APKDownloader.setStatus(I18N.get("login.unsuccessful"));
        }
    }
}
