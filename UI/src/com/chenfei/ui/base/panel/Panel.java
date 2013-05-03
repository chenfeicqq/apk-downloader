package com.chenfei.ui.base.panel;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Panel extends JPanel
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    public Panel()
    {
    }

    public Panel(final LayoutManager layoutManager)
    {
        super(layoutManager);
    }

    @Override
    public Panel add(final Component component)
    {
        super.add(component);
        return this;
    }

    public Panel setDisplay(final boolean display)
    {
        super.setVisible(display);
        return this;
    }
}
