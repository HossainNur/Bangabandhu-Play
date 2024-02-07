package com.durbar.bangabandhuplay.data.model.category.single_sub;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OttContent {
    @Nullable
    @SerializedName("id")
    @Expose
    public Integer id;
    @Nullable
    @SerializedName("uuid")
    @Expose
    public String uuid;
    @Nullable
    @SerializedName("title")
    @Expose
    public String title;
    @Nullable
    @SerializedName("short_title")
    @Expose
    public Object shortTitle;
    @Nullable
    @SerializedName("root_category_id")
    @Expose
    public Integer rootCategoryId;
    @Nullable
    @SerializedName("sub_category_id")
    @Expose
    public Integer subCategoryId;
    @Nullable
    @SerializedName("sub_sub_category_id")
    @Expose
    public Object subSubCategoryId;
    @Nullable
    @SerializedName("series_id")
    @Expose
    public Object seriesId;
    @Nullable
    @SerializedName("content_type_id")
    @Expose
    public Object contentTypeId;
    @Nullable
    @SerializedName("description")
    @Expose
    public Object description;
    @Nullable
    @SerializedName("bangla_description")
    @Expose
    public Object banglaDescription;
    @Nullable
    @SerializedName("year")
    @Expose
    public Object year;
    @Nullable
    @SerializedName("runtime")
    @Expose
    public Object runtime;
    @Nullable
    @SerializedName("youtube_url")
    @Expose
    public Object youtubeUrl;
    @Nullable
    @SerializedName("cloud_url")
    @Expose
    public Object cloudUrl;
    @Nullable
    @SerializedName("intro_starts")
    @Expose
    public Object introStarts;
    @Nullable
    @SerializedName("intro_end")
    @Expose
    public Object introEnd;
    @Nullable
    @SerializedName("next_end")
    @Expose
    public Object nextEnd;
    @Nullable
    @SerializedName("poster")
    @Expose
    public Object poster;
    @Nullable
    @SerializedName("backdrop")
    @Expose
    public Object backdrop;
    @Nullable
    @SerializedName("thumbnail_portrait")
    @Expose
    public String thumbnailPortrait;
    @Nullable
    @SerializedName("thumbnail_landscape")
    @Expose
    public String thumbnailLandscape;
    @Nullable
    @SerializedName("tv_cover")
    @Expose
    public Object tvCover;
    @Nullable
    @SerializedName("view_count")
    @Expose
    public Object viewCount;
    @Nullable
    @SerializedName("release_date")
    @Expose
    public Object releaseDate;
    @Nullable
    @SerializedName("status")
    @Expose
    public String status;
    @Nullable
    @SerializedName("access")
    @Expose
    public String access;
    @Nullable
    @SerializedName("order")
    @Expose
    public Integer order;
    @Nullable
    @SerializedName("series_order")
    @Expose
    public Integer seriesOrder;
    @Nullable
    @SerializedName("number_of_allowed_audience_per_user")
    @Expose
    public Integer numberOfAllowedAudiencePerUser;
    @Nullable
    @SerializedName("title_bangla")
    @Expose
    public Object titleBangla;
    @Nullable
    @SerializedName("content_type")
    @Expose
    public Object contentType;
    @Nullable
    @SerializedName("vod_type")
    @Expose
    public String vodType;
    @Nullable
    @SerializedName("video_type")
    @Expose
    public String videoType;
    @Nullable
    @SerializedName("upload_date")
    @Expose
    public Object uploadDate;
    @Nullable
    @SerializedName("imdb")
    @Expose
    public Object imdb;
    @Nullable
    @SerializedName("saga")
    @Expose
    public Object saga;
    @Nullable
    @SerializedName("is_original")
    @Expose
    public String isOriginal;
    @Nullable
    @SerializedName("synopsis_english")
    @Expose
    public Object synopsisEnglish;
    @Nullable
    @SerializedName("synopsis_bangla")
    @Expose
    public Object synopsisBangla;
    @Nullable
    @SerializedName("genre")
    @Expose
    public String genre;
    @Nullable
    @SerializedName("tags")
    @Expose
    public String tags;
    @Nullable
    @SerializedName("associated_teaser")
    @Expose
    public Object associatedTeaser;
    @Nullable
    @SerializedName("up_comming")
    @Expose
    public Object upComming;
    @Nullable
    @SerializedName("content_owner_id")
    @Expose
    public Integer contentOwnerId;
    @Nullable
    @SerializedName("external_id")
    @Expose
    public String externalId;
    @Nullable
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @Nullable
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    public OttContent(Integer id, String uuid, String title, Object shortTitle, Integer rootCategoryId, Integer subCategoryId, Object subSubCategoryId, Object seriesId, Object contentTypeId, Object description, Object banglaDescription, Object year, Object runtime, Object youtubeUrl, Object cloudUrl, Object introStarts, Object introEnd, Object nextEnd, Object poster, Object backdrop, String thumbnailPortrait, String thumbnailLandscape, Object tvCover, Object viewCount, Object releaseDate, String status, String access, Integer order, Integer seriesOrder, Integer numberOfAllowedAudiencePerUser, Object titleBangla, Object contentType, String vodType, String videoType, Object uploadDate, Object imdb, Object saga, String isOriginal, Object synopsisEnglish, Object synopsisBangla, String genre, String tags, Object associatedTeaser, Object upComming, Integer contentOwnerId, String externalId, String createdAt, String updatedAt) {
        this.id = id;
        this.uuid = uuid;
        this.title = title;
        this.shortTitle = shortTitle;
        this.rootCategoryId = rootCategoryId;
        this.subCategoryId = subCategoryId;
        this.subSubCategoryId = subSubCategoryId;
        this.seriesId = seriesId;
        this.contentTypeId = contentTypeId;
        this.description = description;
        this.banglaDescription = banglaDescription;
        this.year = year;
        this.runtime = runtime;
        this.youtubeUrl = youtubeUrl;
        this.cloudUrl = cloudUrl;
        this.introStarts = introStarts;
        this.introEnd = introEnd;
        this.nextEnd = nextEnd;
        this.poster = poster;
        this.backdrop = backdrop;
        this.thumbnailPortrait = thumbnailPortrait;
        this.thumbnailLandscape = thumbnailLandscape;
        this.tvCover = tvCover;
        this.viewCount = viewCount;
        this.releaseDate = releaseDate;
        this.status = status;
        this.access = access;
        this.order = order;
        this.seriesOrder = seriesOrder;
        this.numberOfAllowedAudiencePerUser = numberOfAllowedAudiencePerUser;
        this.titleBangla = titleBangla;
        this.contentType = contentType;
        this.vodType = vodType;
        this.videoType = videoType;
        this.uploadDate = uploadDate;
        this.imdb = imdb;
        this.saga = saga;
        this.isOriginal = isOriginal;
        this.synopsisEnglish = synopsisEnglish;
        this.synopsisBangla = synopsisBangla;
        this.genre = genre;
        this.tags = tags;
        this.associatedTeaser = associatedTeaser;
        this.upComming = upComming;
        this.contentOwnerId = contentOwnerId;
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public Object getShortTitle() {
        return shortTitle;
    }

    public Integer getRootCategoryId() {
        return rootCategoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public Object getSubSubCategoryId() {
        return subSubCategoryId;
    }

    public Object getSeriesId() {
        return seriesId;
    }

    public Object getContentTypeId() {
        return contentTypeId;
    }

    public Object getDescription() {
        return description;
    }

    public Object getBanglaDescription() {
        return banglaDescription;
    }

    public Object getYear() {
        return year;
    }

    public Object getRuntime() {
        return runtime;
    }

    public Object getYoutubeUrl() {
        return youtubeUrl;
    }

    public Object getCloudUrl() {
        return cloudUrl;
    }

    public Object getIntroStarts() {
        return introStarts;
    }

    public Object getIntroEnd() {
        return introEnd;
    }

    public Object getNextEnd() {
        return nextEnd;
    }

    public Object getPoster() {
        return poster;
    }

    public Object getBackdrop() {
        return backdrop;
    }

    public String getThumbnailPortrait() {
        return thumbnailPortrait;
    }

    public String getThumbnailLandscape() {
        return thumbnailLandscape;
    }

    public Object getTvCover() {
        return tvCover;
    }

    public Object getViewCount() {
        return viewCount;
    }

    public Object getReleaseDate() {
        return releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public String getAccess() {
        return access;
    }

    public Integer getOrder() {
        return order;
    }

    public Integer getSeriesOrder() {
        return seriesOrder;
    }

    public Integer getNumberOfAllowedAudiencePerUser() {
        return numberOfAllowedAudiencePerUser;
    }

    public Object getTitleBangla() {
        return titleBangla;
    }

    public Object getContentType() {
        return contentType;
    }

    public String getVodType() {
        return vodType;
    }

    public String getVideoType() {
        return videoType;
    }

    public Object getUploadDate() {
        return uploadDate;
    }

    public Object getImdb() {
        return imdb;
    }

    public Object getSaga() {
        return saga;
    }

    public String getIsOriginal() {
        return isOriginal;
    }

    public Object getSynopsisEnglish() {
        return synopsisEnglish;
    }

    public Object getSynopsisBangla() {
        return synopsisBangla;
    }

    public String getGenre() {
        return genre;
    }

    public String getTags() {
        return tags;
    }

    public Object getAssociatedTeaser() {
        return associatedTeaser;
    }

    public Object getUpComming() {
        return upComming;
    }

    public Integer getContentOwnerId() {
        return contentOwnerId;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
