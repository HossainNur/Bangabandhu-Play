package com.durbar.bangabandhuplay.data.model.category.root.single;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentSource {

    @Nullable
    @SerializedName("key")
    @Expose
    private Object key;
    @Nullable
    @SerializedName("value")
    @Expose
    private Object value;

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
