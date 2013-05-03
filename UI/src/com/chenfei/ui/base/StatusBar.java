package com.chenfei.ui.base;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jb2011.lnf.beautyeye.widget.N9ComponentFactory;

import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.UI;
import com.chenfei.ui.base.panel.Panel;

public class StatusBar extends JPanel implements UI
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private JLabel label = N9ComponentFactory.createLabel_style1("状态");

    private Panel panel = new Panel();

    /** 属性 */
    private Attribute attr;

    /** 样式 */
    private CSS css;

    public StatusBar()
    {
        super(new BorderLayout());
        super.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        this.panel.setLayout(new BorderLayout());

        this.add(this.label, BorderLayout.WEST);
        this.add(this.panel, BorderLayout.CENTER);
    }

    public StatusBar(final Attribute attr, final CSS css)
    {
        this();

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

        if (null != this.attr.getName())
        {
            this.setName(this.attr.getName());
        }
    }

    @Override
    public void setCSS(final CSS css)
    {
        if (css == null)
        {
            return;
        }

        this.css = css;

        if (null != this.css.getBackground())
        {
            this.setBackground(this.css.getBackground());
            this.panel.setBackground(this.css.getBackground());
        }
    }

    /** 属性设置 Begin **/

    @Override
    public void setName(final String name)
    {
        this.label.setText(name);
    }

    /** CSS设置 Begin **/

    /** 子节点操作 Begin **/

    public void setStatus(final Component component)
    {
        this.panel.removeAll();
        this.panel.add(component, BorderLayout.EAST);
        this.updateUI();
    }
}
