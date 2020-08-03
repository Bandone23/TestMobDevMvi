package com.bancosantander.testmobdevmvi.data.remote.net

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://dog.ceo/api/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    val apiService: BreedsApi = getRetrofit().create(BreedsApi::class.java)

}