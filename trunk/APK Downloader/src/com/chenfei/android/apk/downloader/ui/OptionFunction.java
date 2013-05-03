package com.chenfei.android.apk.downloader.ui;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import com.chenfei.android.apk.downloader.APKDownloader;
import com.chenfei.android.apk.downloader.config.Config;
import com.chenfei.android.apk.downloader.config.Config.AccountConfig;
import com.chenfei.android.apk.downloader.config.Config.CommonConfig;
import com.chenfei.android.apk.downloader.config.Config.ProxyConfig;
import com.chenfei.android.apk.downloader.config.ConfigUtil;
import com.chenfei.android.apk.downloader.ui.common.LoginWorker;
import com.chenfei.android.apk.downloader.ui.option.AccountOption;
import com.chenfei.android.apk.downloader.ui.option.CommonOption;
import com.chenfei.android.apk.downloader.ui.option.ProxyOption;
import com.chenfei.android.apk.downloader.util.ThreadUtils;
import com.chenfei.ui.base.panel.Panel;
import com.chenfei.ui.function.Function;

public class OptionFunction extends Function
{
    private Panel         optionBar = new Panel();

    private AccountOption accountOption;

    private ProxyOption   proxyOption;

    private CommonOption  commonOption;

    private Panel         buttonBar = new Panel();

    private JButton       saveButton;

    private JButton       resetButton;

    public OptionFunction()
    {
        super("选项");

        this.initOption(ConfigUtil.getConfig());
        this.initButton();

        this.getPanel().setLayout(new BoxLayout(this.getPanel(), BoxLayout.Y_AXIS));

        this.add(this.optionBar);
        this.add(Box.createVerticalStrut(10));
        this.add(this.buttonBar);
    }

    private void initOption(final Config config)
    {
        this.accountOption = new AccountOption(config.getAccountConfig());
        this.proxyOption = new ProxyOption(config.getProxyConfig());
        this.commonOption = new CommonOption(config.getCommonConfig());

        this.setLayout();

        this.optionBar.add(this.accountOption);
        this.optionBar.add(this.proxyOption);
        this.optionBar.add(this.commonOption);
        this.optionBar.updateUI();
    }

    private void setLayout()
    {
        this.optionBar.setLayout(new BoxLayout(this.optionBar, BoxLayout.Y_AXIS));

        this.accountOption.setLayout(new BoxLayout(this.accountOption, BoxLayout.Y_AXIS));
        this.proxyOption.setLayout(new BoxLayout(this.proxyOption, BoxLayout.Y_AXIS));
        this.commonOption.setLayout(new BoxLayout(this.commonOption, BoxLayout.Y_AXIS));
    }

    private void initButton()
    {
        this.resetButton = new JButton("重置");
        this.saveButton = new JButton("保存");

        this.buttonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

        this.buttonBar.add(this.resetButton);
        this.buttonBar.add(Box.createHorizontalStrut(10));
        this.buttonBar.add(this.saveButton);

        this.bindListener();
    }

    private Config getConfig()
    {
        final AccountConfig accountConfig = OptionFunction.this.accountOption.getConfig();
        final ProxyConfig proxyConfig = OptionFunction.this.proxyOption.getConfig();
        final CommonConfig commonConfig = OptionFunction.this.commonOption.getConfig();

        return new Config(accountConfig, proxyConfig, commonConfig);
    }

    private void bindListener()
    {
        this.saveButton.addMouseListener(new com.chenfei.ui.Listener()
        {
            @Override
            public void mouseClicked(final MouseEvent event)
            {

                ConfigUtil.saveConfig(OptionFunction.this.getConfig());

                APKDownloader.setStatus("已保存");

                ThreadUtils.sleep(300);

                new LoginWorker().execute();
            }
        });

        this.resetButton.addMouseListener(new com.chenfei.ui.Listener()
        {
            @Override
            public void mouseClicked(final MouseEvent e)
            {
                OptionFunction.this.optionBar.removeAll();

                OptionFunction.this.initOption(ConfigUtil.getConfig());

                APKDownloader.setStatus("已重置");
            }
        });
    }
}
