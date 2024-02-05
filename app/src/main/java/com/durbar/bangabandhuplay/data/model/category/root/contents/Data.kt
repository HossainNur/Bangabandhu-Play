package com.durbar.bangabandhuplay.data.model.category.root.contents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
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
    @SerializedName("youtube_url")
    @Expose
    private String youtubeUrl;
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

    public Data(
            Integer id, String uuid, String title, String shortTitle,
            String youtubeUrl, Object cloudUrl, String poster, Integer viewCount,
            String releaseDate, String status, Integer order
    ){
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public void setCloudUrl(Object cloudUrl) {
        this.cloudUrl = cloudUrl;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
