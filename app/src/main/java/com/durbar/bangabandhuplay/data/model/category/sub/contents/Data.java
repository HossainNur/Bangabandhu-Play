package com.durbar.bangabandhuplay.data.model.category.sub.contents;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @Nullable
    @SerializedName("id")
    @Expose
    private Integer id;
    @Nullable
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @Nullable
    @SerializedName("title")
    @Expose
    private String title;
    @Nullable
    @SerializedName("short_title")
    @Expose
    private String shortTitle;
    @Nullable
    @SerializedName("youtube_url")
    @Expose
    private String youtubeUrl;
    @Nullable
    @SerializedName("cloud_url")
    @Expose
    private Object cloudUrl;
    @Nullable
    @SerializedName("poster")
    @Expose
    private String poster;
    @Nullable
    @SerializedName("view_count")
    @Expose
    private Integer viewCount;
    @Nullable
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @Nullable
    @SerializedName("status")
    @Expose
    private String status;
    @Nullable
    @SerializedName("order")
    @Expose
    private Integer order;

    public Data
            (
                    Integer id,
                    String uuid,
                    String title,
                    String shortTitle,
                    String youtubeUrl,
                    Object cloudUrl,
                    String poster,
                    Integer viewCount,
                    String releaseDate,
                    String status,
                    Integer order
            ) {
        this.id = id;
        this.uuid = uuid;
        this.title = title;
        this.shortTitle = shortTitle;
        this.youtubeUrl = youtubeUrl;
        this.cloudUrl = cloudUrl;
        this.poster = poster;
        this.viewCount = viewCount;
        this.releaseDate = releaseDate;
        this.status = status;
        this.order = order;
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

    public String getYoutubeUrl() {
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
}
