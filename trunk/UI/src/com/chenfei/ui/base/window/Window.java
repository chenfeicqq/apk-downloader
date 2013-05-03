package com.chenfei.ui.base.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;

import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.UI;
import com.chenfei.ui.base.StatusBar;
import com.chenfei.ui.base.menu.MenuBar;
import com.chenfei.ui.base.panel.Panel;
import com.chenfei.ui.base.toolbar.Toolbar;

/**
 * 应用程序窗口
 */
public class Window implements UI
{
    /** 窗口框架 */
    private JFrame frame = new JFrame(this.getGraphicsConfiguration());

    /** 内容显示的面板 */
    private Panel panel = new Panel();

    /** 属性 */
    private Attribute attr;

    /** 样式 */
    private CSS css;

    public Window()
    {
        this.initFrame();
    }

    public Window(final Attribute attr, final CSS css)
    {
        this();

        this.setAttribute(attr);
        this.setCSS(css);
    }

    private void initFrame()
    {
        this.frame.getContentPane().add(this.panel, BorderLayout.CENTER);
    }

    private GraphicsConfiguration getGraphicsConfiguration()
    {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }

    @Override
    public void setAttribute(final Attribute attr)
    {
        if (null == attr)
        {
            return;
        }

        this.attr = attr;

        Image ico = this.attr.getIco();
        if (null != ico)
        {
            this.setIco(ico);
        }

        String title = this.attr.getTitle();
        if (null != title)
        {
            this.setTitle(title);
        }
        this.setResizable(this.attr.isResizable());
        this.setCloseOperation(this.attr.getCloseOperation());
    }

    @Override
    public void setCSS(final CSS css)
    {
        if (null == css)
        {
            return;
        }

        this.css = css;

        this.setWidth(this.css.getWidth());
        this.setHeight(this.css.getHeight());
        int maxHeight = this.css.getMaxHeight();
        if (0 != maxHeight)
        {
            this.setMaxHeight(maxHeight);
        }
        int minHeight = this.css.getMinHeight();
        if (0 != minHeight)
        {
            this.setMinHeight(minHeight);
        }
        int maxWidth = this.css.getMaxWidth();
        if (0 != maxWidth)
        {
            this.setMaxWidth(maxWidth);
        }
        int minWidth = this.css.getMinWidth();
        if (0 != minWidth)
        {
            this.setMinWidth(minWidth);
        }
        this.setDisplay(this.css.isDisplay());
    }

    /** 属性设置 Begin **/

    private void setTitle(final String title)
    {
        this.frame.setTitle(title);
    }

    private void setIco(final Image ico)
    {
        this.frame.setIconImage(ico);
    }

    private void setResizable(final boolean resizable)
    {
        this.frame.setResizable(resizable);
    }

    private void setCloseOperation(final int closeOperation)
    {
        this.frame.setDefaultCloseOperation(closeOperation);
    }

    /** CSS设置 Begin **/

    private void setWidth(final int width)
    {
        this.frame.setSize(width, this.frame.getSize().height);
    }

    private void setHeight(final int height)
    {
        this.frame.setSize(this.frame.getSize().width, height);
    }

    private void setMaxWidth(final int maxWidth)
    {
        Dimension maxSize = this.frame.getMaximumSize();
        maxSize.setSize(maxWidth, maxSize.height);
        this.frame.setMaximumSize(maxSize);
    }

    private void setMaxHeight(final int maxHeight)
    {
        Dimension maxSize = this.frame.getMaximumSize();
        maxSize.setSize(maxSize.width, maxHeight);
        this.frame.setMaximumSize(maxSize);
    }

    private void setMinWidth(final int minWidth)
    {
        Dimension minSize = this.frame.getMinimumSize();
        minSize.setSize(minWidth, minSize.height);
        this.frame.setMinimumSize(minSize);
    }

    private void setMinHeight(final int minHeight)
    {
        Dimension minSize = this.frame.getMinimumSize();
        minSize.setSize(minSize.width, minHeight);
        this.frame.setMinimumSize(minSize);
    }

    private void setDisplay(final boolean display)
    {
        if (display)
        {
            // 动态计算大小，this.frame.pack();
            this.setPosition();
        }
        this.frame.setVisible(display);
    }

    private void setPosition()
    {
        Rectangle screenRect = this.frame.getGraphicsConfiguration().getBounds();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(this.frame.getGraphicsConfiguration());

        // Make sure we don't place the demo off the screen.
        int centerWidth =
            screenRect.width < this.frame.getSize().width ? screenRect.x : screenRect.x + screenRect.width / 2
                - this.frame.getSize().width / 2;
        int centerHeight =
            screenRect.height < this.frame.getSize().height ? screenRect.y : screenRect.y + screenRect.height / 2
                - this.frame.getSize().height / 2;

        centerHeight = centerHeight < screenInsets.top ? screenInsets.top : centerHeight;

        this.frame.setLocation(centerWidth, centerHeight);
    }

    /** 子节点操作 Begin **/

    /** 添加菜单栏 */
    public void add(final MenuBar menuBar)
    {
        this.frame.setJMenuBar(menuBar);
    }

    /** 添加工具栏 */
    public void add(final Toolbar toolbar)
    {
        this.frame.getContentPane().add(toolbar, BorderLayout.NORTH);
    }

    /** 添加内容 */
    public void add(final Panel panel)
    {
        this.panel.removeAll();
        this.panel.add(panel, BorderLayout.CENTER);
        this.refresh();
    }

    /** 添加状态栏 */
    public void add(final StatusBar statusBar)
    {
        this.frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
    }

    /** 刷新界面 */
    public void refresh()
    {
        this.panel.updateUI();
    }

    /** 事件监听操作 Begin **/

    public void addListener(final WindowAdapter windowAdapter)
    {
        this.frame.addWindowListener(windowAdapter);
    }

}
