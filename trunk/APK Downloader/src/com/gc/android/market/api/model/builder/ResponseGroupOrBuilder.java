package com.gc.android.market.api.model.builder;

import com.gc.android.market.api.model.Market.AppsResponse;
import com.gc.android.market.api.model.Market.CategoriesResponse;
import com.gc.android.market.api.model.Market.CommentsResponse;
import com.gc.android.market.api.model.Market.GetAssetResponse;
import com.gc.android.market.api.model.Market.GetImageResponse;
import com.gc.android.market.api.model.Market.ResponseContext;
import com.gc.android.market.api.model.Market.SubCategoriesResponse;

public interface ResponseGroupOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional .ResponseContext context = 2;
    boolean hasContext();

    ResponseContext getContext();

    ResponseContextOrBuilder getContextOrBuilder();

    // optional .AppsResponse appsResponse = 3;
    boolean hasAppsResponse();

    AppsResponse getAppsResponse();

    AppsResponseOrBuilder getAppsResponseOrBuilder();

    // optional .CommentsResponse commentsResponse = 4;
    boolean hasCommentsResponse();

    CommentsResponse getCommentsResponse();

    CommentsResponseOrBuilder getCommentsResponseOrBuilder();

    // optional .GetAssetResponse getAssetResponse = 9;
    boolean hasGetAssetResponse();

    GetAssetResponse getGetAssetResponse();

    GetAssetResponseOrBuilder getGetAssetResponseOrBuilder();

    // optional .GetImageResponse imageResponse = 10;
    boolean hasImageResponse();

    GetImageResponse getImageResponse();

    GetImageResponseOrBuilder getImageResponseOrBuilder();

    // optional .CategoriesResponse categoriesResponse = 20;
    boolean hasCategoriesResponse();

    CategoriesResponse getCategoriesResponse();

    CategoriesResponseOrBuilder getCategoriesResponseOrBuilder();

    // optional .SubCategoriesResponse subCategoriesResponse = 13;
    boolean hasSubCategoriesResponse();

    SubCategoriesResponse getSubCategoriesResponse();

    SubCategoriesResponseOrBuilder getSubCategoriesResponseOrBuilder();
}