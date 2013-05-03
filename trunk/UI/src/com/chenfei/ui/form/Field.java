package com.chenfei.ui.form;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.chenfei.ui.base.panel.Panel;

public class Field extends Panel
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private JLabel label;

    private Component input;

    public Field(final String name, final int nameWidth, final Component input)
    {
        super();
        super.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.label = new JLabel(name, SwingConstants.RIGHT);
        this.label.setPreferredSize(new Dimension(nameWidth, this.label.getPreferredSize().height));

        this.input = input;

        super.add(this.label);
        super.add(this.input);
    }

    public Field(final String name, final Component input)
    {
        this(name, 75, input);
    }

}
