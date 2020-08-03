package com.bancosantander.testmobdevmvi.data.remote.model

import com.squareup.moshi.Json

data class BreedsImgEntry(
    @Json(name="message")
    val message: List<String>,
    @Json(name="status")
    val status:String= ""
)