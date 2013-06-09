package com.chenfei.android.apk.downloader.ui.option;

import javax.swing.JFileChooser;

import com.chenfei.android.apk.downloader.config.Config;
import com.chenfei.android.apk.downloader.config.Config.CommonConfig;
import com.chenfei.android.apk.downloader.config.ConfigUtils;
import com.chenfei.android.apk.downloader.ui.i18n.I18N;
import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.base.input.FileInput;
import com.chenfei.ui.base.input.Select;
import com.chenfei.ui.base.panel.DashedPanel;
import com.chenfei.ui.form.Field;

public class CommonOption extends DashedPanel
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private Select languageSelect;

    private FileInput savePathInput;

    public CommonOption(final CommonConfig config)
    {
        super(I18N.get("option.general"));

        this.languageSelect = new Select(I18N.getLanguageNames());
        this.languageSelect.setSelected(I18N.getLanguageName(ConfigUtils.getConfig().getCommonConfig().getLanguage()));
        this.languageSelect.setCSS(new CSS().setWidth(90).setHeight(25));
        this.languageSelect.setAttribute(new Attribute().setTitle(I18N.get("option.language.tip")));

        this.savePathInput = new FileInput(
            new Attribute().setValue(config.getSavePath()).setMode(JFileChooser.DIRECTORIES_ONLY),
            new CSS().setWidth(450).setHeight(25)
        );
        this.savePathInput.setText(I18N.get("option.general.save.path.browse"));

        this.add(new Field(I18N.get("option.language"), this.languageSelect));
        this.add(new Field(I18N.get("option.general.save.path"), this.savePathInput));
    }

    public CommonConfig getConfig()
    {
        String language = I18N.getLanguage((String) this.languageSelect.getSelected());
        String savePath = this.savePathInput.getValue();

        return new Config.CommonConfig().setLanguage(language).setSavePath(savePath);
    }

    public void refresh(final CommonConfig commonConfig)
    {
        this.languageSelect.setSelected(I18N.getLanguageName(ConfigUtils.getConfig().getCommonConfig().getLanguage()));
        this.savePathInput.setValue(commonConfig.getSavePath());
    }
}
