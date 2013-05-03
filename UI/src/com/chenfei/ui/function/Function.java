package com.chenfei.ui.function;

import java.awt.Component;

import com.chenfei.ui.base.panel.Panel;
import com.chenfei.ui.base.toolbar.ToolbarItem;

/**
 * 功能组件：
 *    工具栏子项
 *    显示区Panel
 */
public class Function
{
    /** 功能的显示区域 */
    private Panel panel;

    /** 功能在工具栏上的按钮 */
    private ToolbarItem toolbarItem;

    /** 是否是首页功能 */
    private boolean home;

    public Function(final String name)
    {
        this.toolbarItem = new ToolbarItem(name);
        this.panel = new Panel();
    }

    public Function add(final Component component, final Object constraints)
    {
        this.panel.add(component, constraints);
        return this;
    }

    public Function add(final Component component)
    {
        this.panel.add(component);
        return this;
    }

    public Function setHome(final boolean home)
    {
        if (home)
        {
            this.toolbarItem.setSelected(true);
        }
        this.home = home;

        return this;
    }

    public Panel getPanel()
    {
        return this.panel;
    }

    public ToolbarItem getToolbarItem()
    {
        return this.toolbarItem;
    }

    public boolean isHome()
    {
        return this.home;
    }

    public static interface Listener
    {
        void changed(final Object status);
    }
}
