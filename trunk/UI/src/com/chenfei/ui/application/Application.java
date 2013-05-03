package com.chenfei.ui.application;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.List;
import java.util.Map;

import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.Listener;
import com.chenfei.ui.UI;
import com.chenfei.ui.base.StatusBar;
import com.chenfei.ui.base.panel.Panel;
import com.chenfei.ui.base.toolbar.Toolbar;
import com.chenfei.ui.base.toolbar.ToolbarItem;
import com.chenfei.ui.base.window.Window;
import com.chenfei.ui.function.Function;

/**
 * 应用程序
 *  组装Function添加到Window中
 *  创建工具栏，将所有的Function的工具栏子项添加到工具栏中
 *  绑定工具栏子项点击后触发该Function的Panel显示
 */
public class Application
{
    /** 窗口 */
    private Window window = new Window();

    /** 工具栏 */
    private Toolbar toolbar = new Toolbar();

    /** 状态栏 */
    private StatusBar statusBar = new StatusBar();

    /** 开始构建
     */
    public Application(final Map<String, Attribute> attr, final Map<String, CSS> css, final List<Function> functionList)
    {
        this.window.setAttribute(attr.get(UI.WINDOW));
        this.window.setCSS(css.get(UI.WINDOW));

        this.window.add(this.toolbar);

        this.statusBar.setAttribute(attr.get(UI.STATUS_BAR));
        this.statusBar.setCSS(css.get(UI.STATUS_BAR));
        this.window.add(this.statusBar);

        this.initFunction(functionList);
    }

    public void setStatus(final Component status)
    {
        Application.this.statusBar.setStatus(status);
    }

    public void addWindowListener(final WindowAdapter windowAdapter)
    {
        this.window.addListener(windowAdapter);
    }

    /** 组装Function添加到Window中
     *  将所有的Function的工具栏子项添加到工具栏中
     *  绑定工具栏子项点击后触发该Function的Panel显示
     */
    private void initFunction(final List<Function> functionList)
    {
        for (Function function : functionList)
        {
            final Panel panel = function.getPanel();

            ToolbarItem toolbarItem = function.getToolbarItem().on(new Listener()
            {
                @Override
                public void mouseClicked(final MouseEvent e)
                {
                    Application.this.window.add(panel);
                }
            });
            this.toolbar.add(toolbarItem);

            if (function.isHome())
            {
                this.window.add(panel);
            }
        }
    }
}
