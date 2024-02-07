package com.durbar.bangabandhuplay.data.model.live;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @Nullable
    @SerializedName("id")
    @Expose
    public Integer id;
    @Nullable
    @SerializedName("app_id")
    @Expose
    public String appId;
    @Nullable
    @SerializedName("title")
    @Expose
    public String title;
    @Nullable
    @SerializedName("description")
    @Expose
    public String description;
    @Nullable
    @SerializedName("channel_name")
    @Expose
    public String channelName;
    @Nullable
    @SerializedName("token")
    @Expose
    public String token;
    @Nullable
    @SerializedName("status")
    @Expose
    public Integer status;
    @Nullable
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @Nullable
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
