package com.durbar.bangabandhuplay.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.Api
import com.durbar.bangabandhuplay.data.ApiService
import com.durbar.bangabandhuplay.data.model.search_content.SearchResultRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchContentsRepository(private val application: Application) {
    private val apiService: ApiService
    private val searchData: MutableLiveData<SearchResultRes?>

    init {
        apiService = Api.getInstance().apiService
        searchData = MutableLiveData()
    }

    fun getSearchContents(keyword: String?): MutableLiveData<SearchResultRes?> {
        val call = apiService.getSearchContents(keyword)
        call.enqueue(object : Callback<SearchResultRes?> {
            override fun onResponse(
                call: Call<SearchResultRes?>,
                response: Response<SearchResultRes?>
            ) {
                if (response.isSuccessful && response.body() != null && response.body()!!.data != null) {
                    searchData.setValue(response.body())
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SearchResultRes?>, t: Throwable) {
                Toast.makeText(application, "Failed - " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return searchData
    }
}
