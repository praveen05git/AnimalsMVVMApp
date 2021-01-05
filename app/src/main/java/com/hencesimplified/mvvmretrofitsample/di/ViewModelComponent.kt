package com.hencesimplified.mvvmretrofitsample.di

import com.hencesimplified.mvvmretrofitsample.viewmodel.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton //Since PrefsModule is singleton
@Component(modules = [ApiModule::class, PrefsModule::class, AppModule::class])
interface ViewModelComponent {
    fun injectAll(viewModel: ListViewModel)
}