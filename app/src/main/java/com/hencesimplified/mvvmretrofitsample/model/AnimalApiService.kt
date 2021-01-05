package com.hencesimplified.mvvmretrofitsample.model

import com.hencesimplified.mvvmretrofitsample.di.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AnimalApiService {

    //private val BASE_URL = "https://us-central1-apis-4674e.cloudfunctions.net"

    /*
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(AnimalApi::class.java)
     */

    @Inject
    lateinit var api: AnimalApi

    init {
        DaggerApiComponent.create()
            .apiInject(this) //DaggerApiComponent is a generated class from ApiComponent Interface
    }

    fun getApiKey(): Single<ApiKey> {
        return api.getApiKey()
    }

    fun getAnimals(key: String): Single<List<Animal>> {
        return api.getAnimals(key)
    }

}