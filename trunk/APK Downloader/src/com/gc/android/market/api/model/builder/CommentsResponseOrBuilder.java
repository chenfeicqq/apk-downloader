package com.gc.android.market.api.model.builder;

public interface CommentsResponseOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // repeated .Comment comments = 1;
    java.util.List<com.gc.android.market.api.model.Market.Comment> getCommentsList();

    com.gc.android.market.api.model.Market.Comment getComments(int index);

    int getCommentsCount();

    java.util.List<? extends com.gc.android.market.api.model.builder.CommentOrBuilder> getCommentsOrBuilderList();

    com.gc.android.market.api.model.builder.CommentOrBuilder getCommentsOrBuilder(int index);

    // optional int32 entriesCount = 2;
    boolean hasEntriesCount();

    int getEntriesCount();
}