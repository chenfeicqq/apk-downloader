package com.chenfei.ui.base.toolbar;

import javax.swing.JToggleButton;

import com.chenfei.ui.Listener;

public class ToolbarItem extends JToggleButton
{
    /**  */
    private static final long serialVersionUID = 1L;

    public ToolbarItem(final String name)
    {
        super(name);

        this.setEnabled(true);
    }

    /** 添加事件 
     */
    public ToolbarItem on(final Listener event)
    {
        super.addMouseListener(event);
        return this;
    }
}
