package com.durbar.bangabandhuplay.data.model.get_related_contents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FixedRelatedContent {
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
    public List<FrontendCustomContentLimitedData> frontendCustomContentLimitedData;

    public FixedRelatedContent(Integer id, Integer contentTypeSlug, String contentTypeTitle, String moreInfoSlug, Integer isAvailableOnSinglePage, Integer isFeaturedSection, String createdAt, String updatedAt, List<FrontendCustomContentLimitedData> frontendCustomContentLimitedData) {
        this.id = id;
        this.contentTypeSlug = contentTypeSlug;
        this.contentTypeTitle = contentTypeTitle;
        this.moreInfoSlug = moreInfoSlug;
        this.isAvailableOnSinglePage = isAvailableOnSinglePage;
        this.isFeaturedSection = isFeaturedSection;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.frontendCustomContentLimitedData = frontendCustomContentLimitedData;
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

    public Integer getIsAvailableOnSinglePage() {
        return isAvailableOnSinglePage;
    }

    public Integer getIsFeaturedSection() {
        return isFeaturedSection;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public List<FrontendCustomContentLimitedData> getFrontendCustomContentLimitedData() {
        return frontendCustomContentLimitedData;
    }
}
