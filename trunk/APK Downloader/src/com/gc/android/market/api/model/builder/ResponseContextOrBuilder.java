package com.gc.android.market.api.model.builder;

public interface ResponseContextOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional .ResponseContext.ResultType result = 1;
    boolean hasResult();

    com.gc.android.market.api.model.Market.ResponseContext.ResultType getResult();

    // optional int32 maxAge = 2;
    boolean hasMaxAge();

    int getMaxAge();

    // optional string etag = 3;
    boolean hasEtag();

    String getEtag();

    // optional int32 serverVersion = 4;
    boolean hasServerVersion();

    int getServerVersion();
}