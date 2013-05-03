package com.gc.android.market.api.model.builder;

public interface SubCategoriesResponseOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // repeated .Category category = 1;
    java.util.List<com.gc.android.market.api.model.Market.Category> getCategoryList();

    com.gc.android.market.api.model.Market.Category getCategory(int index);

    int getCategoryCount();

    java.util.List<? extends com.gc.android.market.api.model.builder.CategoryOrBuilder> getCategoryOrBuilderList();

    com.gc.android.market.api.model.builder.CategoryOrBuilder getCategoryOrBuilder(int index);

    // optional string subCategoryDisplay = 2;
    boolean hasSubCategoryDisplay();

    String getSubCategoryDisplay();

    // optional int32 subCategoryId = 3;
    boolean hasSubCategoryId();

    int getSubCategoryId();
}