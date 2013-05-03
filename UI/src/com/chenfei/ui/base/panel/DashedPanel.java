package com.chenfei.ui.base.panel;

import javax.swing.border.TitledBorder;

public class DashedPanel extends Panel
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    public DashedPanel(final String name)
    {
        this.setBorder(new TitledBorder(name));
    }
}
