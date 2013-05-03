package com.gc.android.market.api.model.builder;

public interface AppsResponseOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // repeated .App app = 1;
    java.util.List<com.gc.android.market.api.model.Market.App> getAppList();

    com.gc.android.market.api.model.Market.App getApp(int index);

    int getAppCount();

    java.util.List<? extends com.gc.android.market.api.model.builder.AppOrBuilder> getAppOrBuilderList();

    com.gc.android.market.api.model.builder.AppOrBuilder getAppOrBuilder(int index);

    // optional int32 entriesCount = 2;
    boolean hasEntriesCount();

    int getEntriesCount();
}