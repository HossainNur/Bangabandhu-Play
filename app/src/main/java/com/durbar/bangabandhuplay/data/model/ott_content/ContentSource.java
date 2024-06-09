package com.durbar.bangabandhuplay.data.model.ott_content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentSource {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("ott_content_id")
    @Expose
    public Integer ottContentId;
    @SerializedName("content_source")
    @Expose
    public String contentSource;
    @SerializedName("fps")
    @Expose
    public Object fps;
    @SerializedName("source_format")
    @Expose
    public Object sourceFormat;
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
