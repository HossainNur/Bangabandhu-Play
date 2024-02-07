package com.durbar.bangabandhuplay.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.Api
import com.durbar.bangabandhuplay.data.ApiService
import com.durbar.bangabandhuplay.data.model.pathshala.PathshalaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PathshalaRepositoryJava(private val application: Application) {
    private val apiService: ApiService
    private val dataxList: MutableLiveData<PathshalaResponse?>

    init {
        apiService = Api.instance!!.apiService
        dataxList = MutableLiveData()
    }

    fun fetchEbook(): MutableLiveData<PathshalaResponse?> {
        val call = apiService.pathsahlaPdf
        call.enqueue(object : Callback<PathshalaResponse?> {
            override fun onResponse(
                call: Call<PathshalaResponse?>,
                response: Response<PathshalaResponse?>
            ) {
                if (response.isSuccessful && response.body() != null && response.body()!!.data != null) {
                    dataxList.setValue(response.body())
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PathshalaResponse?>, t: Throwable) {
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return dataxList
    }
}
