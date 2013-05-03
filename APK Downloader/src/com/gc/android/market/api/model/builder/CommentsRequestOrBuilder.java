package com.gc.android.market.api.model.builder;

public interface CommentsRequestOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional string appId = 1;
    boolean hasAppId();

    String getAppId();

    // optional int32 startIndex = 2;
    boolean hasStartIndex();

    int getStartIndex();

    // optional int32 entriesCount = 3;
    boolean hasEntriesCount();

    int getEntriesCount();
}