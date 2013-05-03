package com.chenfei.ui.base.panel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JSplitPane;

import com.chenfei.ui.CSS;

public class SplitPanel extends JSplitPane
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    public SplitPanel(final CSS css)
    {
        super.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        super.setContinuousLayout(true);

        this.setCSS(css);
    }

    private void setCSS(final CSS css)
    {
        if (0 != css.getWidth())
        {
            this.setPreferredSize(new Dimension(css.getWidth(), this.getPreferredSize().getSize().height));

        }
        if (0 != css.getHeight())
        {
            this.setPreferredSize(new Dimension(this.getPreferredSize().getSize().width, css.getHeight()));
        }
    }

    public SplitPanel setLeft(final Component left)
    {
        super.setLeftComponent(left);
        return this;
    }

    public SplitPanel setRight(final Component right)
    {
        super.setRightComponent(right);
        return this;
    }

}
