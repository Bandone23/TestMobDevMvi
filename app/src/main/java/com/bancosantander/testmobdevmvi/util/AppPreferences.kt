package com.bancosantander.testmobdevmvi.util

import android.content.SharedPreferences
import androidx.core.content.edit
import org.koin.core.KoinComponent
import org.koin.core.inject

object AppPreferences: KoinComponent {

    private val preferences: SharedPreferences by inject()
    private const val NAME = "nem"
    const val SHARED_PREFERENCES_NAME = "TestMobDevMvi"


    var nameBreeds: String?
        get() = preferences.getString(NAME, "")
        set(value) = preferences.edit {
            putString(NAME, value).apply()
        }

}