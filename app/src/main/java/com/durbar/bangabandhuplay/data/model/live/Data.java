package com.durbar.bangabandhuplay.data.model.live;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("app_id")
    @Expose
    public String appId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("channel_name")
    @Expose
    public String channelName;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    public Data(Integer id, String appId, String title, String description, String channelName, String token, Integer status, String createdAt, String updatedAt) {
        this.id = id;
        this.appId = appId;
        this.title = title;
        this.description = description;
        this.channelName = channelName;
        this.token = token;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public String getAppId() {
        return appId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getToken() {
        return token;
    }

    public Integer getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", appId='" + appId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", channelName='" + channelName + '\'' +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
