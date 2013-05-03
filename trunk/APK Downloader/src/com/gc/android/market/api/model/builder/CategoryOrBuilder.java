package com.gc.android.market.api.model.builder;

public interface CategoryOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional int32 appType = 2;
    boolean hasAppType();

    int getAppType();

    // optional string title = 4;
    boolean hasTitle();

    String getTitle();

    // optional string categoryId = 3;
    boolean hasCategoryId();

    String getCategoryId();

    // optional string subtitle = 5;
    boolean hasSubtitle();

    String getSubtitle();

    // repeated .Category subCategories = 8;
    java.util.List<com.gc.android.market.api.model.Market.Category> getSubCategoriesList();

    com.gc.android.market.api.model.Market.Category getSubCategories(int index);

    int getSubCategoriesCount();

    java.util.List<? extends com.gc.android.market.api.model.builder.CategoryOrBuilder> getSubCategoriesOrBuilderList();

    com.gc.android.market.api.model.builder.CategoryOrBuilder getSubCategoriesOrBuilder(int index);
}