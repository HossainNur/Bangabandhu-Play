package com.durbar.bangabandhuplay.data.model.ott_content;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @Nullable
    @SerializedName("content_data")
    @Expose
    public ContentData contentData;
    @Nullable
    @SerializedName("subscription")
    @Expose
    public Boolean subscription;
    @Nullable
    @SerializedName("device_stream_limit_exceeded")
    @Expose
    public Boolean deviceStreamLimitExceeded;

    public Data(ContentData contentData, Boolean subscription, Boolean deviceStreamLimitExceeded) {
        this.contentData = contentData;
        this.subscription = subscription;
        this.deviceStreamLimitExceeded = deviceStreamLimitExceeded;
    }

    public ContentData getContentData() {
        return contentData;
    }

    public Boolean getSubscription() {
        return subscription;
    }

    public Boolean getDeviceStreamLimitExceeded() {
        return deviceStreamLimitExceeded;
    }
}
