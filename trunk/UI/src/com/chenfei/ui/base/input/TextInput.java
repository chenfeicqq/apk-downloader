package com.chenfei.ui.base.input;

import java.awt.Dimension;

import javax.swing.JTextField;

import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.UI;

public class TextInput extends JTextField implements UI
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private CSS css;

    private Attribute attr;

    public TextInput()
    {
    }

    public TextInput(final Attribute attr, final CSS css)
    {
        this.setAttribute(attr);
        this.setCSS(css);
    }

    @Override
    public void setAttribute(final Attribute attr)
    {
        if (null == attr)
        {
            return;
        }

        this.attr = attr;

        String value = this.attr.getValue();
        if (null != value)
        {
            this.setValue(value);
        }
    }

    @Override
    public void setCSS(final CSS css)
    {
        if (null == css)
        {
            return;
        }

        this.css = css;

        if (0 != this.css.getWidth())
        {
            this.setWidth(this.css.getWidth());
        }
        if (0 != this.css.getHeight())
        {
            this.setHeight(this.css.getHeight());
        }
    }

    /** 属性设置 Begin **/

    public void setValue(final String value)
    {
        this.setText(value);
    }

    /** 属性获取 Begin **/

    public String getValue()
    {
        return this.getText();
    }

    /** CSS设置 Begin **/

    public void setWidth(final int width)
    {
        this.setPreferredSize(new Dimension(width, this.getPreferredSize().height));
    }

    public void setHeight(final int height)
    {
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, height));
    }
}
