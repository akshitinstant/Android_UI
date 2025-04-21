package com.example.architecture.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.architecture.model.User


class MyViewModel2(val user: User) : ViewModel() {
    // ViewModel logic

/*    companion object {
        val MY_USER_KEY = object : CreationExtras.Key<User> {}
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val user = checkNotNull(extras[MY_USER_KEY]) {
                    "Repository not found in CreationExtras"
                }
                return MyViewModel(user) as T
            }
        }
    }*/
}
