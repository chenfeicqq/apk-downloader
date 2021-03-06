package com.chenfei.android.apk.downloader.ui.home.download;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

import org.apache.log4j.Logger;

import com.chenfei.android.apk.downloader.bean.App;
import com.chenfei.android.apk.downloader.config.ConfigUtils;
import com.chenfei.android.apk.downloader.session.Session.DownloadListener;
import com.chenfei.android.apk.downloader.ui.i18n.I18N;
import com.chenfei.ui.Listener;
import com.chenfei.ui.base.ProgressBar;
import com.chenfei.ui.base.panel.Panel;

public class DownloadItem extends Panel implements DownloadListener
{
    /** 删除事件 */
    public static final String DELETEED = "deleteed";

    /** 默认序列化版本 */
    private static final long serialVersionUID = 1L;

    /** 日志 */
    private static final Logger LOG = Logger.getLogger(DownloadItem.class);

    private App app;

    /** 下载完成百分占比 */
    private int downloaded;

    private DownloadWorker downloadWorder;

    private Timer timer;

    private JLabel ico;

    private ProgressBar progressBar;

    private JButton cancelButton;

    private JButton retryButton;

    private JButton deleteButton;

    public DownloadItem(final App app)
    {
        this.app = app;

        super.setLayout(null);
        // 宽度保持和DownloadFunction一致
        super.setPreferredSize(new Dimension(210, 40));
        super.setMaximumSize(super.getPreferredSize());
        super.setMinimumSize(super.getPreferredSize());

        this.initUI();

        this.bindListener();

        super.add(this.ico);
        super.add(this.progressBar);
        super.add(this.cancelButton);
        super.add(this.deleteButton);

        this.download();
    }

    public String getAppID()
    {
        return this.app.getID();
    }

    private void initUI()
    {
        this.ico = new JLabel(new ImageIcon(this.app.getImage(29, 29, Image.SCALE_AREA_AVERAGING)));
        this.ico.setBounds(0, 0, 29, 29);

        this.progressBar = new ProgressBar();
        this.progressBar.setBounds(30, 0, 100, 30);

        this.cancelButton = new JButton(I18N.get("download.button.cancel"));
        this.cancelButton.setToolTipText(I18N.get("download.button.cancel.tip"));
        this.cancelButton.setBounds(131, 0, 59, 14);

        this.retryButton = new JButton(I18N.get("download.button.retry"));
        this.retryButton.setToolTipText(I18N.get("download.button.retry.tip"));
        this.retryButton.setBounds(131, 0, 59, 14);

        this.deleteButton = new JButton(I18N.get("download.button.delete"));
        this.deleteButton.setToolTipText(I18N.get("download.button.delete.tip"));
        this.deleteButton.setBounds(131, 15, 59, 14);
    }

    private void bindListener()
    {
        if (Desktop.isDesktopSupported())
        {
            // 支持Desktop，双击打开文件所在目录
            this.ico.addMouseListener(new Listener()
            {
                @Override
                public void mouseClicked(final MouseEvent event)
                {
                    if (event.getClickCount() == 2)
                    {
                        // 默认打开目录：配置的保存目录
                        File saveDirectory = new File(ConfigUtils.getConfig().getSavePath());

                        // 下载文件不为空，打开文件所在目录
                        File appFile = DownloadItem.this.app.getFile();
                        if (null != appFile)
                        {
                            try
                            {
                                saveDirectory = appFile.getCanonicalFile().getParentFile();
                            }
                            catch (Exception e)
                            {
                                LOG.error("Get the download directory error.", e);
                            }
                        }

                        if (saveDirectory != null && saveDirectory.exists())
                        {
                            try
                            {
                                Desktop.getDesktop().open(saveDirectory);
                            }
                            catch (Exception e)
                            {
                                LOG.error("Open the download directory error.", e);
                            }
                        }
                    }
                }
            });
        }

        this.cancelButton.addMouseListener(new Listener()
        {
            @Override
            public void mouseClicked(final MouseEvent event)
            {
                // 取消下载
                DownloadItem.this.cancel();

                // 提示信息
                DownloadItem.this.setStatus(I18N.get("download.status.cancelled"));

                DownloadItem.this.cancel2retry();

                // 点击取消时，如果下载已完成，则将下载完成的文件删除
                if (DownloadItem.this.isDone())
                {
                    DownloadItem.this.app.getFile().delete();
                }
            }
        });

        this.retryButton.addMouseListener(new Listener()
        {
            @Override
            public void mouseClicked(final MouseEvent event)
            {
                DownloadItem.this.download();

                DownloadItem.this.setStatus(null);

                DownloadItem.this.retry2cancel();
            }
        });

        this.deleteButton.addMouseListener(new Listener()
        {
            @Override
            public void mouseClicked(final MouseEvent event)
            {
                // 取消下载
                DownloadItem.this.cancel();

                // 触发删除事件
                DownloadItem.this.firePropertyChange(DELETEED, false, true);
            }
        });
    }

    /** 取消下载
     */
    private void cancel()
    {
        this.timer.stop();
        this.downloadWorder.cancel(true);
    }

    private void retry2cancel()
    {
        super.add(this.cancelButton);
        super.remove(this.retryButton);
        super.updateUI();
    }

    private void cancel2retry()
    {
        super.add(this.retryButton);
        super.remove(this.cancelButton);
        super.updateUI();
    }

    private boolean isDone()
    {
        return 100 == this.downloaded;
    }

    private void setStatus(final String status)
    {
        this.progressBar.setString(status);
    }

    private void refreshProgress()
    {
        this.progressBar.setValue(this.downloaded);
    }

    private void download()
    {
        this.downloaded = 0;

        this.refreshProgress();

        this.timer = new Timer(100, new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                DownloadItem.this.refreshProgress();

                // 如果下载完成
                if (DownloadItem.this.isDone())
                {
                    // 提示信息
                    DownloadItem.this.setStatus(I18N.get("download.status.successful"));

                    // 将取消按钮变为重试按钮
                    DownloadItem.this.cancel2retry();

                    // 结束巡检
                    DownloadItem.this.timer.stop();
                }
            }
        });

        this.downloadWorder = new DownloadWorker(this.app, this);

        this.downloadWorder.addPropertyChangeListener(new Listener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent event)
            {
                if (DownloadWorker.ERROR.endsWith(event.getPropertyName()))
                {
                    DownloadItem.this.cancel();

                    String status = DownloadItem.this.downloadWorder.getStatus();
                    if (status == null)
                    {
                        status = I18N.get("download.status.unsuccessful");
                    }

                    // 提示信息
                    DownloadItem.this.setStatus(status);
                    DownloadItem.this.cancel2retry();
                }
            }
        });

        this.downloadWorder.execute();
        this.timer.start();
    }

    @Override
    public void downloaded(final long downloaded, final long total)
    {
        this.downloaded = (int)(downloaded * 100 / total);
    }
}