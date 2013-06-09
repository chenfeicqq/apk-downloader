package com.chenfei.android.apk.downloader.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.common.Callback;
import com.chenfei.android.apk.downloader.util.IOUtils;
import com.gc.android.market.api.common.Base64;

public final class ConfigUtils
{
    private static final Logger LOG = Logger.getLogger(ConfigUtils.class);

    private static final File CONFIG_FILE = new File("APK Downloader.dat");

    private static Config config;

    static
    {
        config = ConfigUtils.loadConfig();

        if (null == config)
        {
            ConfigUtils.saveConfig(new Config(), null);
        }
    }

    public static Config getConfig()
    {
        return ConfigUtils.config;
    }

    /** 保存配置项
     *  将配置刷新到内存中，并重新启动线程将配置保存到配置文件中，忽略是否保存成功
     */
    public static void saveConfig(final Config config, final Callback<Boolean> callback)
    {
        ConfigUtils.config = config;

        saveConfig(callback);
    }

    public static void saveConfig(final Callback<Boolean> callback)
    {
        new SwingWorker<Boolean, Void>()
        {
            @Override
            protected Boolean doInBackground()
            {
                return ConfigUtils.saveConfig();
            }

            @Override
            protected void done()
            {
                if (null != callback)
                {
                    Boolean result = Boolean.FALSE;
                    try
                    {
                        result = this.get();
                    }
                    catch (InterruptedException e)
                    {
                        LOG.error(e);
                    }
                    catch (ExecutionException e)
                    {
                        LOG.error(e);
                    }
                    callback.callback(result);
                }
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

            writer.write(encode(config));

            return true;
        }
        catch (Exception e)
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

            return decode(configScanner.nextLine());
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

    private static final String KEY = "!~@#n$%^w&*9u)_+";

    private static String encode(Config config) throws Exception
    {
        String configStr = Base64.encodeObject(config);

        byte[] keyByte = KEY.getBytes();

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyByte, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(keyByte);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);


        return Base64.encodeBytes(cipher.doFinal(configStr.getBytes()));
    }

    private static Config decode(String code) throws Exception
    {
        byte[] keyByte = KEY.getBytes("ASCII");

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyByte, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(keyByte);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        return (Config) Base64.decodeToObject(new String(cipher.doFinal(Base64.decode(code))));
    }

    private ConfigUtils()
    {
    }
}
