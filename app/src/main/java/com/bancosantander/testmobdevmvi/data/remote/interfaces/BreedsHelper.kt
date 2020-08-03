package com.bancosantander.testmobdevmvi.data.remote.interfaces

import com.bancosantander.testmobdevmvi.data.remote.model.BreedsEntry
import com.bancosantander.testmobdevmvi.data.remote.model.BreedsImgEntry

interface BreedsHelper {
    suspend fun getBreeds():BreedsEntry
    suspend fun getBreedsImg(name:String):BreedsImgEntry
}