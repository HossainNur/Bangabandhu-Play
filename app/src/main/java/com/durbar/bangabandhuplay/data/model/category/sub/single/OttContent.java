package com.durbar.bangabandhuplay.data.model.category.sub.single;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OttContent {

    @Nullable
    @SerializedName("id")
    @Expose
    public Integer id;
    @Nullable
    @SerializedName("uuid")
    @Expose
    public String uuid;
    @Nullable
    @SerializedName("title")
    @Expose
    public String title;
    @Nullable
    @SerializedName("short_title")
    @Expose
    public String shortTitle;
    @Nullable
    @SerializedName("access")
    @Expose
    public String access;
    @Nullable
    @SerializedName("youtube_url")
    @Expose
    public Object youtubeUrl;
    @Nullable
    @SerializedName("cloud_url")
    @Expose
    public Object cloudUrl;
    @Nullable
    @SerializedName("poster")
    @Expose
    public String poster;
    @Nullable
    @SerializedName("view_count")
    @Expose
    public Integer viewCount;
    @Nullable
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @Nullable
    @SerializedName("status")
    @Expose
    public String status;
    @Nullable
    @SerializedName("order")
    @Expose
    public Integer order;
    @Nullable
    @SerializedName("content_source")
    @Expose
    public List<ContentSource> contentSource;

    public OttContent(Integer id, String uuid, String title, String shortTitle, String access, Object youtubeUrl, Object cloudUrl, String poster, Integer viewCount, String releaseDate, String status, Integer order, List<ContentSource> contentSource) {
        this.id = id;
        this.uuid = uuid;
        this.title = title;
        this.shortTitle = shortTitle;
        this.access = access;
        this.youtubeUrl = youtubeUrl;
        this.cloudUrl = cloudUrl;
        this.poster = poster;
        this.viewCount = viewCount;
        this.releaseDate = releaseDate;
        this.status = status;
        this.order = order;
        this.contentSource = contentSource;
    }

    public Integer getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getAccess() {
        return access;
    }

    public Object getYoutubeUrl() {
        return youtubeUrl;
    }

    public Object getCloudUrl() {
        return cloudUrl;
    }

    public String getPoster() {
        return poster;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public Integer getOrder() {
        return order;
    }

    public List<ContentSource> getContentSource() {
        return contentSource;
    }
}
