package com.durbar.bangabandhuplay.data.model.get_related_contents;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentSource {
    @Nullable
    @SerializedName("key")
    @Expose
    public Object key;
    @Nullable
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
