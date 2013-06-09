package com.chenfei.android.apk.downloader.session;

import com.chenfei.android.apk.downloader.config.ConfigUtils;

public final class SessionUtils
{
    private static Session session;

    static
    {
        refreshSession();
    }

    /** 获取Session
     */
    public static Session getSession()
    {
        return session;
    }

    /** 刷新Session
     */
    public static void refreshSession()
    {
        session = new Session(ConfigUtils.getConfig());
    }


    private SessionUtils()
    {
    }
}
