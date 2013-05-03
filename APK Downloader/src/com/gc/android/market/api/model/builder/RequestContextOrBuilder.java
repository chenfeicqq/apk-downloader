package com.gc.android.market.api.model.builder;

public interface RequestContextOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // required string authSubToken = 1;
    boolean hasAuthSubToken();

    String getAuthSubToken();

    // required bool isSecure = 2;
    boolean hasIsSecure();

    boolean getIsSecure();

    // required int32 version = 3;
    boolean hasVersion();

    int getVersion();

    // required string androidId = 4;
    boolean hasAndroidId();

    String getAndroidId();

    // optional string deviceAndSdkVersion = 5;
    boolean hasDeviceAndSdkVersion();

    String getDeviceAndSdkVersion();

    // optional string userLanguage = 6;
    boolean hasUserLanguage();

    String getUserLanguage();

    // optional string userCountry = 7;
    boolean hasUserCountry();

    String getUserCountry();

    // optional string operatorAlpha = 8;
    boolean hasOperatorAlpha();

    String getOperatorAlpha();

    // optional string simOperatorAlpha = 9;
    boolean hasSimOperatorAlpha();

    String getSimOperatorAlpha();

    // optional string operatorNumeric = 10;
    boolean hasOperatorNumeric();

    String getOperatorNumeric();

    // optional string simOperatorNumeric = 11;
    boolean hasSimOperatorNumeric();

    String getSimOperatorNumeric();
}