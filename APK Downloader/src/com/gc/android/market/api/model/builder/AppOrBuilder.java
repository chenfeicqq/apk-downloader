package com.gc.android.market.api.model.builder;

public interface AppOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional string id = 1;
    boolean hasId();

    String getId();

    // optional string title = 2;
    boolean hasTitle();

    String getTitle();

    // optional .AppType appType = 3 [default = NONE];
    boolean hasAppType();

    com.gc.android.market.api.model.Market.AppType getAppType();

    // optional string creator = 4;
    boolean hasCreator();

    String getCreator();

    // optional string version = 5;
    boolean hasVersion();

    String getVersion();

    // optional string price = 6;
    boolean hasPrice();

    String getPrice();

    // optional string rating = 7;
    boolean hasRating();

    String getRating();

    // optional int32 ratingsCount = 8;
    boolean hasRatingsCount();

    int getRatingsCount();

    // optional group ExtendedInfo = 12 {
    boolean hasExtendedInfo();

    com.gc.android.market.api.model.Market.App.ExtendedInfo getExtendedInfo();

    com.gc.android.market.api.model.builder.ExtendedInfoOrBuilder getExtendedInfoOrBuilder();

    // optional string creatorId = 22;
    boolean hasCreatorId();

    String getCreatorId();

    // optional string packageName = 24;
    boolean hasPackageName();

    String getPackageName();

    // optional int32 versionCode = 25;
    boolean hasVersionCode();

    int getVersionCode();

    // optional string priceCurrency = 32;
    boolean hasPriceCurrency();

    String getPriceCurrency();

    // optional int32 priceMicros = 33;
    boolean hasPriceMicros();

    int getPriceMicros();
}