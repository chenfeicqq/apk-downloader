package com.gc.android.market.api.model.builder;

public interface InstallAssetOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional string assetId = 2;
    boolean hasAssetId();

    String getAssetId();

    // optional string assetName = 3;
    boolean hasAssetName();

    String getAssetName();

    // optional string assetType = 4;
    boolean hasAssetType();

    String getAssetType();

    // optional string assetPackage = 5;
    boolean hasAssetPackage();

    String getAssetPackage();

    // optional string blobUrl = 6;
    boolean hasBlobUrl();

    String getBlobUrl();

    // optional string assetSignature = 7;
    boolean hasAssetSignature();

    String getAssetSignature();

    // optional uint64 assetSize = 8;
    boolean hasAssetSize();

    long getAssetSize();

    // optional uint64 refundTimeout = 9;
    boolean hasRefundTimeout();

    long getRefundTimeout();

    // optional bool forwardLocked = 10;
    boolean hasForwardLocked();

    boolean getForwardLocked();

    // optional bool secured = 11;
    boolean hasSecured();

    boolean getSecured();

    // optional int32 versionCode = 12;
    boolean hasVersionCode();

    int getVersionCode();

    // optional string downloadAuthCookieName = 13;
    boolean hasDownloadAuthCookieName();

    String getDownloadAuthCookieName();

    // optional string downloadAuthCookieValue = 14;
    boolean hasDownloadAuthCookieValue();

    String getDownloadAuthCookieValue();
}