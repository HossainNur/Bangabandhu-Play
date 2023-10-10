package com.durbar.bangabandhuplay.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.Api
import com.durbar.bangabandhuplay.data.ApiService
import com.durbar.bangabandhuplay.data.model.photo_gallery.Data
import com.durbar.bangabandhuplay.data.model.photo_gallery.PhotoGallery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrawerMenuRepository {
    private var application: Application? = null
    private var apiService: ApiService? = null
    val dataList: MutableLiveData<List<Data>> = MutableLiveData()

    init{
        this.application = application
        apiService = Api.getInstance().apiService
    }

fun getPhotos(): MutableLiveData<List<Data>> {
    val call: Call<PhotoGallery> = apiService!!.photos
    call.enqueue(object : Callback<PhotoGallery> {
        override fun onResponse(call: Call<PhotoGallery>, response: Response<PhotoGallery>) {
            if (response.isSuccessful && response.body() != null) {
                dataList.value = response.body()!!.data
            } else {
                Toast.makeText(application, "Something went wrong!!", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<PhotoGallery>, t: Throwable) {
            Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            t.printStackTrace()
        }

    })
    return dataList
}

}