package com.durbar.bangabandhuplay.data.model.get_related_contents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleContentRelatedContent {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("uuid")
    @Expose
    public String uuid;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("short_title")
    @Expose
    public Object shortTitle;
    @SerializedName("access")
    @Expose
    public String access;
    @SerializedName("youtube_url")
    @Expose
    public Object youtubeUrl;
    @SerializedName("cloud_url")
    @Expose
    public Object cloudUrl;
    @SerializedName("poster")
    @Expose
    public Object poster;
    @SerializedName("view_count")
    @Expose
    public Object viewCount;
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("order")
    @Expose
    public Integer order;
    @SerializedName("runtime")
    @Expose
    public Object runtime;
    @SerializedName("content_source")
    @Expose
    public List<ContentSource> contentSource;
    @SerializedName("average_review_count")
    @Expose
    public Integer averageReviewCount;
    @SerializedName("thumbnail_portrait")
    @Expose
    public String thumbnailPortrait;
    @SerializedName("thumbnail_landscape")
    @Expose
    public String thumbnailLandscape;

    public SingleContentRelatedContent(Integer id, String uuid, String title, Object shortTitle, String access, Object youtubeUrl, Object cloudUrl, Object poster, Object viewCount, String releaseDate, String status, Integer order, Object runtime, List<ContentSource> contentSource, Integer averageReviewCount, String thumbnailPortrait, String thumbnailLandscape) {
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
        this.runtime = runtime;
        this.contentSource = contentSource;
        this.averageReviewCount = averageReviewCount;
        this.thumbnailPortrait = thumbnailPortrait;
        this.thumbnailLandscape = thumbnailLandscape;
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

    public Object getShortTitle() {
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

    public Object getPoster() {
        return poster;
    }

    public Object getViewCount() {
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

    public Object getRuntime() {
        return runtime;
    }

    public List<ContentSource> getContentSource() {
        return contentSource;
    }

    public Integer getAverageReviewCount() {
        return averageReviewCount;
    }

    public String getThumbnailPortrait() {
        return thumbnailPortrait;
    }

    public String getThumbnailLandscape() {
        return thumbnailLandscape;
    }
}
