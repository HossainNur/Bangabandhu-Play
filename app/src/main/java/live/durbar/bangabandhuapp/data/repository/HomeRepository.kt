package live.durbar.bangabandhuapp.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.Api
import live.durbar.bangabandhuapp.data.ApiService
import live.durbar.bangabandhuapp.data.model.fronend_custom_slider.FrontEndCustomSliders
import live.durbar.bangabandhuapp.data.model.frontend_custom_content.custom_contents.CustomContent
import live.durbar.bangabandhuapp.data.model.frontend_custom_content.custom_contents.Data
import live.durbar.bangabandhuapp.data.model.sliders.Original
import live.durbar.bangabandhuapp.data.model.sliders.Sliders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(private val application: Application) {
    private val apiService: ApiService = Api.instance!!.apiService
    private val sliderList: MutableLiveData<List<Original>?> = MutableLiveData()
    private val dataList: MutableLiveData<List<Data>?> = MutableLiveData()
    private val customSliderList: MutableLiveData<List<live.durbar.bangabandhuapp.data.model.fronend_custom_slider.Original>?> = MutableLiveData()

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
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
        return dataList
    }


    fun fetchCustomSlider(): MutableLiveData<List<live.durbar.bangabandhuapp.data.model.fronend_custom_slider.Original>?> {
        val call: Call<FrontEndCustomSliders> = apiService.frontEndCustomSliders
        call.enqueue(object : Callback<FrontEndCustomSliders>{
            override fun onResponse(call: Call<FrontEndCustomSliders>, response: Response<FrontEndCustomSliders>) {
                if (response.isSuccessful && response.body() != null && response.body()?.data?.original != null) {
                    customSliderList.value = response.body()?.data?.original
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<FrontEndCustomSliders>, t: Throwable) {
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
        return customSliderList
    }
}
