package com.bancosantander.testmobdevmvi.presentation.mvibase.breedsImg

import com.bancosantander.testmobdevmvi.presentation.mvibase.breeds.BreedsIntent

sealed class BreedsImgIntent {
    object FetchBreedsImg : BreedsImgIntent()

}