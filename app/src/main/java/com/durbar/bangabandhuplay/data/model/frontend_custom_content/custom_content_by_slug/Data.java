package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @Nullable
    @SerializedName("title")
    @Expose
    public String title;
    @Nullable
    @SerializedName("contents")
    @Expose
    public List<Content> contents;

    public Data(String title, List<Content> contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public List<Content> getContents() {
        return contents;
    }
}
