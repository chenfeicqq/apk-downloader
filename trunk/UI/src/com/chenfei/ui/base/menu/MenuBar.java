package com.chenfei.ui.base.menu;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    /** 菜单列表 */
    private List<Menu> menuList = new LinkedList<Menu>();

    /**添加菜单
     */
    public void add(final Menu menu)
    {
        this.menuList.add(menu);

        super.add(menu);
    }
}
