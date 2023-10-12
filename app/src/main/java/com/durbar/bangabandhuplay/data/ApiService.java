package com.durbar.bangabandhuplay.data;

import com.durbar.bangabandhuplay.data.model.category.root.single.SingleRootCategory;
import com.durbar.bangabandhuplay.data.model.family_member.FamilyMemberResponse;
import com.durbar.bangabandhuplay.data.model.photo_gallery.PhotoGalleryResponse;
import com.durbar.bangabandhuplay.data.model.sliders.Sliders;
import com.durbar.bangabandhuplay.data.model.category.single_sub.SingleSubCategoryRes;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug.CustomContentBySlug;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.CustomContent;
import com.durbar.bangabandhuplay.data.model.get_related_contents.GetRelatedContent;
import com.durbar.bangabandhuplay.data.model.ott_content.SingleOttContent;
import com.durbar.bangabandhuplay.data.model.search_content.SearchResultRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("frontend/custom/sections")
    Call<CustomContent> getFrontendCustomContent();

    @GET("sliders")
    Call<Sliders> getSliders();

    @GET("frontend/custom/section/slug/{slug}")
    Call<CustomContentBySlug> getCustomContentBySlug(@Path("slug") String slug);
    @GET("category/{slug}")
    Call<SingleRootCategory> getMoviesCategory(@Path("slug") String slug);

    @GET("sub-category/contents/{slug}")
    Call<SingleSubCategoryRes> getSingleSubCategoryContents(@Path("slug") String slug);

    @GET("single-ott-content/{UUID}")
    Call<SingleOttContent> getSingleOttContent(@Path("UUID") String UUID);

    @GET("get-related-content/{UUID}")
    Call<GetRelatedContent> getRelatedOttContents(@Path("UUID") String UUID);

    @GET("ott-content/search")
    Call<SearchResultRes> getSearchContents(@Query("keyword") String keyword);

    @GET("family-members")
    Call<FamilyMemberResponse> getFamilyMemberPhotos();

    @GET("photo-gallery")
    Call<PhotoGalleryResponse> getPhotoGalleryPhotos();
}



