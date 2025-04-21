package com.example.dogapi2recycler.repository

import androidx.lifecycle.MutableLiveData
import com.example.dogapi2recycler.model.Dog
import com.example.dogapi2recycler.model.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoClass {
    val coroutineScope = CoroutineScope(Dispatchers.Main)
    val dogList = MutableLiveData<ArrayList<Dog>>()
    val error = MutableLiveData<String>()

    fun baseURL(): String {
        return "https://api.thedogapi.com"
    }

    fun imageURL(): String {
        return "https://cdn2.thedogapi.com/images/"
    }

    fun getList() {
        coroutineScope.launch {
            val response = withContext(Dispatchers.IO) {
                RetrofitHelper.createRetrofit(baseURL()).getDogs(limit = 20)
            }
            if (response.isSuccessful)
                dogList.value = response.body()
            else
                error.value = response.errorBody().toString()
        }
    }
}