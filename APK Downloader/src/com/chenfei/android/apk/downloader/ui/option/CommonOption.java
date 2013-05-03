package com.chenfei.android.apk.downloader.ui.option;

import javax.swing.JFileChooser;

import com.chenfei.android.apk.downloader.config.Config;
import com.chenfei.android.apk.downloader.config.Config.CommonConfig;
import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.base.input.FileInput;
import com.chenfei.ui.base.panel.DashedPanel;
import com.chenfei.ui.form.Field;

public class CommonOption extends DashedPanel
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private FileInput savePath;

    public CommonOption(final CommonConfig config)
    {
        super("常规");

        this.savePath =
            new FileInput(new Attribute().setValue(config.getSavePath()).setMode(JFileChooser.DIRECTORIES_ONLY),
                new CSS().setWidth(450).setHeight(25));

        this.add(new Field("下载目录：", this.savePath));
    }

    public CommonConfig getConfig()
    {
        return new Config.CommonConfig().setSavePath(this.savePath.getValue());
    }
}
