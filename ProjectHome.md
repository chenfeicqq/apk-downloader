**由于Google官方下载链接出现问题，目前已无法下载**

# APK Downloader #
(Now this app is support [english](https://code.google.com/p/apk-downloader/wiki/en_US), if you want support other language, send e-mail for me(chenfeicqq@gmail.com), i need you help.)

> [使用技巧](https://code.google.com/p/apk-downloader/wiki/Skill)

  * Windows（1.4.2）
    1. [下载](https://apk-downloader.googlecode.com/files/APK%20Downloader-1.4.2-Windows-All.zip)（文件较大，下载耗时较长，包含程序运行必须的JRE）
    1. [下载](https://apk-downloader.googlecode.com/files/APK%20Downloader-1.4.2-Windows-ExcludeJRE.zip)（文件较小，下载耗时短，如果您的电脑上已安装JRE1.6或以上版本）
  * Linux/Mac OS/Unix（1.4.2）
    1. [下载](https://apk-downloader.googlecode.com/files/APK%20Downloader-1.4.2-Other-ExcludeJRE.zip)（需要您的系统中已安装JRE1.6或以上版本）
    1. 使用说明：解压后，在控制台下进入程序目录，执行命令：
```
sh APK\ Downloader.sh
```

---

## 最新版本特性 ##
> [所有记录](https://code.google.com/p/apk-downloader/wiki/History)
> |2013.07.13|**1.4.2**|建议更新|功能完善|
|:---------|:--------|:-----------|:-----------|
    1. 新增特性：直接输入Google Play应用的地址（URL）进行搜索
> |2013.06.13|**1.4.1**|已经升级1.4.0可忽略该版本|细节优化|
    1. 首次打开时，根据用户的操作系统语言，智能选择程序语言是中文还是英文
> |2013.06.09|**1.4.0**|建议更新|功能完善及细节优化|覆盖安装需要把APK Downloader.dat删除重新配置|
    1. 增加语言选项（简体中文/English）
    1. 自动记录搜索使用的国家及搜索结果数，下次启动后获取上次搜索使用的配置
    1. 配置项保存优化（只有修改配置项涉及登录及代理信息时才触发重新登录）
    1. 优化搜索结果按大小排序的逻辑
    1. 增大搜索面板和下载面板之间控制按钮的大小
    1. BeautyEye升级至3.5（非Windows平台部分UI优化）
    1. 数据加密升级，极大提升本地信息安全性


---

  * 本程序不会盗取您的帐号信息，请至<a href='https://code.google.com/p/apk-downloader/'>主页</a>进行下载，其他地方下载不保证程序安全性。

  * 本程序使用Java开发，需要JRE（Java运行环境）的支持才能使用。

  * 编写该程序的初衷：
    1. ~~最早使用Chrome浏览器插件下载，现在好像已经不能正常使用了，作者也没有进行后续的维护更新~~（最新版1.4.3已支持下载）
    1. 后来换成RealAPKLeecher，但是由于Google被墙的原因，经常服务不稳定，且界面太简陋

---

## 已知问题 ##
|搜索的时候如果打开中文输入法，会无法关闭程序，必须杀进程。建议您关闭该程序前，先将中文输入法关闭|
|:-----------------------------------------------------------------------------------------------------------------------------------------------|


---

## 使用说明 ##
> 【选项】设置：
    * 登录信息( **下面三项必须正确填写** )
      1. 用户名
      1. 密码（如果您开启了Google两步验证，请输入应用专用密码，而不是帐户密码）
      1. 手机DeviceID（可安装[DeviceID](https://apk-downloader.googlecode.com/files/DeviceID-1.0.apk)来获取您的手机的Device ID）
    * 代理
    * 下载存放目录
    * 登录（使用当前配置进行登录）
    * 保存（保存当前配置，保存成功后自动登录）

> 【搜索】
    * 输入关键词后，回车

> 【下载】
    * Ctrl任意多选
    * Shift连续多选

## 图例 ##
> ![https://apk-downloader.googlecode.com/files/option.1.4.0.png](https://apk-downloader.googlecode.com/files/option.1.4.0.png)
> ![https://apk-downloader.googlecode.com/files/search.1.4.0.png](https://apk-downloader.googlecode.com/files/search.1.4.0.png)
> ![https://apk-downloader.googlecode.com/files/select.1.4.0.png](https://apk-downloader.googlecode.com/files/select.1.4.0.png)
> ![https://apk-downloader.googlecode.com/files/download.1.4.0.png](https://apk-downloader.googlecode.com/files/download.1.4.0.png)