#!/bin/bash
export APP_HOME=$PWD
## jre是Windows的，暂时不能使用
export JRE_HOME="$APP_HOME/jre"
export CLASSPATH="$APP_HOME/lib/protobuf-java-2.4.1.jar"
export CLASSPATH="$CLASSPATH:$APP_HOME/lib/beautyeye_lnf.jar"
export CLASSPATH="$CLASSPATH:$APP_HOME/lib/log4j-1.2.17.jar"
export CLASSPATH="$CLASSPATH:$APP_HOME/bin/APK Downloader.jar"

nohup "$JRE_HOME/bin/java" -cp "$CLASSPATH" com.chenfei.android.apk.downloader.APKDownloader > "$APP_HOME/logs/console.log" &