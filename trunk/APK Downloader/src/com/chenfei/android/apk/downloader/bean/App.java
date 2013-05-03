package com.chenfei.android.apk.downloader.bean;

import java.awt.Image;
import java.io.File;
import java.text.NumberFormat;

import javax.swing.ImageIcon;

public class App
{
    private String id;

    private Image image;

    private String title;

    private String packageName;

    private String creator;

    private String version;

    /** 单位：字节 */
    private Size size;

    private String price;

    /** 下载存放的文件 */
    private File file;

    public Object[] getAppInfo()
    {
        Object[] appInfo = new Object[8];
        appInfo[0] = new ImageIcon(this.image);
        appInfo[1] = this.title;
        appInfo[2] = this.packageName;
        appInfo[3] = this.creator;
        appInfo[4] = this.version;
        appInfo[5] = this.size;
        appInfo[6] = this.price;
        appInfo[7] = this;
        return appInfo;
    }

    public String getFileName()
    {
        String fileName = this.getTitle().trim() + this.getVersion().trim() + ".apk";

        return fileName.replaceAll("[\\\\/:*?\"<>|]", "-");
    }

    public String getID()
    {
        return this.id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public Image getImage()
    {
        return this.image;
    }

    public Image getImage(final int width, final int height, final int hints)
    {
        return this.image.getScaledInstance(width, height, hints);
    }

    public void setImage(final Image image)
    {
        this.image = image;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(final String title)
    {
        this.title = title;
    }

    public String getPackageName()
    {
        return this.packageName;
    }

    public void setPackageName(final String packageName)
    {
        this.packageName = packageName;
    }

    public String getCreator()
    {
        return this.creator;
    }

    public void setCreator(final String creator)
    {
        this.creator = creator;
    }

    public String getVersion()
    {
        return this.version;
    }

    public void setVersion(final String version)
    {
        this.version = version;
    }

    public Size getSize()
    {
        return this.size;
    }

    public void setSize(final int bytes)
    {
        this.size = new Size(bytes);
    }

    public void setSize(final Size size)
    {
        this.size = size;
    }

    public String getPrice()
    {
        return this.price;
    }

    public void setPrice(final String price)
    {
        if (null == price || "".equals(price))
        {
            this.price = "免费";
        }
        else
        {
            this.price = price;
        }
    }

    public File getFile()
    {
        return this.file;
    }

    public void setFile(final File file)
    {
        this.file = file;
    }

    @Override
    public String toString()
    {
        return "App [id=" + this.id + ", image=" + this.image + ", title=" + this.title + ", packageName="
            + this.packageName + ", creator=" + this.creator + ", version=" + this.version + ", size=" + this.size
            + ", price=" + this.price + "]";
    }

    public static class Size implements Comparable<Size>
    {
        private int bytes;

        public Size(final int bytes)
        {
            this.bytes = bytes;
        }

        @Override
        public String toString()
        {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(1);

            if (this.bytes / 1024D / 1024D < 1)
            {
                return numberFormat.format(this.bytes / 1024D) + "KB";
            }

            return numberFormat.format(this.bytes / 1024D / 1024D) + "MB";
        }

        @Override
        public int compareTo(final Size size)
        {
            return this.bytes - size.getBytes();
        }

        public int getBytes()
        {
            return this.bytes;
        }
    }
}
