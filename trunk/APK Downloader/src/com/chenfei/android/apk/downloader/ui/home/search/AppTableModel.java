package com.chenfei.android.apk.downloader.ui.home.search;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.ui.i18n.I18N;

public class AppTableModel extends AbstractTableModel
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private String[]          columnNames      = {
                                                    I18N.get("table.title.ico"),
                                                    I18N.get("table.title.name"),
                                                    I18N.get("table.title.package"),
                                                    I18N.get("table.title.creator"),
                                                    I18N.get("table.title.version"),
                                                    I18N.get("table.title.size"),
                                                    I18N.get("table.title.price"),
                                                  };

    private List<Object[]> appInfoList = new LinkedList<Object[]>();

    public AppTableModel(final List<App> appList)
    {
        for (App app : appList)
        {
            this.appInfoList.add(app.getAppInfo());
        }
    }

    @Override
    public int getColumnCount()
    {
        return this.columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return this.appInfoList.size();
    }

    @Override
    public Object getValueAt(final int row, final int col)
    {
        return this.appInfoList.get(row)[col];
    }

    @Override
    public String getColumnName(final int column)
    {
        return this.columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(final int c)
    {
        return this.getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(final int row, final int col)
    {
        return false;
    }
}