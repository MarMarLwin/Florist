package com.example.floristbypo.repo

import com.google.android.gms.common.api.Api
import com.google.firebase.inappmessaging.internal.ApiClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object APIClient {

    private var retrofit: Retrofit? = null
    private var mInstance: APIClient? = null


    val client: Retrofit
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            retrofit = Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com/2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit!!
        }

    @Synchronized
    fun getInstance(): APIClient? {
        if (mInstance == null) {
            mInstance = APIClient
        }
        return mInstance
    }

    fun getApi(): APIInterface? {
        return retrofit?.create(APIInterface::class.java)
    }
}
