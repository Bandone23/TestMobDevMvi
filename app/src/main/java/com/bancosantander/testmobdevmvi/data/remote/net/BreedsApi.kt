package com.bancosantander.testmobdevmvi.data.remote.net

import com.bancosantander.testmobdevmvi.data.remote.model.BreedsEntry
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface BreedsApi {
    @GET("breeds/list")
     fun getBreeds(): Call<BreedsEntry>
}