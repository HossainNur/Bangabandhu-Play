package com.durbar.bangabandhuplay.data

import com.durbar.bangabandhuplay.utils.Constants
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.Volatile

class Api private constructor() {
    @JvmField
    val apiService: ApiService

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) //add our client
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    companion object {
        @JvmStatic
        @Volatile
        var instance: Api? = null
            get() {
                if (field == null) synchronized(
                    Api::class.java
                ) {
                    if (field == null) {
                        field = Api()
                    }
                }
                return field
            }
            private set
    }
}
