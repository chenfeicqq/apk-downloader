package com.chenfei.android.apk.downloader.session;

import com.chenfei.android.apk.downloader.config.ConfigUtil;

public final class SessionUtil
{
    private static Session session;

    static
    {
        refreshSession();
    }

    /** 获取Session
     * @return Session
     */
    public static Session getSession()
    {
        return session;
    }

    /**
     * 刷新Session
     */
    public static void refreshSession()
    {
        session = new Session(ConfigUtil.getConfig());
    }

    private SessionUtil()
    {
    }
}
