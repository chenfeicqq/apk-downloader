package com.chenfei.ui.base.toolbar;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JToolBar;

import com.chenfei.ui.Listener;

public class Toolbar extends JToolBar
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private final List<ToolbarItem> toolbarItemList = new LinkedList<ToolbarItem>();

    public Toolbar()
    {
        super();

        this.setFloatable(true);
    }

    public void add(final ToolbarItem item)
    {
        item.on(new Listener()
        {
            @Override
            public void mouseClicked(final MouseEvent e)
            {
                for (ToolbarItem tempItem : Toolbar.this.toolbarItemList)
                {
                    if (tempItem.equals(item))
                    {
                        continue;
                    }
                    tempItem.setSelected(false);
                }
                Toolbar.this.updateUI();
            }
        });

        this.toolbarItemList.add(item);
        super.add(item);
    }
}