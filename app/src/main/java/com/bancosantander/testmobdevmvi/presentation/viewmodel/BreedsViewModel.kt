package com.bancosantander.testmobdevmvi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancosantander.testmobdevmvi.data.repository.BreedsRepository
import com.bancosantander.testmobdevmvi.presentation.mvibase.BreedsIntent
import com.bancosantander.testmobdevmvi.presentation.mvibase.BreedsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class BreedsViewModel (
    private val repository: BreedsRepository
):ViewModel() {


    val breedIntent = Channel<BreedsIntent>(Channel.UNLIMITED)
    private val stateId = MutableStateFlow<BreedsState>(BreedsState.Idb)
    val state:StateFlow<BreedsState>
    get() = stateId

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            breedIntent.consumeAsFlow().collect {
                when (it) {
                    is BreedsIntent.FetchBreeds -> fetchBreeds()
                }
            }
        }
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            stateId.value = BreedsState.Loading
            stateId.value = try {
                BreedsState.Breed(repository.getBreeds().message)
            } catch (e: Exception) {
                BreedsState.Error(e.localizedMessage)
            }
        }
    }
}