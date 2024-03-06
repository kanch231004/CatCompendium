package com.example.catcompendium.api

import com.example.catcompendium.api.config.BASE_URL
import com.example.catcompendium.api.config.CONNECT_TIMEOUT
import com.example.catcompendium.model.CatBreedItem
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    private val okHttpClient =  OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

     fun getService(): CatServiceApp = retrofit.create(CatServiceApp::class.java)
}

interface CatService {
    @GET("v1/breeds")
    suspend fun getCatBreeds(@Query("page") page: Int , @Query("limit") limit: Int): Response<ArrayList<CatBreedItem>>
}

interface CatServiceApp: CatService

