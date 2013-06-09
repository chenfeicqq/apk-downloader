package com.chenfei.android.apk.downloader.session;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.chenfei.android.apk.downloader.APKDownloader;
import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.config.Config;
import com.chenfei.android.apk.downloader.util.FileUtils;

public class SessionTest extends Session
{

    private Config config;

    public SessionTest(final Config config)
    {
        super(config);

        this.config = config;
    }

    @Override
    public boolean isLogged()
    {
        return true;
    }

    @Override
    public List<App> searchApps()
    {
        if (Math.random() * 10 > 5)
        {
            return super.searchApps();
        }

        List<App> appList = new ArrayList<App>();

        for (int i = 0; i < 40; i++)
        {
            String id = String.valueOf(i);

            App app = new App();
            app.setId(id);
            app.setTitle("Test" + id);
            app.setPackageName("com.test" + id);
            app.setCreator("T" + id);
            app.setVersion("1.0.0");
            app.setSize(i * 1024);
            app.setPrice(null);
            app.setImage(new ImageIcon(APKDownloader.class.getResource("/APK Downloader.png")).getImage());
            appList.add(app);
        }

        try
        {
            Thread.sleep(2000);
        }
        catch (Exception e)
        {

        }

        return appList;
    }

    @Override
    public void downloadApp(final App app, final DownloadListener listener)
        throws IOException
    {
        // 如果下载目录不存在
        if (!FileUtils.exists(this.config.getSavePath()))
        {
            throw new DownloadException("下载目录不存在");
        }

        boolean downloadResult = false;

        File tempFile = this.getDownloadFile(app.getFileName() + ".download");
        app.setFile(tempFile);
        File saveFile = this.getDownloadFile(app.getFileName());

        try
        {

            int total = 1024 * 1024 * 10;
            long downloaded = 0L;
            while (downloaded < total)
            {
                downloaded += 100 * 1024;

                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    break;
                }

                listener.downloaded(downloaded, total);
            }

            downloadResult = true;
        }
        finally
        {
            if (downloadResult)
            {
                app.setFile(saveFile);
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
}