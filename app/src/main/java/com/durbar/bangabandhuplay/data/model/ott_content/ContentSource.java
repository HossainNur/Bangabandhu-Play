package com.durbar.bangabandhuplay.data.model.ott_content;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentSource {
    @Nullable
    @SerializedName("id")
    @Expose
    public Integer id;
    @Nullable
    @SerializedName("ott_content_id")
    @Expose
    public Integer ottContentId;
    @Nullable
    @SerializedName("content_source")
    @Expose
    public String contentSource;
    @Nullable
    @SerializedName("fps")
    @Expose
    public Object fps;
    @Nullable
    @SerializedName("source_format")
    @Expose
    public Object sourceFormat;
    @Nullable
    @SerializedName("source_type")
    @Expose
    public String sourceType;

    public ContentSource(Integer id, Integer ottContentId, String contentSource, Object fps, Object sourceFormat, String sourceType) {
        this.id = id;
        this.ottContentId = ottContentId;
        this.contentSource = contentSource;
        this.fps = fps;
        this.sourceFormat = sourceFormat;
        this.sourceType = sourceType;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOttContentId() {
        return ottContentId;
    }

    public String getContentSource() {
        return contentSource;
    }

    public Object getFps() {
        return fps;
    }

    public Object getSourceFormat() {
        return sourceFormat;
    }

    public String getSourceType() {
        return sourceType;
    }
}
