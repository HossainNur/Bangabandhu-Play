package com.durbar.bangabandhuplay.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.ApiLive
import com.durbar.bangabandhuplay.data.ApiService
import com.durbar.bangabandhuplay.data.model.pathshala.Ebook
import com.durbar.bangabandhuplay.data.model.pathshala.PathshalaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PathshalaRepository {
      private lateinit var application : Application
      private lateinit var apiService: ApiService
      private val dataList: MutableLiveData<List<Ebook>?> = MutableLiveData()

      constructor(application: Application){
          this.application = application
          apiService = ApiLive.getInstance().apiService
      }

      fun fetchPdfs(): MutableLiveData<List<Ebook>?> {
          val call: Call<PathshalaResponse> = apiService.pathsahlaPdf
          call.enqueue(object : Callback<PathshalaResponse> {
              override fun onResponse(call: Call<PathshalaResponse>, response: Response<PathshalaResponse>) {
                  if (response.isSuccessful && response.body() != null && response.body()?.data != null){
                      dataList.value = response.body()!!.data?.data?.first()?.ebooks as List<Ebook>?
                  }else{
                      Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show()
                  }
              }

              override fun onFailure(call: Call<PathshalaResponse>, t: Throwable) {
                  Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
                  t.printStackTrace()
              }

          })

          return dataList
      }
}