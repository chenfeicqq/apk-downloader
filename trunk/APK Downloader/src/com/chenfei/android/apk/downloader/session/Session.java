package com.chenfei.android.apk.downloader.session;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.config.Config;
import com.chenfei.android.apk.downloader.util.FileUtils;
import com.chenfei.android.apk.downloader.util.IOUtils;
import com.chenfei.android.apk.downloader.util.ThreadUtils;
import com.gc.android.market.api.MarketSession;
import com.gc.android.market.api.MarketSession.Callback;
import com.gc.android.market.api.model.Market.AppsRequest;
import com.gc.android.market.api.model.Market.AppsRequest.ViewType;
import com.gc.android.market.api.model.Market.AppsResponse;
import com.gc.android.market.api.model.Market.GetAssetResponse.InstallAsset;
import com.gc.android.market.api.model.Market.GetImageRequest;
import com.gc.android.market.api.model.Market.GetImageRequest.AppImageUsage;
import com.gc.android.market.api.model.Market.GetImageResponse;
import com.gc.android.market.api.model.Market.ResponseContext;

public class Session
{
    private static final Logger LOG = Logger.getLogger(Session.class);

    /** 查询App使用的session */
    private MarketSession session = new MarketSession(false);

    /** 下载App使用的session */
    private MarketSession downloadSession = new MarketSession(true);

    private Config config;

    private boolean logged;

    public Session(final Config config)
    {
        this.config = config;

        this.login();
    }

