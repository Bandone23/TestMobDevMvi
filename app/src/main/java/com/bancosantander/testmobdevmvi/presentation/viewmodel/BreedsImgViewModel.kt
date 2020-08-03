package com.bancosantander.testmobdevmvi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancosantander.testmobdevmvi.data.repository.BreedsRepository
import com.bancosantander.testmobdevmvi.presentation.mvibase.breeds.BreedsIntent
import com.bancosantander.testmobdevmvi.presentation.mvibase.breeds.BreedsState
import com.bancosantander.testmobdevmvi.presentation.mvibase.breedsImg.BreedsImgIntent
import com.bancosantander.testmobdevmvi.presentation.mvibase.breedsImg.BreedsImgState
import com.bancosantander.testmobdevmvi.util.AppPreferences
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class BreedsImgViewModel(
    private val repository: BreedsRepository
):ViewModel() {
    val breedImgIntent = Channel<BreedsImgIntent>(Channel.UNLIMITED)

    private val stateId = MutableStateFlow<BreedsImgState>(BreedsImgState.Idb)

    val state: StateFlow<BreedsImgState>
        get() = stateId

    init {
        handleIntent(name = AppPreferences.nameBreeds!!.toString())
    }

    private fun handleIntent(name:String) {
        viewModelScope.launch {
            breedImgIntent.consumeAsFlow().collect {
                when (it) {
                    is BreedsImgIntent.FetchBreedsImg -> fetchBreedsImg(name = name)
                }
            }
        }
    }

    private fun fetchBreedsImg(name:String) {
        viewModelScope.launch {
            stateId.value = BreedsImgState.Loading
            stateId.value = try {
                BreedsImgState.BreedImg(repository.getBreedsImg(name).message)
            } catch (e: Exception) {
                BreedsImgState.Error(e.localizedMessage)
            }
        }
    }
}