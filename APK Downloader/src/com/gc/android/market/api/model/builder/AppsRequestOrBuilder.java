package com.gc.android.market.api.model.builder;

public interface AppsRequestOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional .AppType appType = 1;
    boolean hasAppType();

    com.gc.android.market.api.model.Market.AppType getAppType();

    // optional string query = 2;
    boolean hasQuery();

    String getQuery();

    // optional string categoryId = 3;
    boolean hasCategoryId();

    String getCategoryId();

    // optional string appId = 4;
    boolean hasAppId();

    String getAppId();

    // optional bool withExtendedInfo = 6;
    boolean hasWithExtendedInfo();

    boolean getWithExtendedInfo();

    // optional .AppsRequest.OrderType orderType = 7 [default = NONE];
    boolean hasOrderType();

    com.gc.android.market.api.model.Market.AppsRequest.OrderType getOrderType();

    // optional uint64 startIndex = 8;
    boolean hasStartIndex();

    long getStartIndex();

    // optional int32 entriesCount = 9;
    boolean hasEntriesCount();

    int getEntriesCount();

    // optional .AppsRequest.ViewType viewType = 10 [default = ALL];
    boolean hasViewType();

    com.gc.android.market.api.model.Market.AppsRequest.ViewType getViewType();
}