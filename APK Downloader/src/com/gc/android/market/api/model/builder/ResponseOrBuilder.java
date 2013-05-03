package com.gc.android.market.api.model.builder;

import java.util.List;

import com.gc.android.market.api.model.Market.Response.ResponseGroup;

public interface ResponseOrBuilder extends com.google.protobuf.MessageOrBuilder
{
    // repeated group ResponseGroup = 1 {
    List<ResponseGroup> getResponseGroupList();

    ResponseGroup getResponseGroup(int index);

    int getResponseGroupCount();

    List<? extends ResponseGroupOrBuilder> getResponseGroupOrBuilderList();

    ResponseGroupOrBuilder getResponseGroupOrBuilder(int index);
}