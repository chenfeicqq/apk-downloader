package com.gc.android.market.api.model.builder;

public interface GetAssetRequestOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // required string assetId = 1;
    boolean hasAssetId();

    String getAssetId();

    // optional string directDownloadKey = 2;
    boolean hasDirectDownloadKey();

    String getDirectDownloadKey();
}