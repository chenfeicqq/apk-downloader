package com.chenfei.ui.base.input;

import java.awt.event.KeyEvent;

import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.Listener;

public abstract class SearchInput extends TextInput
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    public SearchInput(Attribute attr, CSS css)
    {
        super(attr, css);

        this.addKeyListener(new Listener()
        {

            @Override
            public void keyPressed(KeyEvent event)
            {
                if (event.getKeyChar() == KeyEvent.VK_ENTER)
                {
                    SearchInput.this.search(SearchInput.this.getValue());
                }
            }
        });
    }

    protected abstract void search(String searchKeyword);
}
