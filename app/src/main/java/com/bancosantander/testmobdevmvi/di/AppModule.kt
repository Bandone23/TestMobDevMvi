package com.bancosantander.testmobdevmvi.di

import android.content.Context
import android.content.SharedPreferences
import com.bancosantander.testmobdevmvi.util.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            AppPreferences.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }
}