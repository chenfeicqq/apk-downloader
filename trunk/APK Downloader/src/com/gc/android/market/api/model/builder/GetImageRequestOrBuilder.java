package com.gc.android.market.api.model.builder;

public interface GetImageRequestOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional string appId = 1;
    boolean hasAppId();

    String getAppId();

    // optional .GetImageRequest.AppImageUsage imageUsage = 3;
    boolean hasImageUsage();

    com.gc.android.market.api.model.Market.GetImageRequest.AppImageUsage getImageUsage();

    // optional string imageId = 4;
    boolean hasImageId();

    String getImageId();
}