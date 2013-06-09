package com.chenfei.android.apk.downloader.ui.home;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;

import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.ui.home.download.DownloadItem;
import com.chenfei.ui.Listener;
import com.chenfei.ui.base.panel.Panel;
import com.chenfei.ui.base.panel.ScrollPane;

public class DownloadFunction extends ScrollPane
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private Map<String, App>  downloadMap      = new LinkedHashMap<String, App>();

    private Panel             panel            = new Panel();

    public DownloadFunction()
    {
        super();

        super.setMinimumSize(new Dimension(210, super.getMinimumSize().height));
        super.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        super.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        super.getViewport().add(this.panel);
    }

    public void addDownload(final App app)
    {
        String appID = app.getID();

        if (this.downloadMap.containsKey(appID))
        {
            return;
        }

        this.downloadMap.put(app.getID(), app);
        this.panel.add(this.getDownloadItem(app));
        this.refreshUI();
    }

    private DownloadItem getDownloadItem(App app)
    {
        final DownloadItem item = new DownloadItem(app);

        item.addPropertyChangeListener(DownloadItem.DELETEED, new Listener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent event)
            {
                DownloadFunction.this.deleteDownload(item);
            }
        });

        return item;
    }

    private void deleteDownload(DownloadItem item)
    {
        this.downloadMap.remove(item.getAppID());
        this.panel.remove(item);

        this.refreshUI();
    }

    /**
     * 根据当前的下载数量重新计算Panel的高度，并刷新UI
     */
    private void refreshUI()
    {
        this.panel.setPreferredSize(new Dimension(this.panel.getWidth(), 40 * this.downloadMap.size()));
        this.panel.updateUI();
        super.updateUI();
    }
}
