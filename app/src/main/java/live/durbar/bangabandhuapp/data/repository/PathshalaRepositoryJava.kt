package live.durbar.bangabandhuapp.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.Api
import live.durbar.bangabandhuapp.data.ApiService
import live.durbar.bangabandhuapp.data.model.pathshala.PathshalaResponse
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
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
        return dataxList
    }
}
