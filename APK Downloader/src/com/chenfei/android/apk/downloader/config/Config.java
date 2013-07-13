package com.chenfei.android.apk.downloader.config;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chenfei.android.apk.downloader.util.StringUtils;

public final class Config implements Serializable
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private AccountConfig accountConfig = new AccountConfig();

    private ProxyConfig proxyConfig = new ProxyConfig();

    private CommonConfig commonConfig = new CommonConfig();

    private SearchConfig searchConfig = new SearchConfig();

    public Config()
    {

    }

    public Config(final AccountConfig accountConfig, final ProxyConfig proxyConfig, final CommonConfig commonConfig)
    {
        this.accountConfig = accountConfig;
        this.proxyConfig = proxyConfig;
        this.commonConfig = commonConfig;
    }

    public boolean isNeedLogin(Config config)
    {
        if (!this.getAccountConfig().equals(config.getAccountConfig()))
        {
            return true;
        }
        if (!this.getProxyConfig().equals(config.getProxyConfig()))
        {
            return true;
        }
        return false;
    }

    public AccountConfig getAccountConfig()
    {
        return this.accountConfig;
    }

    public Config setAccountConfig(final AccountConfig accountConfig)
    {
        this.accountConfig = accountConfig;
        return this;
    }

    public ProxyConfig getProxyConfig()
    {
        return this.proxyConfig;
    }

    public Config setProxyConfig(final ProxyConfig proxyConfig)
    {
        this.proxyConfig = proxyConfig;
        return this;
    }

    public CommonConfig getCommonConfig()
    {
        return this.commonConfig;
    }

    public Config setCommonConfig(final CommonConfig commonConfig)
    {
        this.commonConfig = commonConfig;
        return this;
    }

    public SearchConfig getSearchConfig()
    {
        return searchConfig;
    }

    public Config setSearchConfig(SearchConfig searchConfig)
    {
        this.searchConfig = searchConfig;
        return this;
    }

    public String getEmail()
    {
        return this.accountConfig.getEmail();
    }

    public String getPassword()
    {
        return this.accountConfig.getPassword();
    }

    public String getDeviceID()
    {
        return this.accountConfig.getDeviceID();
    }

    public String getSavePath()
    {
        return this.commonConfig.getSavePath();
    }

    public Proxy getProxy()
    {
        return this.proxyConfig.getProxy();
    }

    public static class SearchConfig implements Serializable
    {
        /** 默认序列化版本 */
        private static final long serialVersionUID = 1L;

        private static final String REGEX = "play\\.google\\.com\\/store\\/apps\\/details\\?(?:|.*&)id=([\\w\\d\\.\\_]+)";

        private static final Pattern PATTERN = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);

        private transient String keywords;

        private Locale locale = Locale.getDefault();

        private int entriesCount = 10;

        public String getKeywords()
        {
            if (null != this.keywords)
            {
                Matcher matcher = PATTERN.matcher(this.keywords);
                if (matcher.find())
                {
                    return "pname:" + matcher.group(1);
                }
            }
            return this.keywords;
        }

        public SearchConfig setKeywords(String keywords)
        {
            this.keywords = keywords;
            return this;
        }

        public Locale getLocale()
        {
            return this.locale;
        }

        public SearchConfig setLocale(Locale locale)
        {
            this.locale = locale;
            return this;
        }

        public int getEntriesCount()
        {
            return this.entriesCount;
        }

        public SearchConfig setEntriesCount(int entriesCount)
        {
            this.entriesCount = entriesCount;
            return this;
        }
    }

    public static class CommonConfig implements Serializable
    {
        /** 默认序列化版本 */
        private static final long serialVersionUID = 1L;

        private String language;

        private String savePath = System.getProperty("user.home", "");

        public static void main(String[] args)
        {
            System.out.println(Locale.getDefault().getCountry());
        }

        public CommonConfig()
        {
            if ("CN".equalsIgnoreCase(Locale.getDefault().getCountry()))
            {
                this.language = "zh_CN";
            }
            else
            {
                this.language = "en_US";
            }
        }

        public String getLanguage()
        {
            return language;
        }

        public CommonConfig setLanguage(String language)
        {
            this.language = language;
            return this;
        }

        public String getSavePath()
        {
            return this.savePath;
        }

        public CommonConfig setSavePath(final String savePath)
        {
            this.savePath = savePath;
            return this;
        }
    }

    public static class AccountConfig implements Serializable
    {
        /** 默认序列化版本 */
        private static final long serialVersionUID = 1L;

        private String email;

        private String password;

        private String deviceID;

        public String getEmail()
        {
            return this.email;
        }

        public AccountConfig setEmail(final String email)
        {
            this.email = email;
            return this;
        }

        public String getPassword()
        {
            return this.password;
        }

        public AccountConfig setPassword(final String password)
        {
            this.password = password;
            return this;
        }

        public String getDeviceID()
        {
            return this.deviceID;
        }

        public AccountConfig setDeviceID(final String deviceID)
        {
            this.deviceID = deviceID;
            return this;
        }

        @Override
        public boolean equals(Object o)
        {
            if (null == o || !(o instanceof AccountConfig))
            {
                return false;
            }

            AccountConfig accountConfig = (AccountConfig) o;

            if (!StringUtils.isEquals(this.getEmail(), accountConfig.getEmail()))
            {
                return false;
            }

            if (!StringUtils.isEquals(this.getPassword(), accountConfig.getPassword()))
            {
                return false;
            }

            if (!StringUtils.isEquals(this.getDeviceID(), accountConfig.getDeviceID()))
            {
                return false;
            }

            return true;
        }
    }

    public static class ProxyConfig implements Serializable
    {
        /** 默认序列化版本 */
        private static final long serialVersionUID = 1L;

        private boolean enabled;

        private String host;

        private int port;

        public Proxy getProxy()
        {
            if (this.enabled)
            {
                return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.host, this.port));
            }
            else
            {
                return null;
            }
        }

        public String getHost()
        {
            return this.host;
        }

        public ProxyConfig setHost(final String host)
        {
            this.host = host;
            return this;
        }

        public String getPort()
        {
            if (0 == this.port)
            {
                return "";
            }

            return String.valueOf(this.port);
        }

        public ProxyConfig setPort(final int port)
        {
            this.port = port;
            return this;
        }

        public boolean isEnabled()
        {
            return this.enabled;
        }

        public ProxyConfig setEnabled(final boolean enabled)
        {
            this.enabled = enabled;
            return this;
        }

        @Override
        public boolean equals(Object o)
        {
            if (null == o || !(o instanceof ProxyConfig))
            {
                return false;
            }

            ProxyConfig proxyConfig = (ProxyConfig) o;

            if (proxyConfig.isEnabled() != this.isEnabled())
            {
                return false;
            }

            if (!StringUtils.isEquals(this.getHost(), proxyConfig.getHost()))
            {
                return false;
            }

            if (!StringUtils.isEquals(this.getPort(), proxyConfig.getPort()))
            {
                return false;
            }

            return true;
        }
    }

}
