package com.chenfei.android.apk.downloader.ui.option;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.chenfei.android.apk.downloader.config.Config;
import com.chenfei.android.apk.downloader.config.Config.ProxyConfig;
import com.chenfei.android.apk.downloader.ui.i18n.I18N;
import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.base.input.NumberInput;
import com.chenfei.ui.base.input.TextInput;
import com.chenfei.ui.base.panel.DashedPanel;
import com.chenfei.ui.form.Field;

public class ProxyOption extends DashedPanel
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private JCheckBox enabled;

    private TextInput host;

    private NumberInput port;

    public ProxyOption(final ProxyConfig config)
    {
        super(I18N.get("option.proxy"));

        this.init(config);

        this.add(new Field(I18N.get("option.proxy.enabled"), this.enabled));
        this.add(new Field(I18N.get("option.proxy.host"), this.host));
        this.add(Box.createVerticalStrut(5));
        this.add(new Field(I18N.get("option.proxy.port"), this.port));
    }

    private void init(final ProxyConfig config)
    {
        this.enabled = new JCheckBox();
        this.enabled.setBorder(null);
        this.enabled.setSelected(config.isEnabled());

        this.host = new TextInput(new Attribute().setValue(config.getHost()), new CSS().setWidth(250));

        this.port = new NumberInput(new Attribute().setValue(config.getPort()), new CSS().setWidth(50));

        this.update();

        this.bindListener();
    }

    private void update()
    {
        this.host.setEnabled(this.enabled.isSelected());
        this.port.setEnabled(this.enabled.isSelected());
    }

    private void bindListener()
    {
        this.enabled.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(final ChangeEvent e)
            {
                ProxyOption.this.update();
            }
        });
    }

    public ProxyConfig getConfig()
    {
        String host = this.host.getValue();

        int port = 0;
        if (!"".equals(this.port.getValue()))
        {
            try
            {
                port = Integer.parseInt(this.port.getValue());
            }
            catch (Exception e)
            {

            }
        }

        boolean enabled = this.enabled.isSelected();

        return new Config.ProxyConfig().setHost(host).setPort(port).setEnabled(enabled);
    }

    public void refresh(final ProxyConfig proxyConfig)
    {
        this.enabled.setSelected(proxyConfig.isEnabled());
        this.host.setValue(proxyConfig.getHost());
        this.port.setValue(proxyConfig.getPort());
    }
}
