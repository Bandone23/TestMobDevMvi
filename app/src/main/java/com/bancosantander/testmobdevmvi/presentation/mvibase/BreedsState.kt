package com.bancosantander.testmobdevmvi.presentation.mvibase

import com.bancosantander.testmobdevmvi.data.remote.model.BreedsEntry

sealed class BreedsState {
    object Idb :BreedsState()
    object Loading :BreedsState()
    data class Breed(val breedList: List<String>):BreedsState()
    data class Error(val error:String?):BreedsState()

}