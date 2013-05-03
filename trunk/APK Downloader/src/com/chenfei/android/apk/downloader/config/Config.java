package com.chenfei.android.apk.downloader.config;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Proxy;

public final class Config implements Serializable
{
    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    private AccountConfig accountConfig = new AccountConfig();

    private ProxyConfig proxyConfig = new ProxyConfig();

    private CommonConfig commonConfig = new CommonConfig();

    public Config()
    {

    }

    public Config(final AccountConfig accountConfig, final ProxyConfig proxyConfig, final CommonConfig commonConfig)
    {
        this.accountConfig = accountConfig;
        this.proxyConfig = proxyConfig;
        this.commonConfig = commonConfig;
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

    public static class CommonConfig implements Serializable
    {
        /** 默认序列化版本 */
        private static final long serialVersionUID = 1L;

        private String savePath = System.getProperty("user.home", "");

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
    }

    public static class ProxyConfig implements Serializable
    {
        /** 默认序列化版本 */
        private static final long serialVersionUID = 1L;

        private boolean enabled;

        private String hostName;

        private int port;

        public Proxy getProxy()
        {
            if (this.enabled)
            {
                return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.hostName, this.port));
            }
            else
            {
                return null;
            }
        }

        public String getHostName()
        {
            return this.hostName;
        }

        public ProxyConfig setHostName(final String hostName)
        {
            this.hostName = hostName;
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
    }
}
