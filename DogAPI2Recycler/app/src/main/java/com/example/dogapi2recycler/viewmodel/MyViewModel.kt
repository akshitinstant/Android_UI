package com.example.dogapi2recycler.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogapi2recycler.model.Dog
import com.example.dogapi2recycler.repository.RepoClass

class MyViewModel : ViewModel() {

    var list: ArrayList<Dog> = arrayListOf()
    val repo = RepoClass()

    init {
        repo.getList()
    }

    fun getBaseURL():String{
        return repo.baseURL()
    }

    fun getImageURL():String{
        Log.d("h3", "vm"+repo.imageURL())
        return repo.imageURL()
    }

    fun getDogList(): LiveData<ArrayList<Dog>> {
        return repo.dogList
    }

}