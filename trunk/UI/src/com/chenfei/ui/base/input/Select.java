package com.chenfei.ui.base.input;

import java.awt.Dimension;

import javax.swing.JComboBox;

import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.UI;

public class Select extends JComboBox implements UI
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private CSS css;

    private Attribute attr;

    public Select(Object[] options)
    {
        super(options);
    }

    @Override
    public void setAttribute(final Attribute attr)
    {
        if (null == attr)
        {
            return;
        }

        this.attr = attr;

        String title = this.attr.getTitle();
        if (null != title)
        {
            this.setTitle(title);
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

    private void setTitle(final String title)
    {
        this.setToolTipText(title);
    }

    /** 设置选中某个选项
     */
    public void setSelected(Object selected)
    {
        if (null == selected)
        {
            return;
        }
        this.setSelectedItem(selected);
    }

    /** 属性获取 Begin **/

    /** 获取选中项
     */
    public Object getSelected()
    {
        return this.getSelectedItem();
    }

    /** CSS设置 Begin **/

    private void setWidth(final int width)
    {
        this.setPreferredSize(new Dimension(width, this.getPreferredSize().height));
    }

    private void setHeight(final int height)
    {
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, height));
    }

    /** 子节点操作 Begin **/
}