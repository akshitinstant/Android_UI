package com.example.architecture.views

import androidx.lifecycle.ViewModel
import com.example.architecture.model.User

class MyViewModel(val initial: Int): ViewModel() {
    var count:Int=initial

    fun increment(): Int{
        return count++
    }
}