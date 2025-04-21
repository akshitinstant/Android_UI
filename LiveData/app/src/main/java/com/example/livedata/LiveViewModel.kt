package com.example.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveViewModel : ViewModel() {
    private val _factsLiveData = MutableLiveData<String>()
    val factsLiveData: LiveData<String> = _factsLiveData
    var count = 0
    fun updateLive() {
        _factsLiveData.value = "Updated text ${count++}"
    }
}