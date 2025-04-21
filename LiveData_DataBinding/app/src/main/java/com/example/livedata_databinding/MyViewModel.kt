package com.example.livedata_databinding

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    private val mutableLD = MutableLiveData<String>("Default")
    val liveData : LiveData<String> =mutableLD
    var count=0
    fun updateMessage(view : View){
        mutableLD.value="Live || Data Binding ${count++}"
    }


    val newText= MutableLiveData("Demo")
}