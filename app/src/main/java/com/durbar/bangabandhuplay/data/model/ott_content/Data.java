package com.durbar.bangabandhuplay.data.model.ott_content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("content_data")
    @Expose
    public ContentData contentData;
    @SerializedName("subscription")
    @Expose
    public Boolean subscription;
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
