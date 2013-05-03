package com.gc.android.market.api.model.builder;

public interface RequestOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional .RequestContext context = 1;
    boolean hasContext();

    com.gc.android.market.api.model.Market.RequestContext getContext();

    com.gc.android.market.api.model.builder.RequestContextOrBuilder getContextOrBuilder();

    // repeated group RequestGroup = 2 {
    java.util.List<com.gc.android.market.api.model.Market.Request.RequestGroup> getRequestGroupList();

    com.gc.android.market.api.model.Market.Request.RequestGroup getRequestGroup(int index);

    int getRequestGroupCount();

    java.util.List<? extends com.gc.android.market.api.model.builder.RequestGroupOrBuilder> getRequestGroupOrBuilderList();

    com.gc.android.market.api.model.builder.RequestGroupOrBuilder getRequestGroupOrBuilder(int index);
}