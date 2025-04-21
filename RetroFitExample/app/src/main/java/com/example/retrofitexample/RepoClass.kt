package com.example.retrofitexample

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoClass {
    var BASE_URL="https://api.thecatapi.com/v1/"
    var coroutineScope= CoroutineScope(Dispatchers.IO)
    fun setAPI(){
        RetroFitHelper.createRetrofit(BASE_URL)
    }


    fun getList() : List<Cat>?{
        var catList : List<Cat>? = null
        coroutineScope.launch {
            catList =  RetroFitHelper.api.getCats(limit = 10, mime = "gif")
        }
        return catList
    }
}