package com.durbar.bangabandhuplay.data.model.get_related_contents;

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

    public OttContent(Integer id, String title, String uuid, Object poster, String access) {
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

    public Object getPoster() {
        return poster;
    }

    public String getAccess() {
        return access;
    }
}
