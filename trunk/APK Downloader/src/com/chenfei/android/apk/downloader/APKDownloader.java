package com.chenfei.android.apk.downloader;

import java.awt.Color;
import java.awt.Image;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.chenfei.android.apk.downloader.session.SessionUtils;
import com.chenfei.android.apk.downloader.ui.AboutFunction;
import com.chenfei.android.apk.downloader.ui.HomeFunction;
import com.chenfei.android.apk.downloader.ui.OptionFunction;
import com.chenfei.android.apk.downloader.ui.i18n.I18N;
import com.chenfei.android.apk.downloader.util.DateUtils;
import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.UI;
import com.chenfei.ui.UIBuilder;
import com.chenfei.ui.application.Application;

public class APKDownloader
{
    private static final Logger LOG = Logger.getLogger(APKDownloader.class);

    private static Application application;

    /**设置应用程序状态
     */
    public static void setStatus(final Object statusO)
    {
        String status = String.valueOf(statusO) + "    " + DateUtils.getNow(I18N.get("status.bar.date.pattern"));

        application.setStatus(new JLabel(status));
    }

    private static String getTitle()
    {
        Properties properties = new Properties();
        try
        {
            properties.load(APKDownloader.class.getResourceAsStream("/APK Downloader.properties"));
        }
        catch (Exception e)
        {
            LOG.error(e);
        }
        return properties.getProperty("name") + " " + properties.getProperty("version");
    }

    public static void main(final String[] args)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Application started.");
        }

        try
        {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;

            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch (Exception e)
        {
            LOG.error("BeautyEyeLNF load error.", e);
        }

        UIManager.put("RootPane.setupButtonVisible", false);
        UIManager.put("SplitPane.oneTouchButtonSize", 10);

        final Image ico = new ImageIcon(APKDownloader.class.getResource("/APK Downloader.png")).getImage();

        application =
            new UIBuilder().put(UI.WINDOW, new CSS().setWidth(900).setHeight(600))
                .put(UI.STATUS_BAR, new CSS().setBackground(Color.WHITE))
                .put(UI.WINDOW, new Attribute().setTitle(getTitle()).setIco(ico))
                .put(UI.STATUS_BAR, new Attribute().setName(I18N.get("status.bar")))
                .add(new HomeFunction())
                .add(new OptionFunction())
                .add(new AboutFunction(APKDownloader.class.getResource("/about.html")))
                .build();

        setStatus(SessionUtils.getSession().isLogged() ? I18N.get("login.successful") : I18N.get("login.unsuccessful"));

        if (LOG.isDebugEnabled())
        {
            LOG.debug("Application startup success.");
        }
    }
}