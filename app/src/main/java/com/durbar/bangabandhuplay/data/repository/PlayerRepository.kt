package com.durbar.bangabandhuplay.data.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.Api
import com.durbar.bangabandhuplay.data.ApiService
import com.durbar.bangabandhuplay.data.model.get_related_contents.GetRelatedContent
import com.durbar.bangabandhuplay.data.model.get_related_contents.SingleContentRelatedContent
import com.durbar.bangabandhuplay.data.model.ott_content.SingleOttContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerRepository(private val application: Application) {
    private val apiService: ApiService
    private val content: MutableLiveData<SingleOttContent?>
    private val relatedContent: MutableLiveData<List<SingleContentRelatedContent>?>

    init {
        apiService = Api.getInstance().apiService
        content = MutableLiveData()
        relatedContent = MutableLiveData()
    }

    fun fetchContent(UUID: String?): MutableLiveData<SingleOttContent?> {
        val call = apiService.getSingleOttContent(UUID)
        call.enqueue(object : Callback<SingleOttContent?> {
            override fun onResponse(
                call: Call<SingleOttContent?>,
                response: Response<SingleOttContent?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    if (response.body()!!.data != null) content.value = response.body()
                    Log.v(TAG, "code: " + response.code() + " message: " + response.message())
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SingleOttContent?>, t: Throwable) {
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return content
    }

    fun getRelatedOttContents(UUID: String?): MutableLiveData<List<SingleContentRelatedContent>?> {
        val call = apiService.getRelatedOttContents(UUID)
        call.enqueue(object : Callback<GetRelatedContent?> {
            override fun onResponse(
                call: Call<GetRelatedContent?>,
                response: Response<GetRelatedContent?>
            ) {
                if (response.isSuccessful && response.body() != null && response.body()!!.data!!.singleContentRelatedContents != null) {
                    relatedContent.value = response.body()!!.data!!.singleContentRelatedContents
                    Log.d("response", "" + response.body()!!.data!!.singleContentRelatedContents)
                } else {
                    Toast.makeText(
                        application,
                        response.code().toString() + " - " + response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<GetRelatedContent?>, t: Throwable) {
                Toast.makeText(application, "Failed - " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return relatedContent
    }

    companion object {
        private const val TAG = "PlayerRepository"
    }
}
