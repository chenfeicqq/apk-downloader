package com.chenfei.android.apk.downloader.ui.home.search;

import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.APKDownloader;
import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.config.ConfigUtils;
import com.chenfei.android.apk.downloader.session.SessionUtils;
import com.chenfei.android.apk.downloader.ui.i18n.I18N;

public class SearchWorker extends SwingWorker<List<App>, Void>
{
    private static final Logger LOG   = Logger.getLogger(SearchWorker.class);

    @Override
    protected List<App> doInBackground()
    {
        APKDownloader.setStatus(I18N.get("search.ing"));

        try
        {
            // 保存搜索信息
            ConfigUtils.saveConfig(null);

            return SessionUtils.getSession().searchApps();
        }
        catch (Exception e)
        {
            LOG.error("搜索失败", e);

            APKDownloader.setStatus(I18N.get("search.unsuccessful"));

            return null;
        }
    }
}