package com.chenfei.android.apk.downloader;

import java.awt.Color;
import java.awt.Image;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.chenfei.android.apk.downloader.session.SessionUtil;
import com.chenfei.android.apk.downloader.ui.AboutFunction;
import com.chenfei.android.apk.downloader.ui.HomeFunction;
import com.chenfei.android.apk.downloader.ui.OptionFunction;
import com.chenfei.ui.Attribute;
import com.chenfei.ui.CSS;
import com.chenfei.ui.UI;
import com.chenfei.ui.UIBuilder;
import com.chenfei.ui.application.Application;

public class APKDownloader
{
    private static final Logger LOG = Logger.getLogger(APKDownloader.class);

    private static final Properties PROPERTIES = new Properties();

    private static Application application;

    /**设置应用程序状态
     */
    public static void setStatus(final Object statusO)
    {
        JLabel status = new JLabel(String.valueOf(statusO) + "    " + new Date());

        application.setStatus(status);
    }

    private static String getTitle()
    {
        try
        {
            PROPERTIES.load(APKDownloader.class.getResourceAsStream("/APK Downloader.properties"));
        }
        catch (Exception e)
        {
            LOG.error(e);
        }

        return PROPERTIES.getProperty("name", "APK Downloader") + " " + PROPERTIES.getProperty("version", "");
    }

    public static void main(final String[] args)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Application started.");
        }

        try
        {
            // 设置本属性将改变窗口边框样式定义
            if (System.getProperty("os.name").indexOf("Windows") < 0)
            {
                // Linux/Mac OS
                BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            }
            else
            {
                // Windows
                BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
            }

            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch (Exception e)
        {
            LOG.error("BeautyEyeLNF load error.", e);
        }

        UIManager.put("RootPane.setupButtonVisible", false);

        final Image ico = new ImageIcon(APKDownloader.class.getResource("/APK Downloader.png")).getImage();

        application =
            new UIBuilder().put(UI.WINDOW, new CSS().setWidth(900).setHeight(600))
                .put(UI.STATUS_BAR, new CSS().setBackground(Color.WHITE))
                .put(UI.WINDOW, new Attribute().setTitle(getTitle()).setIco(ico))
                .add(new HomeFunction())
                .add(new OptionFunction())
                .add(new AboutFunction(APKDownloader.class.getResource("/about.html")))
                .build();

        setStatus(SessionUtil.getSession().isLogged() ? "登录成功" : "登录失败");

        if (LOG.isDebugEnabled())
        {
            LOG.debug("Application startup success.");
        }
    }
}