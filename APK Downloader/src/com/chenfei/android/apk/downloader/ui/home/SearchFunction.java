package com.chenfei.android.apk.downloader.ui.home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.APKDownloader;
import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.session.Session.Search;
import com.chenfei.android.apk.downloader.session.SessionUtil;
import com.chenfei.android.apk.downloader.ui.common.LoginWorker;
import com.chenfei.android.apk.downloader.ui.home.search.AppTable;
import com.chenfei.android.apk.downloader.ui.home.search.SearchWorker;
import com.chenfei.android.apk.downloader.util.LocaleUtils;
import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.Listener;
import com.chenfei.ui.base.input.SearchInput;
import com.chenfei.ui.base.panel.Panel;

public abstract class SearchFunction extends Panel
{
    /** 默认序列化版本 */
    private static final long   serialVersionUID = 1L;

    private static final Logger LOG              = Logger.getLogger(SearchFunction.class);

    private Panel               searchPanel;

    private Panel               tablePanel;

    private Search              search           = new Search();

    public SearchFunction()
    {
        super(new BorderLayout());

        super.setMinimumSize(new Dimension(660, super.getMinimumSize().height));

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
            protected void search(final String searchKeyword)
            {
                SearchFunction.this.search.setSearchKeyword(searchKeyword);
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

        final JComboBox localeSelect = new JComboBox(LocaleUtils.getNames());
        localeSelect.setPreferredSize(new Dimension(95, 25));
        localeSelect.addActionListener(new Listener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                String name = (String)localeSelect.getSelectedItem();

                Locale locale = LocaleUtils.getLocale(name);

                SessionUtil.getSession().refreshLocale(locale);

                SearchFunction.this.search();
            }
        });

        final JComboBox entriesCountSelect = new JComboBox(new Integer[] {10, 20, 30, 50, 100});
        entriesCountSelect.setPreferredSize(new Dimension(80, 25));
        entriesCountSelect.addActionListener(new Listener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                Integer entriesCount = (Integer)entriesCountSelect.getSelectedItem();
                SearchFunction.this.search.setEntriesCount(entriesCount);
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
        if (null == this.search.getSearchKeyword() || "".equals(this.search.getSearchKeyword()))
        {
            return;
        }

        if (SessionUtil.getSession().isLogged())
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
        new SearchWorker(this.search)
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

                    APKDownloader.setStatus("搜索成功，共" + appList.size() + "个结果！");
                }
                catch (Exception e)
                {
                    LOG.error("搜索失败", e);

                    APKDownloader.setStatus("搜索失败！");
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
