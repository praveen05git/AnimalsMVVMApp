package com.hencesimplified.mvvmretrofitsample.di

import com.hencesimplified.mvvmretrofitsample.model.AnimalApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun apiInject(service: AnimalApiService) //service in a variable
}