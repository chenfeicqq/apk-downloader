package com.chenfei.ui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.chenfei.ui.application.Application;
import com.chenfei.ui.function.Function;

public final class UIBuilder
{
    private List<Function> functionList = new LinkedList<Function>();

    private Map<String, Attribute> attr = new HashMap<String, Attribute>();

    private Map<String, CSS> css = new HashMap<String, CSS>();

    public Application build()
    {
        if (this.functionList.isEmpty())
        {
            return null;
        }

        return new Application(this.attr, this.css, this.functionList);
    }

    public UIBuilder put(final String key, final CSS css)
    {
        this.css.put(key, css);
        return this;
    }

    public UIBuilder put(final String key, final Attribute attr)
    {
        this.attr.put(key, attr);
        return this;
    }

    public UIBuilder add(final Function function)
    {
        this.functionList.add(function);
        return this;
    }
}
