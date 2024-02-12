package com.durbar.bangabandhuplay.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.Api
import com.durbar.bangabandhuplay.data.ApiService
import com.durbar.bangabandhuplay.data.model.family_member.Data
import com.durbar.bangabandhuplay.data.model.family_member.FamilyMemberResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FamilyMemberRepository(private val application: Application) {
    private val apiService: ApiService
    private val dataList: MutableLiveData<List<Data>>

    init {
        apiService = Api.instance!!.apiService
        dataList = MutableLiveData()
    }

    fun fetchFamilyMemberPhotos(): MutableLiveData<List<Data>> {
        val call = apiService.familyMemberPhotos
        call.enqueue(object : Callback<FamilyMemberResponse?> {

            override fun onResponse(
                call: Call<FamilyMemberResponse?>,
                response: Response<FamilyMemberResponse?>
            ) {
                if (response.isSuccessful && response.body() != null && response.body()!!.data != null) {
                    dataList.setValue(response.body()!!.data)
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show()
                }            }

            override fun onFailure(call: Call<FamilyMemberResponse?>, t: Throwable) {
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show()
                t.printStackTrace()           }
        })
        return dataList
    }
}
