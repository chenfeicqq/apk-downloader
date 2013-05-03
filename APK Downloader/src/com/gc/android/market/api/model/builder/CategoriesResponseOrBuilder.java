package com.gc.android.market.api.model.builder;

public interface CategoriesResponseOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // repeated .Category categories = 1;
    java.util.List<com.gc.android.market.api.model.Market.Category> getCategoriesList();

    com.gc.android.market.api.model.Market.Category getCategories(int index);

    int getCategoriesCount();

    java.util.List<? extends com.gc.android.market.api.model.builder.CategoryOrBuilder> getCategoriesOrBuilderList();

    com.gc.android.market.api.model.builder.CategoryOrBuilder getCategoriesOrBuilder(int index);
}