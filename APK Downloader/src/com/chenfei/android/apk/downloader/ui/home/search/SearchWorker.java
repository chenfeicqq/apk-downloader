package com.chenfei.android.apk.downloader.ui.home.search;

import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.APKDownloader;
import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.session.Session.Search;
import com.chenfei.android.apk.downloader.session.SessionUtil;

public class SearchWorker extends SwingWorker<List<App>, Void>
{
    private static final Logger LOG   = Logger.getLogger(SearchWorker.class);

    private Search              search;

    public SearchWorker(Search search)
    {
        this.search = search;
    }

    @Override
    protected List<App> doInBackground()
    {
        APKDownloader.setStatus("搜索中，请稍等！");

        try
        {
            return SessionUtil.getSession().searchApps(this.search);
        }
        catch (Exception e)
        {
            LOG.error("搜索错误", e);

            APKDownloader.setStatus("搜索失败！");

            return null;
        }
    }
}