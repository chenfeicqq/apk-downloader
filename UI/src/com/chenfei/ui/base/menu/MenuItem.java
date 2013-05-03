package com.chenfei.ui.base.menu;

import javax.swing.JMenuItem;

import com.chenfei.ui.Listener;

public class MenuItem extends JMenuItem
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    public MenuItem(final String name)
    {
        super(name);
    }

    public MenuItem on(final Listener event)
    {
        this.addActionListener(event);
        return this;
    }
}
