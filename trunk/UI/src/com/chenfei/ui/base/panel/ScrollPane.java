package com.chenfei.ui.base.panel;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class ScrollPane extends JScrollPane
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    public ScrollPane()
    {
        super();

        super.setBorder(BorderFactory.createEmptyBorder());
    }
}
