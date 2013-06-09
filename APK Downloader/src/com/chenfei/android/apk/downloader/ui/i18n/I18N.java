package com.chenfei.android.apk.downloader.ui.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.config.ConfigUtils;

public class I18N
{
    private static final Logger LOG = Logger.getLogger(I18N.class);

    private static final Map<String, Properties> PROPERTIES_MAP = new HashMap<String, Properties>();

    private static final Map<String, String> NAME_LANGUAGE_MAP = new LinkedHashMap<String, String>();

    private static final Map<String, String> LANGUAGE_NAME_MAP = new LinkedHashMap<String, String>();

    static
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Load i18n starting.");
        }

        PROPERTIES_MAP.put("zh_CN", loadProperties("language/zh_CN.properties"));
        PROPERTIES_MAP.put("en_US", loadProperties("language/en_US.properties"));

        NAME_LANGUAGE_MAP.put("简体中文", "zh_CN");
        NAME_LANGUAGE_MAP.put("English", "en_US");

        LANGUAGE_NAME_MAP.put("zh_CN", "简体中文");
        LANGUAGE_NAME_MAP.put("en_US", "English");

        if (LOG.isDebugEnabled())
        {
            LOG.debug("Load i18n ended.");
        }
    }

    public static String[] getLanguageNames()
    {
        return NAME_LANGUAGE_MAP.keySet().toArray(new String[2]);
    }

    public static String getLanguageName(String language)
    {
        return LANGUAGE_NAME_MAP.get(language);
    }

    public static String getLanguage(String name)
    {
        return NAME_LANGUAGE_MAP.get(name);
    }

    public static String get(final String key)
    {
        return getProperties().getProperty(key);
    }

    public static String get(final String key, Object... arguments)
    {
        String i18nPattern = get(key);
        return MessageFormat.format(i18nPattern, arguments);
    }

    private static Properties loadProperties(String path)
    {
        Properties properties = new Properties();
        try
        {
            properties.load(new FileInputStream(new File(path)));
        }
        catch (FileNotFoundException e)
        {
            LOG.error("找不到资源文件。", e);
        }
        catch (IOException e)
        {
            LOG.error("资源文件加载失败。", e);
        }
        return properties;
    }

    private static Properties getProperties()
    {
        Properties properties = PROPERTIES_MAP.get(ConfigUtils.getConfig().getCommonConfig().getLanguage());

        if (null == properties)
        {
            properties = PROPERTIES_MAP.get(Locale.CHINA.toString());
        }

        return properties;
    }

    private I18N()
    {
    }
}
