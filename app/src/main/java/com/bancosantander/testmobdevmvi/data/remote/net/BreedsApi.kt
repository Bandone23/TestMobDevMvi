package com.bancosantander.testmobdevmvi.data.remote.net

import com.bancosantander.testmobdevmvi.data.remote.model.BreedsEntry
import com.bancosantander.testmobdevmvi.data.remote.model.BreedsImgEntry
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsApi {
    @GET("breeds/list")
     fun getBreeds(): Call<BreedsEntry>

    @GET("breed/{nameId}/images")
    fun getBreedsImg(@Path("nameId") name:String): Call<BreedsImgEntry>

}