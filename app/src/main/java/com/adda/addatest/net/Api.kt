package com.adda.addatest.net

import com.adda.addatest.model.Users
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object Api {
    var api: ApiInterface? = null
        get() {
            if (field == null) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
                val gson = GsonBuilder()
                    .registerTypeAdapter(
                        Users::class.java,
                        UsersDeserializer()
                    )
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                field = retrofit.create(ApiInterface::class.java)
            }
            return field
        }
        private set
    private const val BASE_URL = "https://gorest.co.in/"

    interface ApiInterface {
        @get:GET("public-api/users")
        val users: Call<Users?>?
    }
}