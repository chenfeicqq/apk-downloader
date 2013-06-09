package com.chenfei.android.apk.downloader.ui.option;

import javax.swing.Box;

import com.chenfei.android.apk.downloader.config.Config;
import com.chenfei.android.apk.downloader.config.Config.AccountConfig;
import com.chenfei.android.apk.downloader.ui.i18n.I18N;
import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.base.input.PasswordInput;
import com.chenfei.ui.base.input.TextInput;
import com.chenfei.ui.base.panel.DashedPanel;
import com.chenfei.ui.form.Field;

public class AccountOption extends DashedPanel
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private TextInput email;

    private PasswordInput password;

    private TextInput deviceID;

    public AccountOption(final AccountConfig config)
    {
        super(I18N.get("option.account"));

        int width = 250;

        this.email = new TextInput(new Attribute().setValue(config.getEmail()), new CSS().setWidth(width));
        this.password = new PasswordInput(new Attribute().setValue(config.getPassword()), new CSS().setWidth(width));
        this.deviceID = new TextInput(new Attribute().setValue(config.getDeviceID()), new CSS().setWidth(width));

        this.password.setToolTipText(I18N.get("option.account.password.tip"));

        this.add(new Field(I18N.get("option.account.email"), this.email));
        this.add(Box.createVerticalStrut(5));
        this.add(new Field(I18N.get("option.account.password"), this.password));
        this.add(Box.createVerticalStrut(5));
        this.add(new Field(I18N.get("option.account.device.id"), this.deviceID));
    }

    public AccountConfig getConfig()
    {
        return new Config.AccountConfig()
            .setEmail(this.email.getValue())
            .setPassword(this.password.getValue())
            .setDeviceID(this.deviceID.getValue());
    }

    public void refresh(final AccountConfig accountConfig)
    {
        this.email.setValue(accountConfig.getEmail());
        this.password.setValue(accountConfig.getPassword());
        this.deviceID.setValue(accountConfig.getDeviceID());
    }
}
