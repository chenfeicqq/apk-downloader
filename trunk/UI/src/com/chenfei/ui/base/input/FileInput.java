package com.chenfei.ui.base.input;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.Listener;
import com.chenfei.ui.UI;
import com.chenfei.ui.base.panel.Panel;

public class FileInput extends Panel implements UI
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private TextInput filePathInput = new TextInput();

    private JButton fileChoose = new JButton("浏览");

    private JFileChooser fileChooser = new JFileChooser();

    private CSS css;

    private Attribute attr;

    public FileInput(final Attribute attr, final CSS css)
    {
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.setAttribute(attr);
        this.setCSS(css);

        this.bindListener();

        this.add(this.filePathInput);
        this.add(Box.createHorizontalStrut(5));
        this.add(this.fileChoose);
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

        this.setMode(this.attr.getMode());
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

    private void bindListener()
    {
        this.fileChoose.addMouseListener(new Listener()
        {
            @Override
            public void mouseClicked(final MouseEvent e)
            {
                int result = FileInput.this.fileChooser.showOpenDialog(FileInput.this);

                if (result == JFileChooser.APPROVE_OPTION)
                {
                    FileInput.this.filePathInput.setValue(FileInput.this.fileChooser.getSelectedFile().getPath());
                }
            }
        });
    }

    /** 属性设置 Begin **/

    /**  设置默认值 */
    public void setValue(final String value)
    {
        this.filePathInput.setValue(value);
        this.fileChooser.setCurrentDirectory(new File(value).getParentFile());
    }

    /** 设置文件选择模式 JFileChooser.FILES_ONLY/JFileChooser.DIRECTORIES_ONLY/JFileChooser.FILES_AND_DIRECTORIES */
    public void setMode(final int mode)
    {
        this.fileChooser.setFileSelectionMode(mode);
    }

    /** 设置显示名称 **/
    public void setText(final String text)
    {
        this.fileChoose.setText(text);
    }

    /** 属性获取 Begin **/

    public String getValue()
    {
        return this.filePathInput.getText();
    }

    /** CSS设置 Begin **/

    private void setWidth(final int width)
    {
        this.filePathInput.setWidth(width - 75);
        this.fileChoose.setPreferredSize(new Dimension(70, this.fileChoose.getPreferredSize().height));
    }

    private void setHeight(final int height)
    {
        this.filePathInput.setHeight(height);
        this.fileChoose.setPreferredSize(new Dimension(this.fileChoose.getPreferredSize().width, height));
    }
}
