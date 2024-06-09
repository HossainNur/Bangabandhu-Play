package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OttContent {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("uuid")
    @Expose
    public String uuid;
    @SerializedName("poster")
    @Expose
    public Object poster;
    @SerializedName("access")
    @Expose
    public String access;
    @SerializedName("runtime")
    @Expose
    public Object runtime;
    @SerializedName("thumbnail_portrait")
    @Expose
    public String thumbnailPortrait;
    @SerializedName("thumbnail_landscape")
    @Expose
    public String thumbnailLandscape;

    public OttContent(Integer id, String title, String uuid, Object poster, String access, Object runtime, String thumbnailPortrait, String thumbnailLandscape) {
        this.id = id;
        this.title = title;
        this.uuid = uuid;
        this.poster = poster;
        this.access = access;
        this.runtime = runtime;
        this.thumbnailPortrait = thumbnailPortrait;
        this.thumbnailLandscape = thumbnailLandscape;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUuid() {
        return uuid;
    }

    public Object getPoster() {
        return poster;
    }

    public String getAccess() {
        return access;
    }

    public Object getRuntime() {
        return runtime;
    }

    public String getThumbnailPortrait() {
        return thumbnailPortrait;
    }

    public String getThumbnailLandscape() {
        return thumbnailLandscape;
    }
}
