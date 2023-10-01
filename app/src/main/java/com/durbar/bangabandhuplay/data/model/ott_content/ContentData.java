package com.durbar.bangabandhuplay.data.model.ott_content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentData {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("uuid")
    @Expose
    public String uuid;
    @SerializedName("access")
    @Expose
    public String access;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("short_title")
    @Expose
    public Object shortTitle;
    @SerializedName("root_category_id")
    @Expose
    public Integer rootCategoryId;
    @SerializedName("sub_category_id")
    @Expose
    public Object subCategoryId;
    @SerializedName("sub_sub_category_id")
    @Expose
    public Object subSubCategoryId;
    @SerializedName("series_id")
    @Expose
    public Object seriesId;
    @SerializedName("content_type_id")
    @Expose
    public Object contentTypeId;
    @SerializedName("description")
    @Expose
    public Object description;
    @SerializedName("year")
    @Expose
    public Object year;
    @SerializedName("runtime")
    @Expose
    public String runtime;
    @SerializedName("youtube_url")
    @Expose
    public Object youtubeUrl;
    @SerializedName("cloud_url")
    @Expose
    public Object cloudUrl;
    @SerializedName("poster")
    @Expose
    public Object poster;
    @SerializedName("backdrop")
    @Expose
    public Object backdrop;
    @SerializedName("view_count")
    @Expose
    public Object viewCount;
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("order")
    @Expose
    public Integer order;
    @SerializedName("content_meta")
    @Expose
    public List<Object> contentMeta;
    @SerializedName("series_info")
    @Expose
    public Object seriesInfo;
    @SerializedName("content_source")
    @Expose
    public List<ContentSource> contentSource;
    @SerializedName("average_review_count")
    @Expose
    public Integer averageReviewCount;
    @SerializedName("reviews")
    @Expose
    public List<Object> reviews;
    @SerializedName("cast_and_crews")
    @Expose
    public List<CastAndCrew> castAndCrews;
    @SerializedName("synopsis_english")
    @Expose
    public String synopsisEnglish;
    @SerializedName("synopsis_bangla")
    @Expose
    public Object synopsisBangla;
    @SerializedName("thumbnail_portrait")
    @Expose
    public String thumbnailPortrait;
    @SerializedName("thumbnail_landscape")
    @Expose
    public String thumbnailLandscape;
    @SerializedName("imdb")
    @Expose
    public String imdb;
    @SerializedName("saga")
    @Expose
    public String saga;
    @SerializedName("genre")
    @Expose
    public String genre;
    @SerializedName("is_original")
    @Expose
    public String isOriginal;
    @SerializedName("video_type")
    @Expose
    public String videoType;

    public ContentData(Integer id, String uuid, String access, String title, Object shortTitle, Integer rootCategoryId, Object subCategoryId, Object subSubCategoryId, Object seriesId, Object contentTypeId, Object description, Object year, String runtime, Object youtubeUrl, Object cloudUrl, Object poster, Object backdrop, Object viewCount, String releaseDate, String status, Integer order, List<Object> contentMeta, Object seriesInfo, List<ContentSource> contentSource, Integer averageReviewCount, List<Object> reviews, List<CastAndCrew> castAndCrews, String synopsisEnglish, Object synopsisBangla, String thumbnailPortrait, String thumbnailLandscape, String imdb, String saga, String genre, String isOriginal, String videoType) {
        this.id = id;
        this.uuid = uuid;
        this.access = access;
        this.title = title;
        this.shortTitle = shortTitle;
        this.rootCategoryId = rootCategoryId;
        this.subCategoryId = subCategoryId;
        this.subSubCategoryId = subSubCategoryId;
        this.seriesId = seriesId;
        this.contentTypeId = contentTypeId;
        this.description = description;
        this.year = year;
        this.runtime = runtime;
        this.youtubeUrl = youtubeUrl;
        this.cloudUrl = cloudUrl;
        this.poster = poster;
        this.backdrop = backdrop;
        this.viewCount = viewCount;
        this.releaseDate = releaseDate;
        this.status = status;
        this.order = order;
        this.contentMeta = contentMeta;
        this.seriesInfo = seriesInfo;
        this.contentSource = contentSource;
        this.averageReviewCount = averageReviewCount;
        this.reviews = reviews;
        this.castAndCrews = castAndCrews;
        this.synopsisEnglish = synopsisEnglish;
        this.synopsisBangla = synopsisBangla;
        this.thumbnailPortrait = thumbnailPortrait;
        this.thumbnailLandscape = thumbnailLandscape;
        this.imdb = imdb;
        this.saga = saga;
        this.genre = genre;
        this.isOriginal = isOriginal;
        this.videoType = videoType;
    }

    public Integer getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getAccess() {
        return access;
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

    public Object getSubCategoryId() {
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

    public Object getYear() {
        return year;
    }

    public String getRuntime() {
        return runtime;
    }

    public Object getYoutubeUrl() {
        return youtubeUrl;
    }

    public Object getCloudUrl() {
        return cloudUrl;
    }

    public Object getPoster() {
        return poster;
    }

    public Object getBackdrop() {
        return backdrop;
    }

    public Object getViewCount() {
        return viewCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public Integer getOrder() {
        return order;
    }

    public List<Object> getContentMeta() {
        return contentMeta;
    }

    public Object getSeriesInfo() {
        return seriesInfo;
    }

    public List<ContentSource> getContentSource() {
        return contentSource;
    }

    public Integer getAverageReviewCount() {
        return averageReviewCount;
    }

    public List<Object> getReviews() {
        return reviews;
    }

    public List<CastAndCrew> getCastAndCrews() {
        return castAndCrews;
    }

    public String getSynopsisEnglish() {
        return synopsisEnglish;
    }

    public Object getSynopsisBangla() {
        return synopsisBangla;
    }

    public String getThumbnailPortrait() {
        return thumbnailPortrait;
    }

    public String getThumbnailLandscape() {
        return thumbnailLandscape;
    }

    public String getImdb() {
        return imdb;
    }

    public String getSaga() {
        return saga;
    }

    public String getGenre() {
        return genre;
    }

    public String getIsOriginal() {
        return isOriginal;
    }

    public String getVideoType() {
        return videoType;
    }
}
