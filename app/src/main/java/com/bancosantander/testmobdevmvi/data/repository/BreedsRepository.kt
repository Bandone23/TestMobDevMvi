package com.bancosantander.testmobdevmvi.data.repository

import com.bancosantander.testmobdevmvi.data.remote.interfaces.BreedsHelper

class BreedsRepository(
    private val breedsHelper: BreedsHelper
) {
    suspend fun getBreeds() = breedsHelper.getBreeds()
    suspend fun getBreedsImg(name:String) = breedsHelper.getBreedsImg(name)
}