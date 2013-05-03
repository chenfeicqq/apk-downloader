package com.chenfei.android.apk.downloader.ui.home.search;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.ui.Browser;
import com.chenfei.ui.Listener;
import com.chenfei.ui.base.menu.MenuItem;

public abstract class AppTable extends JTable
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private JPopupMenu popupMenu = new JPopupMenu();

    private List<App> appList;

    public AppTable(final List<App> appList)
    {
        this.appList = appList;

        this.initTable();

        this.initPopupMenu();

        this.bindListener();
    }

    private void initTable()
    {
        TableModel tableModel = new AppTableModel(this.appList);
        this.setModel(tableModel);
        this.setRowHeight(40);
        this.setRowSorter(new TableRowSorter<TableModel>(super.getModel()));

        this.getColumnModel().getColumn(0).setPreferredWidth(40);
        this.getColumnModel().getColumn(1).setPreferredWidth(250);
        this.getColumnModel().getColumn(2).setPreferredWidth(200);
        this.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.getColumnModel().getColumn(4).setPreferredWidth(100);
        this.getColumnModel().getColumn(5).setPreferredWidth(50);
        this.getColumnModel().getColumn(6).setPreferredWidth(50);
    }

    private void initPopupMenu()
    {
        this.popupMenu.add(new MenuItem("下载选中 App 至本地").on(new Listener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                int[] rowIndexs = AppTable.this.getSelectedRows();

                for (int rowIndex : rowIndexs)
                {
                    rowIndex = AppTable.this.convertRowIndexToModel(rowIndex);

                    App app = (App)AppTable.this.getModel().getValueAt(rowIndex, 7);

                    AppTable.this.download(app);
                }
            }
        }));
        this.popupMenu.addSeparator();
        this.popupMenu.add(new MenuItem("在 Google Play 中查看").on(new Listener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                int[] rowIndexs = AppTable.this.getSelectedRows();

                for (int rowIndex : rowIndexs)
                {
                    rowIndex = AppTable.this.convertColumnIndexToModel(rowIndex);

                    String packageName = AppTable.this.getModel().getValueAt(rowIndex, 2).toString();

                    Browser.browse("https://play.google.com/store/apps/details?id=" + packageName);
                }
            }
        }));
    }

    protected abstract void download(final App app);

    private void bindListener()
    {
        this.addMouseListener(new Listener()
        {

            @Override
            public void mousePressed(final MouseEvent event)
            {
                this.showPopupMenu(event);
            }

            @Override
            public void mouseReleased(final MouseEvent event)
            {
                this.showPopupMenu(event);
            }

            private void showPopupMenu(final MouseEvent event)
            {
                if (event.isPopupTrigger())
                {
                    AppTable.this.popupMenu.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        });
    }
}
