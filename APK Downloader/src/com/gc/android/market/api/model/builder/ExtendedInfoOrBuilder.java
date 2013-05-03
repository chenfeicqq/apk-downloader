package com.gc.android.market.api.model.builder;

public interface ExtendedInfoOrBuilder extends com.google.protobuf.MessageOrBuilder
{

    // optional string description = 13;
    boolean hasDescription();

    String getDescription();

    // optional int32 downloadsCount = 14;
    boolean hasDownloadsCount();

    int getDownloadsCount();

    // repeated string permissionId = 15;
    java.util.List<String> getPermissionIdList();

    int getPermissionIdCount();

    String getPermissionId(int index);

    // optional int32 installSize = 16;
    boolean hasInstallSize();

    int getInstallSize();

    // optional string packageName = 17;
    boolean hasPackageName();

    String getPackageName();

    // optional string category = 18;
    boolean hasCategory();

    String getCategory();

    // optional string contactEmail = 20;
    boolean hasContactEmail();

    String getContactEmail();

    // optional string downloadsCountText = 23;
    boolean hasDownloadsCountText();

    String getDownloadsCountText();

    // optional string contactPhone = 26;
    boolean hasContactPhone();

    String getContactPhone();

    // optional string contactWebsite = 27;
    boolean hasContactWebsite();

    String getContactWebsite();

    // optional int32 screenshotsCount = 30;
    boolean hasScreenshotsCount();

    int getScreenshotsCount();

    // optional string promoText = 31;
    boolean hasPromoText();

    String getPromoText();

    // optional string recentChanges = 38;
    boolean hasRecentChanges();

    String getRecentChanges();

    // optional string promotionalVideo = 43;
    boolean hasPromotionalVideo();

    String getPromotionalVideo();
}