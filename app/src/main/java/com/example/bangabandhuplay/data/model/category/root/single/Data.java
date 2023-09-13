package com.example.bangabandhuplay.data.model.category.root.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("seo_title")
    @Expose
    private String seoTitle;
    @SerializedName("seo_description")
    @Expose
    private String seoDescription;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("subCategories")
    @Expose
    private List<SubCategory> subCategories ;
    @SerializedName("categorySliders")
    @Expose
    private List<CategorySlider> categorySliders;
    @SerializedName("selected_category_items")
    @Expose
    private List<SelectedCategoryItem> selectedCategoryItems;

    public Data(Integer id, String title, String slug, String image, Integer order, String seoTitle, String seoDescription, String status, List<SubCategory> subCategories, List<CategorySlider> categorySliders, List<SelectedCategoryItem> selectedCategoryItems) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.image = image;
        this.order = order;
        this.seoTitle = seoTitle;
        this.seoDescription = seoDescription;
        this.status = status;
        this.subCategories = subCategories;
        this.categorySliders = categorySliders;
        this.selectedCategoryItems = selectedCategoryItems;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getImage() {
        return image;
    }

    public Integer getOrder() {
        return order;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public String getStatus() {
        return status;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public List<CategorySlider> getCategorySliders() {
        return categorySliders;
    }

    public List<SelectedCategoryItem> getSelectedCategoryItems() {
        return selectedCategoryItems;
    }
}
