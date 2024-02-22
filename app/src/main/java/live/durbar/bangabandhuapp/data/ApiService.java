package live.durbar.bangabandhuapp.data;

import androidx.annotation.Keep;

import live.durbar.bangabandhuapp.data.model.category.root.single.SingleRootCategory;
import live.durbar.bangabandhuapp.data.model.family_member.FamilyMemberResponse;
import live.durbar.bangabandhuapp.data.model.fronend_custom_slider.FrontEndCustomSliders;
import live.durbar.bangabandhuapp.data.model.live.LiveStreamingResponse;
import live.durbar.bangabandhuapp.data.model.pathshala.PathshalaResponse;
import live.durbar.bangabandhuapp.data.model.photo_gallery.PhotoGalleryResponse;
import live.durbar.bangabandhuapp.data.model.sliders.Sliders;
import live.durbar.bangabandhuapp.data.model.category.single_sub.SingleSubCategoryRes;
import live.durbar.bangabandhuapp.data.model.frontend_custom_content.custom_content_by_slug.CustomContentBySlug;
import live.durbar.bangabandhuapp.data.model.frontend_custom_content.custom_contents.CustomContent;
import live.durbar.bangabandhuapp.data.model.get_related_contents.GetRelatedContent;
import live.durbar.bangabandhuapp.data.model.ott_content.SingleOttContent;
import live.durbar.bangabandhuapp.data.model.search_content.SearchResultRes;

import live.durbar.bangabandhuapp.data.model.category.root.single.SingleRootCategory;
import live.durbar.bangabandhuapp.data.model.category.single_sub.SingleSubCategoryRes;
import live.durbar.bangabandhuapp.data.model.live.LiveStreamingResponse;
import live.durbar.bangabandhuapp.data.model.pathshala.PathshalaResponse;
import live.durbar.bangabandhuapp.data.model.sliders.Sliders;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Keep public interface ApiService {
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

    @GET("get-live-streaming-list")
    Call<LiveStreamingResponse> getLiveStreaming();

    @GET("category-wise-ebook")
    Call<PathshalaResponse> getPathsahlaPdf();


    @GET("frontend/custom/sliders")
    Call<FrontEndCustomSliders> getFrontEndCustomSliders();

}



