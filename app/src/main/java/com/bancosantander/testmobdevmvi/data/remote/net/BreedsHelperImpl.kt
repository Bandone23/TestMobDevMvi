package com.bancosantander.testmobdevmvi.data.remote.net

import com.bancosantander.testmobdevmvi.data.remote.interfaces.BreedsHelper
import com.bancosantander.testmobdevmvi.data.remote.model.BreedsEntry
import retrofit2.await

class BreedsHelperImpl(
    private val api: BreedsApi
) : BreedsHelper {
    override suspend fun getBreeds(): BreedsEntry {
        return  api.getBreeds().await()
    }
}