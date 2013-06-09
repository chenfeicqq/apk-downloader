package com.chenfei.android.apk.downloader.ui;

import java.awt.BorderLayout;

import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.ui.home.DownloadFunction;
import com.chenfei.android.apk.downloader.ui.home.SearchFunction;
import com.chenfei.android.apk.downloader.ui.i18n.I18N;
import com.chenfei.ui.CSS;
import com.chenfei.ui.base.panel.SplitPanel;
import com.chenfei.ui.function.Function;

public class HomeFunction extends Function
{
    private SplitPanel       panel;

    private SearchFunction   search;

    private DownloadFunction download;

    public HomeFunction()
    {
        super(I18N.get("menu.home"));
        super.setHome(true);

        super.getPanel().setLayout(new BorderLayout());

        this.initSearch();
        this.initDownload();

        this.panel = new SplitPanel(new CSS().setWidth(865).setHeight(485));
        this.panel.setOneTouchExpandable(true);
        this.panel.setEnabled(false);
        this.panel.setDividerLocation(865);
        this.panel.setBorder(null);
        this.panel.setDividerSize(10);

        this.panel.setLeft(this.search);
        this.panel.setRight(this.download);

        super.add(this.panel);
    }

    private void initSearch()
    {
        this.search = new SearchFunction()
        {
            /** 默认序列化版本 */
            private static final long serialVersionUID = 1L;

            @Override
            protected void download(final App app)
            {
                HomeFunction.this.download.addDownload(app);
            }
        };
    }

    private void initDownload()
    {
        this.download = new DownloadFunction();
    }
}
