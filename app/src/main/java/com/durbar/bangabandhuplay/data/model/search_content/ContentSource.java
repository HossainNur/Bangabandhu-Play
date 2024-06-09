package com.durbar.bangabandhuplay.data.model.search_content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentSource {
    @SerializedName("key")
    @Expose
    public Object key;
    @SerializedName("value")
    @Expose
    public Object value;

    public ContentSource(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
