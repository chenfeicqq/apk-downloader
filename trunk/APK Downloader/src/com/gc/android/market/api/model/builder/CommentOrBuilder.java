package com.gc.android.market.api.model.builder;

public interface CommentOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional string text = 1;
    boolean hasText();

    String getText();

    // optional int32 rating = 2;
    boolean hasRating();

    int getRating();

    // optional string authorName = 3;
    boolean hasAuthorName();

    String getAuthorName();

    // optional uint64 creationTime = 4;
    boolean hasCreationTime();

    long getCreationTime();

    // optional string authorId = 5;
    boolean hasAuthorId();

    String getAuthorId();
}