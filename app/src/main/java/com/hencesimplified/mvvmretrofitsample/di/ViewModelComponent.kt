package com.hencesimplified.mvvmretrofitsample.di

import com.hencesimplified.mvvmretrofitsample.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)
}