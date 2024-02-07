package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OttContent {
    @Nullable
    @SerializedName("id")
    @Expose
    public Integer id;
    @Nullable
    @SerializedName("title")
    @Expose
    public String title;
    @Nullable
    @SerializedName("uuid")
    @Expose
    public String uuid;
    @Nullable
    @SerializedName("poster")
    @Expose
    public Object poster;
    @Nullable
    @SerializedName("access")
    @Expose
    public String access;
    @Nullable
    @SerializedName("thumbnail_portrait")
    @Expose
    public String thumbnailPortrait;
    @Nullable
    @SerializedName("thumbnail_landscape")
    @Expose
    public String thumbnailLandscape;

    public OttContent(Integer id, String title, String uuid, Object poster, String access, String thumbnailPortrait, String thumbnailLandscape) {
        this.id = id;
        this.title = title;
        this.uuid = uuid;
        this.poster = poster;
        this.access = access;
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

    public String getThumbnailPortrait() {
        return thumbnailPortrait;
    }

    public String getThumbnailLandscape() {
        return thumbnailLandscape;
    }
}
