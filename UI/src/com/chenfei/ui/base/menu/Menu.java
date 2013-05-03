package com.chenfei.ui.base.menu;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenu;

public class Menu extends JMenu
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    /** 子菜单项 */
    private List<MenuItem> menuItemList = new LinkedList<MenuItem>();

    public Menu(final String name)
    {
        super(name);
    }

    public void add(final MenuItem menuItem)
    {
        this.menuItemList.add(menuItem);

        super.add(menuItem);
    }

}
