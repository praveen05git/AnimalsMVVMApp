package com.hencesimplified.mvvmretrofitsample.di

import android.app.Activity
import android.app.Application
import com.hencesimplified.mvvmretrofitsample.util.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class PrefsModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferencesHelper {
        return SharedPreferencesHelper(app)
    }

    /*
    //Providing Activity Context
    @Provides
    @Singleton
    fun provideActivitySharedPreferences(activity: Activity): SharedPreferencesHelper {
        return SharedPreferencesHelper(activity)
    }
     */

}

/*
const val CONTEXT_APP = "Application context"
const val CONTEXT_ACTIVITY = "Activity context"

@Qualifier
annotation class TypeOfContext(val type: String)
 */
