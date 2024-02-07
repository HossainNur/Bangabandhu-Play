package com.durbar.bangabandhuplay.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.Api
import com.durbar.bangabandhuplay.data.ApiService
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.CustomContent
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.Data
import com.durbar.bangabandhuplay.data.model.sliders.Original
import com.durbar.bangabandhuplay.data.model.sliders.Sliders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(private val application: Application) {
    private val apiService: ApiService = Api.instance!!.apiService
    private val sliderList: MutableLiveData<List<Original>?> = MutableLiveData()
    private val dataList: MutableLiveData<List<Data>?> = MutableLiveData()

    fun fetchSlider(): MutableLiveData<List<Original>?> {
        val call: Call<Sliders> = apiService.sliders
        call.enqueue(object : Callback<Sliders> {
            override fun onResponse(call: Call<Sliders>, response: Response<Sliders>) {
                if (response.isSuccessful && response.body() != null && response.body()?.data?.original != null) {
                    sliderList.value = response.body()?.data?.original
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Sliders>, t: Throwable) {
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
        return sliderList
    }

    fun getFrontendSection(): MutableLiveData<List<Data>?> {
        val call: Call<CustomContent> = apiService.frontendCustomContent
        call.enqueue(object : Callback<CustomContent> {
            override fun onResponse(call: Call<CustomContent>, response: Response<CustomContent>) {
                if (response.isSuccessful && response.body() != null && response.body()?.data != null) {
                    dataList.value = response.body()?.data
                } else {
                    Toast.makeText(application, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CustomContent>, t: Throwable) {
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
        return dataList
    }
}
