package com.example.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OttContent {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("access")
    @Expose
    private String access;

    public OttContent(Integer id, String title, String uuid, String poster, String access) {
        this.id = id;
        this.title = title;
        this.uuid = uuid;
        this.poster = poster;
        this.access = access;
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

    public String getPoster() {
        return poster;
    }

    public String getAccess() {
        return access;
    }
}
