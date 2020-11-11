package com.hencesimplified.mvvmretrofitsample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hencesimplified.mvvmretrofitsample.model.Animal

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    fun refresh() {
        getAnimals()
    }

    private fun getAnimals() {
        val a1 = Animal("Dog")
        val a2 = Animal("Cat")

        val animalList: ArrayList<Animal> = arrayListOf(a1, a2)

        animals.value = animalList
        loadError.value = false
        loading.value = false
    }
}