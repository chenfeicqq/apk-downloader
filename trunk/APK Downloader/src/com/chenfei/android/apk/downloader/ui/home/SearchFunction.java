package com.chenfei.android.apk.downloader.ui.home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Locale;

import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.APKDownloader;
import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.config.ConfigUtils;
import com.chenfei.android.apk.downloader.session.SessionUtils;
import com.chenfei.android.apk.downloader.ui.common.LoginWorker;
import com.chenfei.android.apk.downloader.ui.home.search.AppTable;
import com.chenfei.android.apk.downloader.ui.home.search.SearchWorker;
import com.chenfei.android.apk.downloader.ui.i18n.I18N;
import com.chenfei.android.apk.downloader.util.LocaleUtils;
import com.chenfei.android.apk.downloader.util.StringUtils;
import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.Listener;
import com.chenfei.ui.base.input.SearchInput;
import com.chenfei.ui.base.input.Select;
import com.chenfei.ui.base.panel.Panel;

public abstract class SearchFunction extends Panel
{
    /** 默认序列化版本 */
    private static final long   serialVersionUID = 1L;

    private static final Logger LOG              = Logger.getLogger(SearchFunction.class);

    private Panel               searchPanel;

    private Panel               tablePanel;

    public SearchFunction()
    {
        super(new BorderLayout());

        super.setMinimumSize(new Dimension(645, super.getMinimumSize().height));

        this.initSearch();
        this.initTable();

        this.add(this.searchPanel, BorderLayout.NORTH);
        this.add(this.tablePanel, BorderLayout.CENTER);
    }

    private void initTable()
    {
        this.tablePanel = new Panel();
    }

    private void initSearch()
    {
        final SearchInput searchInput = new SearchInput(new Attribute(), new CSS().setWidth(400))
        {
            /** 默认序列化版本 */
            private static final long serialVersionUID = 1L;

            @Override
            protected void search(final String keywords)
            {
                ConfigUtils.getConfig().getSearchConfig().setKeywords(keywords);
                SearchFunction.this.search();
            }
        };

        // 当顶层元素显示后，搜索框获取焦点
        searchInput.addHierarchyListener(new HierarchyListener()
        {
            @Override
            public void hierarchyChanged(final HierarchyEvent event)
            {
                // 如果是显示状态变更的事件
                if ((event.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0)
                {
                    // 如果事件源当前是显示状态
                    if (event.getComponent().isShowing())
                    {
                        // 搜索框获取焦点
                        searchInput.requestFocus();
                    }
                }
            }
        });

        final Select localeSelect = new Select(LocaleUtils.getLocaleNames());
        localeSelect.setSelected(LocaleUtils.getName(ConfigUtils.getConfig().getSearchConfig().getLocale()));
        localeSelect.setCSS(new CSS().setWidth(100).setHeight(25));
        localeSelect.addActionListener(new Listener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                String name = (String)localeSelect.getSelectedItem();

                Locale locale = LocaleUtils.getLocale(name);

                ConfigUtils.getConfig().getSearchConfig().setLocale(locale);

                SearchFunction.this.search();
            }
        });

        final Select entriesCountSelect = new Select(new Integer[] { 10, 20, 30, 50, 100 });
        entriesCountSelect.setSelected(ConfigUtils.getConfig().getSearchConfig().getEntriesCount());
        entriesCountSelect.setCSS(new CSS().setWidth(65).setHeight(25));
        entriesCountSelect.addActionListener(new Listener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                Integer entriesCount = (Integer)entriesCountSelect.getSelectedItem();
                ConfigUtils.getConfig().getSearchConfig().setEntriesCount(entriesCount);
                SearchFunction.this.search();
            }
        });

        this.searchPanel = new Panel();
        this.searchPanel.add(searchInput);
        this.searchPanel.add(localeSelect);
        this.searchPanel.add(entriesCountSelect);
    }

    private void search()
    {
        if (StringUtils.isBlank(ConfigUtils.getConfig().getSearchConfig().getKeywords()))
        {
            return;
        }

        if (SessionUtils.getSession().isLogged())
        {
            this.doSearch();
        }
        else
        {
            LoginWorker loginWorker = new LoginWorker();

            loginWorker.addPropertyChangeListener(new Listener()
            {
                @Override
                public void propertyChange(final PropertyChangeEvent event)
                {
                    if (event.getPropertyName().equals(LoginWorker.SUCCESSFUL))
                    {
                        SearchFunction.this.doSearch();
                    }
                }
            });

            loginWorker.execute();
        }
    }

    private void doSearch()
    {
        new SearchWorker()
        {
            @Override
            protected void done()
            {
                try
                {
                    List<App> appList = this.get();

                    if (null == appList)
                    {
                        return;
                    }

                    SearchFunction.this.showApps(appList);

                    APKDownloader.setStatus(I18N.get("search.successful", appList.size()));
                }
                catch (Exception e)
                {
                    LOG.error("搜索失败", e);

                    APKDownloader.setStatus(I18N.get("search.unsuccessful"));
                }
            }
        }.execute();
    }

    protected void showApps(final List<App> appList)
    {
        this.tablePanel.removeAll();

        JScrollPane appScrollTable = new JScrollPane(new AppTable(appList)
        {
            /** 默认序列化版本 */
            private static final long serialVersionUID = 1L;

            @Override
            protected void download(final App app)
            {
                SearchFunction.this.download(app);
            }
        });
        appScrollTable.setPreferredSize(new Dimension(820, 440));

        this.tablePanel.add(appScrollTable);
    }

    protected abstract void download(final App app);
}
