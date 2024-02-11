package com.durbar.bangabandhuplay.data.model.sliders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OttContent {
    @SerializedName("uuid")
    @Expose
    public String uuid;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("runtime")
    @Expose
    public Object runtime;
    @SerializedName("imdb")
    @Expose
    public Object imdb;
    @SerializedName("release_date")
    @Expose
    public String releaseDate;

    public OttContent(String uuid, String title, Object runtime, Object imdb, String releaseDate) {
        this.uuid = uuid;
        this.title = title;
        this.runtime = runtime;
        this.imdb = imdb;
        this.releaseDate = releaseDate;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public Object getRuntime() {
        return runtime;
    }

    public Object getImdb() {
        return imdb;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "OttContent{" +
                "uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", runtime=" + runtime +
                ", imdb=" + imdb +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
