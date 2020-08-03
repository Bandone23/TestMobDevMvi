package com.bancosantander.testmobdevmvi.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bancosantander.testmobdevmvi.data.remote.interfaces.BreedsHelper
import com.bancosantander.testmobdevmvi.data.repository.BreedsRepository
import com.bancosantander.testmobdevmvi.presentation.viewmodel.BreedsViewModel


class ViewModelFactory(private val breedsHelper: BreedsHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BreedsViewModel::class.java)) {

            return BreedsViewModel(BreedsRepository(breedsHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}