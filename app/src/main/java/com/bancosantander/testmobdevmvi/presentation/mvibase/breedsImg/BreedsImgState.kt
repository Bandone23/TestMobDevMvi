package com.bancosantander.testmobdevmvi.presentation.mvibase.breedsImg

import com.bancosantander.testmobdevmvi.presentation.mvibase.breeds.BreedsState

sealed class BreedsImgState {
    object Idb :
        BreedsImgState()
    object Loading :
        BreedsImgState()
    data class BreedImg(val breedListImg: List<String>):
        BreedsImgState()
    data class Error(val error:String?):
        BreedsImgState()
}