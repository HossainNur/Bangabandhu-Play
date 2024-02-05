package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("content_type_slug")
    @Expose
    public Integer contentTypeSlug;
    @SerializedName("content_type_title")
    @Expose
    public String contentTypeTitle;
    @SerializedName("more_info_slug")
    @Expose
    public String moreInfoSlug;
    @SerializedName("is_available_on_single_page")
    @Expose
    public Integer isAvailableOnSinglePage;
    @SerializedName("is_featured_section")
    @Expose
    public Integer isFeaturedSection;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("frontend_custom_content_limited_data")
    @Expose
    private final List<FrontendCustomContent> frontendCustomContent;

    public Data(Integer id, Integer contentTypeSlug, String contentTypeTitle, String moreInfoSlug, Integer isAvailableOnSinglePage, Integer isFeaturedSection, String createdAt, String updatedAt, List<FrontendCustomContent> frontendCustomContent) {
        this.id = id;
        this.contentTypeSlug = contentTypeSlug;
        this.contentTypeTitle = contentTypeTitle;
        this.moreInfoSlug = moreInfoSlug;
        this.isAvailableOnSinglePage = isAvailableOnSinglePage;
        this.isFeaturedSection = isFeaturedSection;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.frontendCustomContent = frontendCustomContent;
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

    public Object getCreatedAt() {
        return createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public Integer getIsAvailableOnSinglePage() {
        return isAvailableOnSinglePage;
    }

    public Integer getIsFeaturedSection() {
        return isFeaturedSection;
    }

    public List<FrontendCustomContent> getFrontendCustomContent() {
        return frontendCustomContent;
    }


    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", contentTypeSlug=" + contentTypeSlug +
                ", contentTypeTitle='" + contentTypeTitle + '\'' +
                ", moreInfoSlug='" + moreInfoSlug + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", frontendCustomContent=" + frontendCustomContent +
                '}';
    }
}
