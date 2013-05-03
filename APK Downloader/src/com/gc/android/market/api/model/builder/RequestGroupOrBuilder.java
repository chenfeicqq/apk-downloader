package com.gc.android.market.api.model.builder;

public interface RequestGroupOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional .AppsRequest appsRequest = 4;
    boolean hasAppsRequest();

    com.gc.android.market.api.model.Market.AppsRequest getAppsRequest();

    com.gc.android.market.api.model.builder.AppsRequestOrBuilder getAppsRequestOrBuilder();

    // optional .CommentsRequest commentsRequest = 5;
    boolean hasCommentsRequest();

    com.gc.android.market.api.model.Market.CommentsRequest getCommentsRequest();

    com.gc.android.market.api.model.builder.CommentsRequestOrBuilder getCommentsRequestOrBuilder();

    // optional .GetAssetRequest getAssetRequest = 10;
    boolean hasGetAssetRequest();

    com.gc.android.market.api.model.Market.GetAssetRequest getGetAssetRequest();

    com.gc.android.market.api.model.builder.GetAssetRequestOrBuilder getGetAssetRequestOrBuilder();

    // optional .GetImageRequest imageRequest = 11;
    boolean hasImageRequest();

    com.gc.android.market.api.model.Market.GetImageRequest getImageRequest();

    com.gc.android.market.api.model.builder.GetImageRequestOrBuilder getImageRequestOrBuilder();

    // optional .SubCategoriesRequest subCategoriesRequest = 14;
    boolean hasSubCategoriesRequest();

    com.gc.android.market.api.model.Market.SubCategoriesRequest getSubCategoriesRequest();

    com.gc.android.market.api.model.builder.SubCategoriesRequestOrBuilder getSubCategoriesRequestOrBuilder();

    // optional .CategoriesRequest categoriesRequest = 21;
    boolean hasCategoriesRequest();

    com.gc.android.market.api.model.Market.CategoriesRequest getCategoriesRequest();

    com.gc.android.market.api.model.builder.CategoriesRequestOrBuilder getCategoriesRequestOrBuilder();
}