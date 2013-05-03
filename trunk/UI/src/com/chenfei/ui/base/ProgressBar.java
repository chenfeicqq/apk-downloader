package com.chenfei.ui.base;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import com.chenfei.ui.base.panel.Panel;

public class ProgressBar extends Panel
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private JProgressBar progressBar;

    public ProgressBar()
    {
        this(SwingConstants.HORIZONTAL, 0, 100);

        this.progressBar.setStringPainted(true);

    }

    public ProgressBar(final int orient, final int min, final int max)
    {
        super(new BorderLayout());

        this.progressBar = new JProgressBar(orient, min, max);

        super.add(this.progressBar);
    }

    @Override
    public void setSize(final int width, final int height)
    {
        Dimension dimension = new Dimension(width, height);

        super.setMinimumSize(dimension);
        super.setMaximumSize(dimension);
    }

    public void setString(final String string)
    {
        this.progressBar.setString(string);
    }

    public void setValue(final int value)
    {
        this.progressBar.setValue(value);
    }

    public void setStringPainted(final boolean stringPainted)
    {
        this.progressBar.setStringPainted(stringPainted);
    }
}
