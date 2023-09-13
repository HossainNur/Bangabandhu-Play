package com.example.bangabandhuplay.data.model.category.root.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentSource {

    @SerializedName("key")
    @Expose
    private Object key;
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
