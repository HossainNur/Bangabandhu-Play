package com.durbar.bangabandhuplay.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.Api
import com.durbar.bangabandhuplay.data.ApiService
import com.durbar.bangabandhuplay.data.model.category.root.single.Data
import com.durbar.bangabandhuplay.data.model.category.root.single.SingleRootCategory
import com.durbar.bangabandhuplay.data.model.sliders.Original
import com.durbar.bangabandhuplay.data.model.sliders.Sliders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository(private val application: Application) {
    private val apiService: ApiService
    private val sliderList: MutableLiveData<List<Original>?>
    private val dataList: MutableLiveData<List<Data>?>

    init {
        apiService = Api.getInstance().apiService
        sliderList = MutableLiveData()
        dataList = MutableLiveData()
    }

    fun fetchSlider(): MutableLiveData<List<Original>?> {
        val call = apiService.sliders
        call.enqueue(object : Callback<Sliders?> {
            override fun onResponse(call: Call<Sliders?>, response: Response<Sliders?>) {
                if (response.isSuccessful && response.body() != null && response.body()!!.data!!.original != null) {
                    sliderList.setValue(response.body()!!.data!!.original)
                } else {
                    Toast.makeText(application, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Sliders?>, t: Throwable) {
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
        return sliderList
    }

    fun fetchMoviesCategory(slug: String?): MutableLiveData<List<Data>?> {
        val call = apiService.getMoviesCategory(slug)
        call.enqueue(object : Callback<SingleRootCategory?> {
            override fun onResponse(
                call: Call<SingleRootCategory?>,
                response: Response<SingleRootCategory?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    dataList.setValue(response.body()!!.data)
                } else {
                    Toast.makeText(
                        application,
                        response.code().toString() + " - " + response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<SingleRootCategory?>, t: Throwable) {
                Toast.makeText(application, "Failed - " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return dataList
    }
}
