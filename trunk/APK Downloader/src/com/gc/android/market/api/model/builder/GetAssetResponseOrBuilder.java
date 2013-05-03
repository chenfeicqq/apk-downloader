package com.gc.android.market.api.model.builder;

public interface GetAssetResponseOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // repeated group InstallAsset = 1 {
    java.util.List<com.gc.android.market.api.model.Market.GetAssetResponse.InstallAsset> getInstallAssetList();

    com.gc.android.market.api.model.Market.GetAssetResponse.InstallAsset getInstallAsset(int index);

    int getInstallAssetCount();

    java.util.List<? extends com.gc.android.market.api.model.builder.InstallAssetOrBuilder> getInstallAssetOrBuilderList();

    com.gc.android.market.api.model.builder.InstallAssetOrBuilder getInstallAssetOrBuilder(int index);
}