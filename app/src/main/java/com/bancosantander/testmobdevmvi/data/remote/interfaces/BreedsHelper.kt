package com.bancosantander.testmobdevmvi.data.remote.interfaces

import com.bancosantander.testmobdevmvi.data.remote.model.BreedsEntry

interface BreedsHelper {
    suspend fun getBreeds():BreedsEntry
}