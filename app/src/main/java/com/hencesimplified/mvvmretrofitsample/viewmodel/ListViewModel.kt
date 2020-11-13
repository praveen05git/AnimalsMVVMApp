package com.hencesimplified.mvvmretrofitsample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hencesimplified.mvvmretrofitsample.model.Animal
import com.hencesimplified.mvvmretrofitsample.model.AnimalApiService
import com.hencesimplified.mvvmretrofitsample.model.ApiKey
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()
    private val apiService = AnimalApiService()

    fun refresh() {
        loading.value = true
        getKey()
    }

    private fun getKey() {
        disposable.add(
            apiService.getApiKey()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ApiKey>() {
                    override fun onSuccess(apiKey: ApiKey) {
                        if (apiKey.key.isNullOrEmpty()) {
                            loadError.value = true
                            loading.value = false
                        } else {
                            getAnimals(apiKey.key)
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    private fun getAnimals(key: String) {
        disposable.add(
            apiService.getAnimals(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Animal>>() {
                    override fun onSuccess(animalList: List<Animal>) {
                        animals.value = animalList
                        loadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        animals.value = null
                        loadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    /*
    //Local hardcoded data
    private fun getAnimals() {
        val a1 = Animal("Dog")
        val a2 = Animal("Cat")

        val animalList: ArrayList<Animal> = arrayListOf(a1, a2)

        animals.value = animalList
        loadError.value = false
        loading.value = false
    }
     */
}