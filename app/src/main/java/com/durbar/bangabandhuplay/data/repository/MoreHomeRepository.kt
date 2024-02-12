package com.durbar.bangabandhuplay.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.Api
import com.durbar.bangabandhuplay.data.ApiService
import com.durbar.bangabandhuplay.data.model.category.single_sub.SingleSubCategoryRes
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug.CustomContentBySlug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoreHomeRepository(private val application: Application) {
    private val contentHome: MutableLiveData<CustomContentBySlug?>
    private val singleSubCategory: MutableLiveData<SingleSubCategoryRes?>
    private val apiService: ApiService

    init {
        apiService = Api.instance!!.apiService
        contentHome = MutableLiveData()
        singleSubCategory = MutableLiveData()
    }

    fun fetchContentForHome(slug: String?): MutableLiveData<CustomContentBySlug?> {
        val call = apiService.getCustomContentBySlug(slug)
        call.enqueue(object : Callback<CustomContentBySlug?> {
            override fun onResponse(
                call: Call<CustomContentBySlug?>,
                response: Response<CustomContentBySlug?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    //Log.v(TAG, response.body().getMessage());
                    contentHome.setValue(response.body())
                } else {
                    Toast.makeText(
                        application,
                        "Something went wrong!!" + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                    //Log.v(TAG, "response not successful");
                }            }

            override fun onFailure(call: Call<CustomContentBySlug?>, t: Throwable) {
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show()
                t.printStackTrace()          }
        })
        return contentHome
    }

    fun getSingleSubCategoryContents(slug: String?): MutableLiveData<SingleSubCategoryRes?> {
        val call = apiService.getSingleSubCategoryContents(slug)
        call.enqueue(object : Callback<SingleSubCategoryRes?> {
            override fun onResponse(
                call: Call<SingleSubCategoryRes?>,
                response: Response<SingleSubCategoryRes?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    singleSubCategory.setValue(response.body())
                } else {
                    Toast.makeText(
                        application,
                        "Something went wrong!!" + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                }            }

            override fun onFailure(call: Call<SingleSubCategoryRes?>, t: Throwable) {
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show()
                t.printStackTrace()            }
        })
        return singleSubCategory
    }
}
