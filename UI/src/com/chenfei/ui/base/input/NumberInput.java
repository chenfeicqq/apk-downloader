package com.chenfei.ui.base.input;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;

public class NumberInput extends TextInput
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    public NumberInput(final Attribute attr, final CSS css)
    {
        super(attr, css);

        // 设置只允许输入数字
        this.setDocument(new PlainDocument()
        {
            /** 默认序列化版本 */
            private static final long serialVersionUID = 1L;

            @Override
            public void insertString(final int offset, final String str, final AttributeSet attributeSet)
                throws BadLocationException
            {

                char[] chars = str.toCharArray();

                for (int i = 0; i < chars.length; i++)
                {
                    if (chars[i] < '0' || chars[i] > '9')
                    {
                        return;
                    }
                }

                super.insertString(offset, str, attributeSet);
            }
        });

        if (null != attr.getValue())
        {
            try
            {
                this.getDocument().insertString(0, attr.getValue(), null);
            }
            catch (BadLocationException e)
            {
                e.printStackTrace();
            }
        }
    }
}
