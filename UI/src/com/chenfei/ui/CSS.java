package com.chenfei.ui;

import java.awt.Color;

public class CSS
{
    /** Window/FileInput/SplitPanel 宽度 */
    private int width;

    /** Window 最大宽度 */
    private int maxWidth;

    /** Window 最小宽度 */
    private int minWidth;

    /** Window/FileInput/SplitPanel 高度 */
    private int height;

    /** Window 最大高度 */
    private int maxHeight;

    /** Window 最小高度 */
    private int minHeight;

    /** Window 是否显示 */
    private boolean display = true;

    /** StatusBar 背景色 */
    private Color background;

    public int getWidth()
    {
        return this.width;
    }

    public CSS setWidth(final int width)
    {
        this.width = width;
        return this;
    }

    public int getMaxWidth()
    {
        return this.maxWidth;
    }

    public CSS setMaxWidth(final int maxWidth)
    {
        this.maxWidth = maxWidth;
        return this;
    }

    public int getMinWidth()
    {
        return this.minWidth;
    }

    public CSS setMinWidth(final int minWidth)
    {
        this.minWidth = minWidth;
        return this;
    }

    public int getHeight()
    {
        return this.height;
    }

    public CSS setHeight(final int height)
    {
        this.height = height;
        return this;
    }

    public int getMaxHeight()
    {
        return this.maxHeight;
    }

    public CSS setMaxHeight(final int maxHeight)
    {
        this.maxHeight = maxHeight;
        return this;
    }

    public int getMinHeight()
    {
        return this.minHeight;
    }

    public CSS setMinHeight(final int minHeight)
    {
        this.minHeight = minHeight;
        return this;
    }

    public boolean isDisplay()
    {
        return this.display;
    }

    public CSS setDisplay(final boolean display)
    {
        this.display = display;
        return this;
    }

    public Color getBackground()
    {
        return this.background;
    }

    public CSS setBackground(final Color background)
    {
        this.background = background;
        return this;
    }
}
