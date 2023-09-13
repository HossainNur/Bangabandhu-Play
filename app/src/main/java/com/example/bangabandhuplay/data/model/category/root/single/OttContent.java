package com.example.bangabandhuplay.data.model.category.root.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OttContent {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_title")
    @Expose
    private String shortTitle;
    @SerializedName("access")
    @Expose
    private String access;
    @SerializedName("youtube_url")
    @Expose
    private Object youtubeUrl;
    @SerializedName("cloud_url")
    @Expose
    private Object cloudUrl;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("view_count")
    @Expose
    private Integer viewCount;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("content_source")
    @Expose
    private List<ContentSource> contentSource ;

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
