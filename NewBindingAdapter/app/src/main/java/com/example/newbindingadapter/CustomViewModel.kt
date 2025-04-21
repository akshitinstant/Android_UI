package com.example.newbindingadapter

import android.view.View
import androidx.lifecycle.ViewModel

class CustomViewModel: ViewModel() {
var screenState = ScreenState()

    fun increment(view: View){
        screenState.count++
    }

    fun decrement(view: View){
        screenState.count--;
    }
}