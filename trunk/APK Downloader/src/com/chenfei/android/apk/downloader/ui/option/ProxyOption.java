package com.chenfei.android.apk.downloader.ui.option;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.chenfei.android.apk.downloader.config.Config;
import com.chenfei.android.apk.downloader.config.Config.ProxyConfig;
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

    private TextInput hostName;

    private NumberInput port;

    public ProxyOption(final ProxyConfig config)
    {
        super("代理");

        this.init(config);

        this.add(new Field("启用：", this.enabled));
        this.add(new Field("主机：", this.hostName));
        this.add(Box.createVerticalStrut(5));
        this.add(new Field("端口：", this.port));
    }

    private void init(final ProxyConfig config)
    {
        this.enabled = new JCheckBox();
        this.enabled.setBorder(null);
        this.enabled.setSelected(config.isEnabled());

        this.hostName = new TextInput(new Attribute().setValue(config.getHostName()), new CSS().setWidth(250));

        this.port = new NumberInput(new Attribute().setValue(config.getPort()), new CSS().setWidth(50));

        this.update();

        this.bindListener();
    }

    private void update()
    {
        this.hostName.setEnabled(this.enabled.isSelected());
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
        String hostName = this.hostName.getValue();

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

        return new Config.ProxyConfig().setHostName(hostName).setPort(port).setEnabled(enabled);
    }
}
