package live.durbar.bangabandhuapp.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.Api
import live.durbar.bangabandhuapp.data.ApiService
import live.durbar.bangabandhuapp.data.model.photo_gallery.Data
import live.durbar.bangabandhuapp.data.model.photo_gallery.PhotoGalleryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoGalleryRepository {
    private lateinit var application : Application
    private lateinit var apiService: ApiService
    private val dataList: MutableLiveData<List<Data>> = MutableLiveData()

    constructor(application: Application){
        this.application = application
        apiService = Api.instance!!.apiService
    }

    fun fetchGalleryPhotos(): MutableLiveData<List<Data>>{
        val call: Call<PhotoGalleryResponse> = apiService.photoGalleryPhotos
        call.enqueue(object : Callback<PhotoGalleryResponse>{
            override fun onResponse(call: Call<PhotoGalleryResponse>, response: Response<PhotoGalleryResponse>) {
                if (response.isSuccessful && response.body() != null && response.body()?.data != null){
                    dataList.value = response.body()?.data
                }else{
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PhotoGalleryResponse>, t: Throwable) {
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })

        return dataList
    }
}