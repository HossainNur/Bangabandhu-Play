package com.example.bangabandhuplay.data;

import com.example.bangabandhuplay.data.model.category.root.single.SingleRootCategory;
import com.example.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug.CustomContentBySlug;
import com.example.bangabandhuplay.data.model.frontend_custom_content.custom_contents.CustomContent;
import com.example.bangabandhuplay.data.model.frontend_custom_content.forntend_custom_content_section_slider.FrontendCustomContentSlider;
import com.example.bangabandhuplay.data.model.sliders.Sliders;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("frontend/custom/sections")
    Call<CustomContent> getFrontendCustomContent();

    @GET("sliders")
    Call<Sliders> getSliders();

    @GET("frontend/custom/section/sliders/{slug}")
    Call<FrontendCustomContentSlider> getFrontendCustomContentSectionSliders(@Path("slug") String slug);

    @GET("frontend/custom/section/slug/{slug}")
    Call<CustomContentBySlug> getCustomContentBySlug(@Path("slug") String slug);
    @GET("category/{slug}")
    Call<SingleRootCategory> getMoviesCategory(@Path("slug") String slug);
}
