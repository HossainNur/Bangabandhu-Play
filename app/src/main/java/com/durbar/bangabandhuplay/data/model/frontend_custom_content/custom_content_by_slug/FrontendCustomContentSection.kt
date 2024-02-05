package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FrontendCustomContentSection {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content_type_slug")
    @Expose
    private Integer contentTypeSlug;
    @SerializedName("content_type_title")
    @Expose
    private String contentTypeTitle;
    @SerializedName("more_info_slug")
    @Expose
    private String moreInfoSlug;

    public FrontendCustomContentSection(Integer id, Integer contentTypeSlug, String contentTypeTitle, String moreInfoSlug) {
        this.id = id;
        this.contentTypeSlug = contentTypeSlug;
        this.contentTypeTitle = contentTypeTitle;
        this.moreInfoSlug = moreInfoSlug;
    }

    public Integer getId() {
        return id;
    }

    public Integer getContentTypeSlug() {
        return contentTypeSlug;
    }

    public String getContentTypeTitle() {
        return contentTypeTitle;
    }

    public String getMoreInfoSlug() {
        return moreInfoSlug;
    }
}
