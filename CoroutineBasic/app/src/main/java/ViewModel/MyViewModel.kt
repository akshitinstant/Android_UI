package ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    init{
        viewModelScope.launch {
            var i=0
            while (true){
                delay(500)
                Log.d("vmScope","${++i}. In View Model Scope")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("vmScope","View Model Destroyed ${Thread.currentThread().name}")
    }
}