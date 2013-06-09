package com.chenfei.ui;

import java.awt.Image;

import javax.swing.JFileChooser;
import javax.swing.WindowConstants;

public class Attribute
{
    /**
     * Window 窗口标题
     * PasswordInput/Select title提示
     */
    private String title;

    /** 程序图标 */
    private Image ico;

    /** Window 是否允许改变窗口大小 */
    private boolean resizable = false;

    /** Window 点击关闭的行为 */
    private int closeOperation = WindowConstants.EXIT_ON_CLOSE;

    /** FileInput 值 */
    private String value;

    /** FileInput 文件选择模式 FILES_ONLY/DIRECTORIES_ONLY/FILES_AND_DIRECTORIES */
    private int mode = JFileChooser.FILES_AND_DIRECTORIES;

    /** StatusBar 名称 */
    private String name;

    public int getMode()
    {
        return this.mode;
    }

    public Attribute setMode(final int mode)
    {
        this.mode = mode;
        return this;
    }

    public String getTitle()
    {
        return this.title;
    }

    public Attribute setTitle(final String title)
    {
        this.title = title;
        return this;
    }

    public boolean isResizable()
    {
        return this.resizable;
    }

    public Attribute setResizable(final boolean resizable)
    {
        this.resizable = resizable;
        return this;
    }

    public int getCloseOperation()
    {
        return this.closeOperation;
    }

    public Attribute setCloseOperation(final int closeOperation)
    {
        this.closeOperation = closeOperation;
        return this;
    }

    public String getValue()
    {
        return this.value;
    }

    public Attribute setValue(final String value)
    {
        this.value = value;
        return this;
    }

    public String getName()
    {
        return this.name;
    }

    public Attribute setName(final String name)
    {
        this.name = name;
        return this;
    }

    public Image getIco()
    {
        return this.ico;
    }

    public Attribute setIco(final Image ico)
    {
        this.ico = ico;
        return this;
    }
}
