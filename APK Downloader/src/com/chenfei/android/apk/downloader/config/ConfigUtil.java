package com.chenfei.android.apk.downloader.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.util.IOUtils;
import com.gc.android.market.api.common.Base64;

public final class ConfigUtil
{
    private static final Logger LOG = Logger.getLogger(ConfigUtil.class);

    private static final File CONFIG_FILE = new File("APK Downloader.dat");

    private static Config config;

    static
    {
        config = ConfigUtil.loadConfig();

        if (null == config)
        {
            ConfigUtil.saveConfig(new Config());
        }
    }

    public static Config getConfig()
    {
        return ConfigUtil.config;
    }

    /** 保存配置项
     *  将配置刷新到内存中，并重新启动线程将配置保存到配置文件中，忽略是否保存成功
     */
    public static void saveConfig(final Config config)
    {
        ConfigUtil.config = config;

        new SwingWorker<Boolean, Void>()
        {
            @Override
            protected Boolean doInBackground()
            {
                return ConfigUtil.saveConfig();
            }
        }.execute();
    }

    private static boolean saveConfig()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Save config starting.");
        }

        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(CONFIG_FILE), "UTF-8"));

            writer.write(Base64.encodeObject(config));

            return true;
        }
        catch (IOException e)
        {
            LOG.debug("Save config error.", e);

            return false;
        }
        finally
        {
            IOUtils.close(writer);

            if (LOG.isDebugEnabled())
            {
                LOG.debug("Save config ended.");
            }
        }
    }

    private static Config loadConfig()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Load config starting.");
        }

        if (!CONFIG_FILE.exists())
        {
            LOG.error("Config file is not exist.");
            return null;
        }

        if (!CONFIG_FILE.isFile())
        {
            LOG.error("Config file is not a normal file.");
            return null;
        }

        Scanner configScanner = null;
        try
        {
            configScanner = new Scanner(CONFIG_FILE);

            return (Config)Base64.decodeToObject(configScanner.nextLine());
        }
        catch (Exception e)
        {
            LOG.debug("Load config error.", e);
        }
        finally
        {
            IOUtils.close(configScanner);
        }

        if (LOG.isDebugEnabled())
        {
            LOG.debug("Load config ended.");
        }

        return null;
    }

    private ConfigUtil()
    {
    }
}