    private void login()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Begin login.");
        }
        try
        {
            this.session.login(this.config.getEmail(),
                this.config.getPassword(),
                this.config.getDeviceID(),
                this.config.getProxy());

            this.downloadSession.login(this.config.getEmail(),
                this.config.getPassword(),
                this.config.getDeviceID(),
                this.config.getProxy());

            this.logged = true;

            if (LOG.isDebugEnabled())
            {
                LOG.debug("Login success.");
            }
        }
        catch (Exception e)
        {
            LOG.error("Login error.", e);

            this.logged = false;
        }

        if (LOG.isDebugEnabled())
        {
            LOG.debug("End login.");
        }
    }

    public boolean refreshLocale(final Locale locale)
    {
        try
        {
            this.session.setLocale(locale);
            this.downloadSession.setLocale(locale);

            return true;
        }
        catch (Exception e)
        {
            LOG.error("Set locale error.", e);

            return false;
        }
    }

    public boolean isLogged()
    {
        return this.logged;
    }

    public List<App> searchApps(final Search search)
    {
        final List<App> appList = new LinkedList<App>();

        int index = 0, size = search.getEntriesCount(), flushSize = 0;
        while (index < size)
        {
            AppsRequest appsRequest =
                AppsRequest.newBuilder()
                    .setQuery(search.getSearchKeyword())
                    .setStartIndex(index)
                    .setEntriesCount(10)
                    .setWithExtendedInfo(true)
                    .setViewType(ViewType.ALL)
                    .build();

            this.session.append(appsRequest, new Callback<AppsResponse>()
            {
                @Override
                public void onResult(final ResponseContext context, final AppsResponse response)
                {
                    for (com.gc.android.market.api.model.Market.App appInfo : response.getAppList())
                    {
                        App app = new App();
                        app.setId(appInfo.getId());
                        app.setTitle(appInfo.getTitle());
                        app.setPackageName(appInfo.getPackageName());
                        app.setCreator(appInfo.getCreator());
                        app.setVersion(appInfo.getVersion());
                        app.setSize(appInfo.getExtendedInfo().getInstallSize());
                        app.setPrice(appInfo.getPrice());
                        appList.add(app);
                    }
                }
            });

            index += 10;

            // 最多每次获取50个结果，超出50分批发送请求
            if (index % 50 == 0)
            {
                this.session.flush();
                flushSize = index;
            }
        }
        if (flushSize < size)
        {
            this.session.flush();
        }

        this.getAppImage(appList);

        return appList;
    }

    private void getAppImage(final List<App> appList)
    {
        if (appList.isEmpty())
        {
            return;
        }

        int index = 0, size = appList.size(), flushSize = 0;
        while (index < size)
        {
            final App app = appList.get(index);
            GetImageRequest getImageRequest =
                GetImageRequest.newBuilder()
                    .setAppId(app.getID())
                    .setImageUsage(AppImageUsage.ICON)
                    .setImageId("1")
                    .build();

            this.session.append(getImageRequest, new Callback<GetImageResponse>()
            {
                @Override
                public void onResult(final ResponseContext context, final GetImageResponse response)
                {
                    Image image = Toolkit.getDefaultToolkit().createImage(response.getImageData().toByteArray());
                    app.setImage(image);
                }
            });

            index++;

            // 如果列表超出10个，则每10个提交一次
            if (index % 10 == 0)
            {
                this.session.flush();
                flushSize = index;
            }
        }
        if (flushSize < size)
        {
            this.session.flush();
        }
    }

    public void downloadApp(final App app, final DownloadListener listener)
        throws IOException
    {
        // 如果下载目录不存在
        if (!FileUtils.exists(this.config.getSavePath()))
        {
            throw new DownloadException("下载目录不存在");
        }

        InstallAsset installAsset = this.downloadSession.queryGetAssetRequest(app.getID()).getInstallAsset(0);
        String cookieName = installAsset.getDownloadAuthCookieName();
        String cookieValue = installAsset.getDownloadAuthCookieValue();

        URL downloadURL = new URL(installAsset.getBlobUrl());
        HttpURLConnection downloadConn = null;

        Proxy proxy = this.config.getProxy();
        if (null == proxy)
        {
            downloadConn = (HttpURLConnection)downloadURL.openConnection();
        }
        else
        {
            downloadConn = (HttpURLConnection)downloadURL.openConnection(proxy);
        }

        downloadConn.setRequestMethod("GET");
        downloadConn.setRequestProperty("User-Agent", "Android-Market/2 (sapphire PLAT-RC33); gzip");
        downloadConn.setRequestProperty("Cookie", cookieName + "=" + cookieValue);

        if (downloadConn.getResponseCode() == 302)
        {
            String location = downloadConn.getHeaderField("Location");
            downloadURL = new URL(location);
            downloadConn = (HttpURLConnection)downloadURL.openConnection();
            downloadConn.setRequestMethod("GET");
            downloadConn.setRequestProperty("User-Agent", "Android-Market/2 (sapphire PLAT-RC33); gzip");
            downloadConn.setRequestProperty("Cookie", cookieName + "=" + cookieValue);
        }

        boolean downloadResult = true;
        InputStream input = null;
        OutputStream output = null;

        File tempFile = this.getDownloadFile(app.getFileName() + ".download");
        app.setFile(tempFile);
        File saveFile = this.getDownloadFile(app.getFileName());

        try
        {
            input = downloadConn.getInputStream();

            output = new FileOutputStream(tempFile);

            int total = downloadConn.getContentLength();
            long downloaded = 0L;
            byte[] buffer = new byte[1024 * 50];
            for (int n = 0; -1 != (n = input.read(buffer));)
            {
                if (ThreadUtils.isInterrupted())
                {
                    downloadResult = false;
                    break;
                }

                output.write(buffer, 0, n);
                downloaded += n;

                listener.downloaded(downloaded, total);
            }
        }
        finally
        {
            IOUtils.close(input, output);
            IOUtils.close(downloadConn);

            if (downloadResult)
            {
                tempFile.renameTo(saveFile);
                app.setFile(saveFile);
            }
            else
            {
                tempFile.delete();
            }
        }
    }

    private File getDownloadFile(final String fileName)
    {
        String savePath = this.config.getSavePath();
        if (null == savePath || "".equals(savePath))
        {
            return new File(fileName);
        }
        else
        {
            return new File(savePath, fileName);
        }
    }

    public static interface DownloadListener
    {
        void downloaded(long downloaded, long total);
    }

    public static class DownloadException extends RuntimeException
    {
        /**  */
        private static final long serialVersionUID = 1L;

        public DownloadException(final String message)
        {
            super(message);
        }
    }

    public static class Search
    {
        private String searchKeyword;

        private int entriesCount = 10;

        public int getEntriesCount()
        {
            return this.entriesCount;
        }

        public void setEntriesCount(final int entriesCount)
        {
            this.entriesCount = entriesCount;
        }

        public String getSearchKeyword()
        {
            return this.searchKeyword;
        }

        public void setSearchKeyword(final String searchKeyword)
        {
            this.searchKeyword = searchKeyword;
        }
    }
}
